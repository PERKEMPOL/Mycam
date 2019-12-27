package com.example.mycam.bottom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycam.R;
import com.example.mycam.bottom.model.KameraItem;
import com.example.mycam.bottom.ui.service.ApiClient;
import com.example.mycam.bottom.ui.service.Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembelianActivity extends AppCompatActivity {
    EditText email_pemesan,alamat_pemesan,nomor_pemesan,nama_pemesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembelian);
        email_pemesan = findViewById(R.id.email_pemesan);
        alamat_pemesan = findViewById(R.id.alamat_pemesan);
        nomor_pemesan = findViewById(R.id.nomor_pemesan);
        nama_pemesan = findViewById(R.id.nama_pemesan);
    }

    public void pembelian(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String id = getIntent().getStringExtra("id_merk");
        String nama = nama_pemesan.getText().toString();
        String nomor = nomor_pemesan.getText().toString();
        String alamat = alamat_pemesan.getText().toString();
        String email = email_pemesan.getText().toString();

        Call<ResponseBody> call = service.actPemesanan(nama,id,nomor,alamat,email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("result").equals("true")) {
                            Toast.makeText(getApplicationContext(), "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();

                        } else {
                            String error_message = jsonRESULTS.getString("message");
                                        Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                            Log.e("check",error_message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("check",e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("check",e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });
    }

    public void btnPemesanan(View view) {
        pembelian();
    }
}