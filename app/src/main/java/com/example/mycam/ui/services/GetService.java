package com.example.mycam.ui.services;

import com.example.mycam.ui.models.PhotoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetService {
    @GET("/photos")
    Call<List<PhotoData>> getAllPhotos();
}