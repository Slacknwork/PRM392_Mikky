package com.example.mikky.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikky.R;
import com.example.mikky.instances.RetrofitInstance;
import com.example.mikky.services.ApiInterface;
import com.example.mikky.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        apiInterface.getposts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body().size()>0){
                    textView = (TextView)findViewById(R.id.textView1);
                    textView.setText("Product");
                    Toast.makeText(MainActivity.this,"List is not empty",Toast.LENGTH_SHORT);
                }else{
                    textView = (TextView)findViewById(R.id.textView1);
                    textView.setText("Failed");
                    Toast.makeText(MainActivity.this,"List is empty",Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                textView = (TextView)findViewById(R.id.textView1);
                textView.setText(t.getLocalizedMessage());
                Toast.makeText(MainActivity.this,"NOT WORKING",Toast.LENGTH_LONG);
            }

        });
    }
}