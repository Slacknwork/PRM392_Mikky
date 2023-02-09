package com.example.mikky.services;

import com.example.mikky.models.Admin;
import com.example.mikky.models.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccessInterface {
    @GET("Customer/All")
    Call<List<Customer>> getALLUser();
    @GET("Customer")
    Call<Customer> getUser(@Query("username") String username, @Query("password")String password);
    @GET("Admin")
    Call<Admin> getAdmin(@Query("username")String username,@Query("password")String password);
}
