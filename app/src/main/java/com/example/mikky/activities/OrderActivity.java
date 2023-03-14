package com.example.mikky.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mikky.R;
import com.example.mikky.adapter.DrinkAdapter;
import com.example.mikky.adapter.HistoryAdapter;
import com.example.mikky.instances.RetrofitInstance;
import com.example.mikky.models.Drink;
import com.example.mikky.models.Order;
import com.example.mikky.services.ApiInterface;
import com.example.mikky.services.OrderInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView rcvOrder;
    private HistoryAdapter historyAdapter;
    private OrderInterface orderInterface;
    private List<Order> listOrder = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        rcvOrder = findViewById(R.id.rcv_order);
        historyAdapter = new HistoryAdapter(this);
        toolbar =(Toolbar) findViewById(R.id.toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvOrder.setLayoutManager(linearLayoutManager);

        SharedPreferences shUser = this.getSharedPreferences("User", MODE_PRIVATE);
        listOrder = getListOrder(shUser.getInt("UserID",0));
        historyAdapter.setOrderData(listOrder);
        rcvOrder.setAdapter(historyAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                Intent intentCart = new Intent(OrderActivity.this, CartActivity.class);
                startActivity(intentCart);
                break;
            case R.id.exit:
                Intent intentLogout = new Intent(OrderActivity.this, LoginActivity.class);
                startActivity(intentLogout);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Order> getListOrder(int id){
        List<Order> list = new ArrayList<>();
        orderInterface = RetrofitInstance.getRetrofit().create(OrderInterface.class);
        orderInterface.getOrderByUser(id).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        for (Order order : response.body()){
                            list.add(order);
                        }
                        historyAdapter.notifyDataSetChanged();
                    }else Toast.makeText(OrderActivity.this,"Fail!",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(OrderActivity.this,"Connect Fail!",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(OrderActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        return list;
    }
}
