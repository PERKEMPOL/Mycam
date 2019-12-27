package com.example.mycam.bottom.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mycam.MainActivity;
import com.example.mycam.R;
import com.example.mycam.bottom.BottomNavigation;
import com.example.mycam.bottom.Tools.SharedPrefManager;
import com.example.mycam.bottom.ui.service.ApiClient;
import com.example.mycam.bottom.ui.service.Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminFragment extends Fragment {

    EditText username,passwords;
    Button btnlogin;
    SharedPrefManager sharedPrefManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin, container, false);
        username = root.findViewById(R.id.username);
        passwords = root.findViewById(R.id.password);
        btnlogin = root.findViewById(R.id.btn_login);
        sharedPrefManager = new SharedPrefManager(getActivity());
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        return root;
    }

    public void login(){
        String no = username.getText().toString();
        String password = passwords.getText().toString();

        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        service.PassswordRequest(no,password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("result").equals("true")){
                            String id = jsonRESULTS.getString("id");
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, id);
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            Toast.makeText(getActivity(), "Selamat datang admin di mycam", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), BottomNavigation.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        } else {
                            String error_message = jsonRESULTS.getString("result");
                            Toast.makeText(getActivity(), "Pastikan username atau password anda benar", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }

        });
    }
}