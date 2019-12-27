package com.example.mycam.bottom.ui.service;

import com.example.mycam.bottom.model.CategoriItem;
import com.example.mycam.bottom.model.KameraItem;
import com.example.mycam.bottom.model.PemesananItem;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface  Service {
    @FormUrlEncoded
    @POST("kamera/ajax_action_get_kamera")
    Call<List<KameraItem>> getAllCamera(@Field("id") String id);

    @GET("kamera/ajax_action_get_kamera_merk")
    Call<List<CategoriItem>> getAllMerk();


    @FormUrlEncoded
    @POST("pemesanan/ajax_action_pemesanan")
    Call<ResponseBody> actPemesanan(@Field("nama_pemesan") String nama_pemesanan,
                                        @Field("id_kamera") String id_kamera,
                                        @Field("nomor_pemesan") String nomor_pemesanan,
                                        @Field("alamat_pemesan") String alamat,
                                        @Field("email_pemesan") String email);

    @FormUrlEncoded
    @POST("pemesanan/ajax_action_get_kamera_id")
    Call<JsonObject> actDetailkamera(@Field("id") String id);

    @GET("pemesanan/ajax_action_get_pemesanan_all")
    Call<List<PemesananItem>> getPemesanan();

    // Fungsi login
    @FormUrlEncoded
    @POST("login/ajax_action_login")
    Call<ResponseBody> PassswordRequest(@Field("username") String username,
                                        @Field("password") String password);


}
