package com.example.mikky.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mikky.R;
import com.example.mikky.adapter.Drink;
import com.example.mikky.adapter.DrinkAdapter;

import java.util.ArrayList;
import java.util.List;


public class DrinkListActivity extends AppCompatActivity {

    private RecyclerView rcvDrink;
    private DrinkAdapter drinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);
        rcvDrink = findViewById(R.id.rcv_drink);
        drinkAdapter = new DrinkAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvDrink.setLayoutManager(linearLayoutManager);

        drinkAdapter.setDrinkData(getListDrink());

    }

    //GetData API Function//
    private List<Drink> getListDrink() {
        List<Drink> list = new ArrayList<Drink>();
        list.add(new Drink(0, "Tra Sua 1", "MT", String.valueOf(R.drawable.ts1), "description", 5.5F));
        return list;
    }
}