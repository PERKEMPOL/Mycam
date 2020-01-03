package com.example.mycam.bottom.ui.admin;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mycam.R;
import com.example.mycam.adapter.CategoriAdapter;
import com.example.mycam.adapter.PemesananAdapter;
import com.example.mycam.bottom.Tools.PublicTools;
import com.example.mycam.bottom.Tools.RecyclerItemClickListener;
import com.example.mycam.bottom.model.CategoriItem;
import com.example.mycam.bottom.model.PemesananItem;
import com.example.mycam.bottom.ui.activity.CategoryCamera;
import com.example.mycam.bottom.ui.activity.DetailKamera;
import com.example.mycam.bottom.ui.activity.DetailPemesanan;
import com.example.mycam.bottom.ui.kategori.KategoriViewModel;
import com.example.mycam.bottom.ui.service.ApiClient;
import com.example.mycam.bottom.ui.service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPemesanan extends Fragment {

    private KategoriViewModel kategoriViewModel;
    private RecyclerView recyclerView;
    private PublicTools tools;
    private PemesananAdapter adapter;
    PublicTools publicTools;
    private List<PemesananItem> payArrayList = new ArrayList<>();
    public ListPemesanan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_pemesanan, container, false);
        declaration(root);
        list();
        return root;
    }

    public void declaration(View view){

        tools = new PublicTools(getActivity());
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setNestedScrollingEnabled(false);
    }


    public void list(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        Call<List<PemesananItem>> call = service.getPemesanan();
        call.enqueue(new Callback<List<PemesananItem>>() {
            @Override
            public void onResponse(Call<List<PemesananItem>> call, Response<List<PemesananItem>> response) {
                Log.e("successProvider",response.body().toString());
                if(response.isSuccessful()) {
                    payArrayList = response.body();
                    generateDataList(payArrayList);
                }else{
                    Toast.makeText(getActivity(), "Mohon Cek Koneksi Internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PemesananItem>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });
    }

    private void generateDataList(List<PemesananItem> listPayment) {
        adapter = new PemesananAdapter(listPayment,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        String id_pemesanan = payArrayList.get(position).getIdPemesanan();
                        Intent i = new Intent(getActivity(), DetailPemesanan.class);
                        i.putExtra("id_pemesanan",id_pemesanan);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }

                ));
    }



}
