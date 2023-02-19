package com.example.mikky.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mikky.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbarDetail;
    ImageView imageDetail;
    TextView txtName, txtPrice, txtDescription;
    EditText editTxtAmount;
    Button btnMinus, btnPlus, btnAddCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        mapping();
        Back();
        GetDetail();
    }

    private void GetDetail() {
        int id = 0;
        String name = "";
        int cateId =0;
        String img = "";
        String description = "";
        int price = 0;

        txtName.setText(name);
        DecimalFormat df = new DecimalFormat("###,###,###");
        txtPrice.setText(df.format(price)+"đ");
        editTxtAmount.setText(0);
        txtDescription.setText(description);
        Picasso.get().load(img).placeholder(R.drawable.noimage).error(R.drawable.error);
    }

    private void Back() {
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
