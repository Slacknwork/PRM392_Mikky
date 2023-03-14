package com.example.mikky.activities;


import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mikky.R;
import com.example.mikky.adapter.CartAdapter;
import com.example.mikky.instances.RetrofitInstance;
import com.example.mikky.models.Drink;
import com.example.mikky.models.Order;
import com.example.mikky.models.OrderDetail;
import com.example.mikky.services.ApiInterface;
import com.example.mikky.services.OrderInterface;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private int drinkId, quantity, shSize;
    private Drink drink;
    private RecyclerView rcvCart;
    private CartAdapter cartAdapter;
    private List<Drink> listDrink = new ArrayList<>();
    private List<Integer> listQuantity = new ArrayList<>();
    private ApiInterface apiInterface;
    private OrderInterface orderInterface;
    private String name,img;
    private Double price,totalPrice = Double.valueOf(0);
    private TextView tp;
    private TextView textEmpty;
    private Button btnOrder, btnContinue;
    private Order order = new Order();
    private OrderDetail orderDetail = new OrderDetail();
    private List<OrderDetail> listOD = new ArrayList<>();

    DecimalFormat df = new DecimalFormat("###,###,###");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnOrder = findViewById(R.id.btnPay);
        btnContinue = findViewById(R.id.btnContinueShopping);
        tp = findViewById(R.id.total_price);
        textEmpty = findViewById(R.id.empty);
        rcvCart = findViewById(R.id.cart_list);
        cartAdapter = new CartAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvCart.setLayoutManager(linearLayoutManager);

        SharedPreferences shCart = this.getSharedPreferences("Cart", MODE_PRIVATE);
        SharedPreferences shUser = this.getSharedPreferences("User", MODE_PRIVATE);
        shSize = shCart.getAll().size() / 5;

        for(int i =1; i<=shSize; i++) {
            drinkId=shCart.getInt("DrinkId"+i, 0);
            quantity=shCart.getInt("Quantity"+i, 0);
            price = Double.valueOf(shCart.getFloat("Price"+i,0));
            name = shCart.getString("Name"+i,"");
            img = shCart.getString("Image"+i,"");
            shUser.getInt("UserID",0);
            totalPrice += price*quantity;
            if (drinkId > 0 ){
                drink = new Drink();
                orderDetail = new OrderDetail();
                drink.setDrinkId(drinkId);
                drink.setDrinkname(name);
                drink.setDrinkImage(img);
                drink.setPrice(price);
                drink.setDescription("0");
                drink.setDrinkCateId(0);
                orderDetail.setDrinkId(drinkId);
                orderDetail.setQuantity(quantity);
                orderDetail.setPrice(price.floatValue());
                listDrink.add(drink);
                listQuantity.add(quantity);
                listOD.add(orderDetail);
                //Toast.makeText(CartActivity.this,orderDetail.getDrinkId()+":" + orderDetail.getPrice()+":"+orderDetail.getQuantity(),Toast.LENGTH_SHORT).show();
            }
        }

        cartAdapter.setCartData(listDrink,listQuantity);
        rcvCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        tp.setText(df.format(totalPrice) + " đồng");

        if (listDrink.size() == 0){
            textEmpty.setVisibility(View.VISIBLE);
        }else textEmpty.setVisibility(View.INVISIBLE);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this, DrinkListActivity.class);
                startActivity(i);
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Calendar cal = Calendar.getInstance();
                java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
                order.setUserId(shUser.getInt("UserID",0));
                order.setDate(timestamp);
                order.setStatus("Delivering");
                order.setTotalPrice(totalPrice.floatValue());
                orderInterface = RetrofitInstance.getRetrofit().create(OrderInterface.class);
                //Toast.makeText(CartActivity.this,order.getUserId()+":" + order.getDate()+order.getStatus() +order.getTotalPrice(),Toast.LENGTH_SHORT).show();
                orderInterface.createOrder(order).enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        if (response.isSuccessful()){
                            if (response.body() !=null) {
                                Toast.makeText(CartActivity.this, "Place Order!", Toast.LENGTH_SHORT).show();
                                for (OrderDetail od : listOD){
                                    od.setOrderId(response.body().getOrderId());
                                    //Toast.makeText(CartActivity.this,od.getDrinkId()+":" + od.getOrderId()+":" +od.getQuantity()+":" + +od.getPrice(),Toast.LENGTH_SHORT).show();
                                    orderInterface.createOrderDetail(od).enqueue(new Callback<OrderDetail>() {
                                        @Override
                                        public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
                                            if (response.isSuccessful()) {
                                                if (response.body() != null) {
                                                    SharedPreferences.Editor editCart = shCart.edit();
                                                    editCart.clear();
                                                    editCart.apply();
                                                    finish();
                                                    startActivity(getIntent());
                                                }else {
                                                    Toast.makeText(CartActivity.this, "Place Order Fail!", Toast.LENGTH_SHORT).show();
                                                }
                                            }else Toast.makeText(CartActivity.this, "Not success", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<OrderDetail> call, Throwable t) {

                                        }
                                    });
                                }
                            }else {
                                Toast.makeText(CartActivity.this,"Order Fail!",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(CartActivity.this,"Not success",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        Toast.makeText(CartActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private Drink getDrink(int id) {
        Drink list = new Drink();
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        Toast.makeText(CartActivity.this,"begin"+id,Toast.LENGTH_SHORT).show();
        apiInterface.getItem(id).enqueue(new Callback<Drink>() {
            @Override
            public void onResponse(Call<Drink> call, Response<Drink> response) {
                Toast.makeText(CartActivity.this,"before",Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    Toast.makeText(CartActivity.this,"bi null",Toast.LENGTH_SHORT).show();
                    if (response.body() != null){
                        Toast.makeText(CartActivity.this,"chay",Toast.LENGTH_SHORT).show();
                        list.setDrinkId(response.body().getDrinkId());
                        list.setDrinkname(response.body().getDrinkImage());
                        list.setPrice(response.body().getPrice());
                        list.setDrinkImage(response.body().getDrinkImage());
                        cartAdapter.notifyDataSetChanged();
                        return;
                    }else{
                        Toast.makeText(CartActivity.this,"Fail!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CartActivity.this,"Connect fail!",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Drink> call, Throwable t) {
                Toast.makeText(CartActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        return list;
    }
}
