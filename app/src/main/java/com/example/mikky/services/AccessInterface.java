package com.example.mikky.services;

import com.example.mikky.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccessInterface {
    @GET("User")
    Call<List<User>> getALLUser();
    @GET("User/Usrname={username}&Pwd={password}")
    Call<User> getUser(@Path(value = "username")String username, @Path(value = "password")String password);
    @POST("User/create")
    Call<User> createUser(@Body User user);
}
