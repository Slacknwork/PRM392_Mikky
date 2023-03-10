package com.example.mikky.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mikky.R;
import com.example.mikky.instances.RetrofitInstance;
import com.example.mikky.models.Drink;
import com.example.mikky.services.ApiInterface;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbarDetail;
    ImageView imageDetail;
    TextView txtName, txtPrice, txtDescription;
    EditText editTxtAmount;
    Button btnMinus, btnPlus, btnAddCart;
    private Toolbar toolbar;
    int quantity = 0;

    private ApiInterface apiInterface;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMinus = (Button) findViewById(R.id.btnPlus);
        editTxtAmount = findViewById(R.id.editTxtAmount);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        int drinkId = (int) getIntent().getIntExtra("putIdToDetail",0);
        int price = (int)getIntent().getIntExtra("Price",0);
        quantity = Integer.parseInt(editTxtAmount.getText().toString());
        mapping();

        back();
        getApi();

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quantity > 0) quantity--;
                editTxtAmount.setText(String.valueOf(quantity));
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantity++;
                editTxtAmount.setText(String.valueOf(quantity));
            }
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Cart",MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                int currentQuantity = sharedPreferences.getInt("Quantity"+drinkId, 0);
                int cartQuantity = quantity + currentQuantity;
                edit.putInt("DrinkId"+drinkId, drinkId);
                edit.putInt("Quantity"+drinkId, cartQuantity);
                edit.putFloat("Price"+drinkId, price);
                edit.commit();
                Toast.makeText(DetailActivity.this,"Add to Cart",Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void getApi(){
        int drinkId = (int) getIntent().getIntExtra("putIdToDetail",0);
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        apiInterface.getItem(drinkId).enqueue(new Callback<Drink>() {
            @Override
            public void onResponse(Call<Drink> call, Response<Drink> response) {
                if (response.isSuccessful()){
                    Drink drink = response.body();
                    if (drink != null){
                        Toast.makeText(DetailActivity.this,"Successfully!",Toast.LENGTH_SHORT).show();

                        txtName.setText(drink.getDrinkname());
                        DecimalFormat df = new DecimalFormat("###,###,###");
                        txtPrice.setText(df.format((int) drink.getPrice())+"đ");
                        txtDescription.setText(drink.getDescription());
                        String img = drink.getDrinkImage();
                        Picasso.get().load(img).placeholder(R.drawable.noimage).error(R.drawable.error).into(imageDetail);
                    }else{
                        Toast.makeText(DetailActivity.this,"Fail!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DetailActivity.this,"Connect fail!",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Drink> call, Throwable t) {
                Toast.makeText(DetailActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void back() {
        setSupportActionBar(toolbarDetail);
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        //toolbarDetail = (Toolbar) findViewById(R.id.toolbarDetail);
        imageDetail = (ImageView) findViewById(R.id.imgDetail);
        txtName = (TextView) findViewById(R.id.txtName);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        editTxtAmount = (EditText) findViewById(R.id.editTxtAmount);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnAddCart = (Button) findViewById(R.id.btnAddCart);

    }
}
