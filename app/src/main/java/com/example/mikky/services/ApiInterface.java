package com.example.mikky.interfaces;

import com.example.mikky.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("All")
    Call<List<Product>> getposts();
}
