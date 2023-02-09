package com.example.mikky.services;

import com.example.mikky.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("All")
    Call<List<Product>> getposts();
    @GET("")
    Call<Product> getItem(@Query("Id")int id);
}
