package com.example.mikky.activities;


import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mikky.R;
import com.example.mikky.models.OrderDetail;

import java.util.ArrayList;

public class CartActivity extends ListActivity {
    private int drinkId, quantity, price;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItems;
    private OrderDetail orderDetail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        listItems=new ArrayList<String>();


        SharedPreferences shCart = this.getSharedPreferences("Cart", MODE_PRIVATE);
        SharedPreferences shUser = this.getSharedPreferences("User", MODE_PRIVATE);
        for(int i =1; i<10; i++) {
            drinkId=shCart.getInt("DrinkId"+i, 0);
            quantity=shCart.getInt("Quantity"+i, 0);
            price = shCart.getInt("Price"+i,0);
            shUser.getInt("UserId",0);
            if (drinkId !=0 && quantity !=0 && price !=0){
                orderDetail.setDrinkId(drinkId);
                orderDetail.setPrice(price);
                orderDetail.setQuantity(quantity);
                listItems.add(orderDetail.toString());
                Toast.makeText(CartActivity.this,listItems.toString(),Toast.LENGTH_SHORT).show();
            }
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listItems);
        setListAdapter(adapter);
    }
}
