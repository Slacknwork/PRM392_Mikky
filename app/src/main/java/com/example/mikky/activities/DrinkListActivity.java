package com.example.mikky.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;


import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import com.example.mikky.R;
import com.example.mikky.models.Drink;
import com.example.mikky.adapter.DrinkAdapter;
import com.example.mikky.instances.RetrofitInstance;
import com.example.mikky.services.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DrinkListActivity extends AppCompatActivity {

    private RecyclerView rcvDrink;
    private DrinkAdapter drinkAdapter;
    private ApiInterface apiInterface;
    private List<Drink> listDrink = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);

        rcvDrink = findViewById(R.id.rcv_drink);
        drinkAdapter = new DrinkAdapter(this);
        toolbar =(Toolbar) findViewById(R.id.toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvDrink.setLayoutManager(linearLayoutManager);

        listDrink = getListDrink();
        drinkAdapter.setDrinkData(listDrink);
        rcvDrink.setAdapter(drinkAdapter);

        SharedPreferences sh = getSharedPreferences("User", Context.MODE_PRIVATE);
        if (sh.getInt("ID",0) !=0){
            Toast.makeText(DrinkListActivity.this,sh.getInt("ID",0),Toast.LENGTH_SHORT).show();
        }

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Cart:
                Intent intentCart = new Intent(DrinkListActivity.this, CartActivity.class);
                startActivity(intentCart);
                break;
            case R.id.logout:
                Intent intentLogout = new Intent(DrinkListActivity.this, LoginActivity.class);
                startActivity(intentLogout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //GetData API Function//
    private List<Drink> getListDrink() {
        List<Drink> list = new ArrayList<>();
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        apiInterface.getListDrink().enqueue(new Callback<List<com.example.mikky.models.Drink>>() {
            @Override
            public void onResponse(Call<List<Drink>> call, Response<List<Drink>> response) {
                if (response.isSuccessful()){

                    if (response.body() != null){
                        Toast.makeText(DrinkListActivity.this,"Successfully!",Toast.LENGTH_SHORT).show();
                        for( Drink d : response.body()){
                            list.add(d);
                        }
                        drinkAdapter.notifyDataSetChanged();

                    }else{
                        Toast.makeText(DrinkListActivity.this,"Fail!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DrinkListActivity.this,"Connect fail!",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Drink>> call, Throwable t) {
                Toast.makeText(DrinkListActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

//        List<Drink> list = new ArrayList<>();
//        list.add(new Drink(1,"Tra Sua 1",1,"","", 25000f));
//        list.add(new Drink(2,"Tra Sua 2",2,"","", 30000f));
        return list;
    }


}