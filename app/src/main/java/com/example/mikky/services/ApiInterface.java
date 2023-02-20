package com.example.mikky.services;

import com.example.mikky.models.Drink;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("All")
    Call<List<Drink>> getposts();
    @GET("Drink/Drid={id}")
    Call<Drink> getItem(@Path(value = "id", encoded = true)int id);
}
