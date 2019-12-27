package com.example.mycam.bottom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycam.R;
import com.example.mycam.adapter.KameraAdapter;
import com.example.mycam.bottom.Tools.PublicTools;
import com.example.mycam.bottom.model.KameraItem;
import com.example.mycam.bottom.ui.service.ApiClient;
import com.example.mycam.bottom.ui.service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryCamera extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    private KameraAdapter adapter;
    PublicTools publicTools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_camera);
        declaration();
        list();
    }

    public void declaration(){
        publicTools = new PublicTools(getApplicationContext());
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public  void list(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String id = getIntent().getStringExtra("id_merk");
        Call<List<KameraItem>> call = service.getAllCamera(id);
        call.enqueue(new Callback<List<KameraItem>>() {
            @Override
            public void onResponse(Call<List<KameraItem>> call, Response<List<KameraItem>> response) {

                if(response.isSuccessful()) {
                    generateDataList(response.body());
                }else{
                    Log.e("aa",response.body().toString());
                    Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<KameraItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });


    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<KameraItem> priceItem) {
        adapter = new KameraAdapter(priceItem,getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }
}
