package com.example.mikky.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        rcvDrink.setAdapter(drinkAdapter);
    }

    //GetData API Function//
    private List<Drink> getListDrink() {
        List<Drink> list = new ArrayList<>();
        list.add(new Drink(R.drawable.ts1,"Tra Sua 1", 25000f));
        list.add(new Drink(R.drawable.ts2,"Tra Sua 2", 30000f));
        list.add(new Drink(R.drawable.ts3,"Tra Sua 3", 15000f));
        list.add(new Drink(R.drawable.st4,"Sinh to 4", 25000f));
        list.add(new Drink(R.drawable.st5,"Sinh to 5", 35000f));
        list.add(new Drink(R.drawable.st6,"Sinh to 6", 15000f));
        list.add(new Drink(R.drawable.t7,"Tra 7", 15000f));
        list.add(new Drink(R.drawable.t8,"Tra 8", 15000f));
        return list;
    }


}