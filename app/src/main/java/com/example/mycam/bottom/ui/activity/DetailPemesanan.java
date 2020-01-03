package com.example.mycam.bottom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mycam.R;
import com.example.mycam.bottom.model.KameraItem;
import com.example.mycam.bottom.ui.service.ApiClient;
import com.example.mycam.bottom.ui.service.PublicStatic;
import com.example.mycam.bottom.ui.service.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemesanan extends AppCompatActivity {
    TextView email_pemesan,alamat_pemesan,nomor_pemesan,nama_pemesan, total, namaKamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemesanan);
        email_pemesan = findViewById(R.id.email_pemesan);
        alamat_pemesan = findViewById(R.id.alamat_pemesan);
        nomor_pemesan = findViewById(R.id.nomor_pemesan);
        nama_pemesan = findViewById(R.id.nama_pemesan);
        namaKamera = findViewById(R.id.namaKamera);
        total = findViewById(R.id.total);
        harga();
    }

    public void harga(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String id = getIntent().getStringExtra("id_pemesanan");
        Call<JsonObject> call = service.actDetailpemesanan(id);
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
                            String namaa = detail.get("nama_pemesan").toString().replace("\"", "");
                            String nomorr = detail.get("nomor_pemesanan").toString().replace("\"", "");
                            String alamatt = detail.get("alamat_pemesan").toString().replace("\"", "");
                            String emaill = detail.get("email_pemesan").toString().replace("\"", "");
                            String namakamera = detail.get("id_kamera").toString().replace("\"", "");
                            String total1l = detail.get("harga_kamera").toString().replace("\"", "");
                            nama_pemesan.setText(namaa);
                            nomor_pemesan.setText(nomorr);
                            alamat_pemesan.setText(alamatt);
                            email_pemesan.setText(emaill);
                            namaKamera.setText(namakamera);
                            total.setText(total1l);
//                            Toast.makeText(getApplicationContext(), nama, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String error_message = status;
                        Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gangguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }

        });
    }

    public void pemesanan(View view) {
        String id_pemesanan = getIntent().getStringExtra("id_pemesanan");
        Intent i = new Intent(getApplicationContext(), DetailPemesanan.class);
        i.putExtra("id_pemesanan",id_pemesanan);
        startActivity(i);
    }
}