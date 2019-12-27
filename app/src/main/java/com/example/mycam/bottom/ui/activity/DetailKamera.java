package com.example.mycam.bottom.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mycam.R;
import com.example.mycam.bottom.ui.service.ApiClient;
import com.example.mycam.bottom.ui.service.PublicStatic;
import com.example.mycam.bottom.ui.service.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKamera extends AppCompatActivity {
    TextView keterengan;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamera);
        keterengan = findViewById(R.id.keterengan);
        logo = findViewById(R.id.logo);
        detail();
    }

    public void detail(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String id = getIntent().getStringExtra("id_merk");
        Call<JsonObject> call = service.actDetailkamera(id);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    String status = response.body().getAsJsonObject().get("result").toString();
                    if (status.equals("true")) {
                        JsonArray array = response.body().getAsJsonObject().getAsJsonArray("data");
                        Log.d("hasilDetail", array.toString());
                        for (int i = 0; i < array.size(); i++) {
                            JsonObject detail = array.get(i).getAsJsonObject();
                            String nama = detail.get("spek_kamera").toString().replace("\"", "");
                            String path = detail.get("nama_file").toString().replace("\"", "");
                            keterengan.setText(nama);
                            Glide.with(getApplicationContext())
                                    .load(PublicStatic.path +path)
                                    .into(logo);
//                            Toast.makeText(getApplicationContext(), nama, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String error_message = status;
                        Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }

        });
    }

    public void pemesanan(View view) {
        String id_merk = getIntent().getStringExtra("id_merk");
        Intent i = new Intent(getApplicationContext(), PembelianActivity.class);
        i.putExtra("id_merk",id_merk);
        startActivity(i);
    }
}
