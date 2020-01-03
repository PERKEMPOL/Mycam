package com.example.mycam.bottom.ui.kategori;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycam.R;
import com.example.mycam.adapter.CategoriAdapter;
import com.example.mycam.adapter.KameraAdapter;
import com.example.mycam.bottom.Tools.PublicTools;
import com.example.mycam.bottom.Tools.PublicTools;
import com.example.mycam.bottom.Tools.RecyclerItemClickListener;
import com.example.mycam.bottom.model.CategoriItem;
import com.example.mycam.bottom.model.KameraItem;
import com.example.mycam.bottom.ui.activity.CategoryCamera;
import com.example.mycam.bottom.ui.service.ApiClient;
import com.example.mycam.bottom.ui.service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KategoriFragment extends Fragment {

    private KategoriViewModel kategoriViewModel;
    private RecyclerView recyclerView;
    private PublicTools tools;
    private CategoriAdapter adapter;
    PublicTools publicTools;
    private List<CategoriItem> payArrayList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kategoriViewModel =
                ViewModelProviders.of(this).get(KategoriViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kategori, container, false);
        declaration(root);
        list();
        return root;
    }

    public void declaration(View view){

        tools = new PublicTools(getActivity());
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setNestedScrollingEnabled(false);
    }


    public  void list(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        Call<List<CategoriItem>> call = service.getAllMerk();
        call.enqueue(new Callback<List<CategoriItem>>() {
            @Override
            public void onResponse(Call<List<CategoriItem>> call, Response<List<CategoriItem>> response) {
                Log.e("successProvider",response.body().toString());
                if(response.isSuccessful()) {
                    payArrayList = response.body();
                    generateDataList(payArrayList);
                }else{
                    Toast.makeText(getActivity(), "Mohon Cek Koneksi Internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoriItem>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });
    }

    private void generateDataList(List<CategoriItem> listPayment) {
        adapter = new CategoriAdapter(listPayment,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        String id_merk = payArrayList.get(position).getIdMerk();
                        Intent i = new Intent(getActivity(), CategoryCamera.class);
                        i.putExtra("id_merk",id_merk);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }

                ));
    }


}