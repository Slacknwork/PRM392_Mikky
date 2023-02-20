package com.example.mikky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        mapping();
//        back();
        getApi(1);
    }

    private void getApi(int drinkId){
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
                        String img = drink.getImage();
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
                loadDetail();
            }
        });
    }

    private void loadDetail() {
        int id = 1;
        String name = "tra sua";
        int cateId =1;
        String img = "https://pos.cafeongbau.com:4433/images/list/tra_sua_tran_chau.png";
        String description = "aaaaaa";
        int price = 25000;
        int amount = 1;

        txtName.setText(name);
        DecimalFormat df = new DecimalFormat("###,###,###");
        txtPrice.setText(df.format(price)+"đ");
        txtDescription.setText(description);
        Picasso.get().load(img).placeholder(R.drawable.noimage).error(R.drawable.error).into(imageDetail);
    }

    private void back() {
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        toolbarDetail = (Toolbar) findViewById(R.id.toolbarDetail);
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
