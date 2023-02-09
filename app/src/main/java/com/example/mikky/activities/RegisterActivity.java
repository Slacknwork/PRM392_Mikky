package com.example.mikky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mikky.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText username,password,address,phone;
    private TextView banner;
    private Button register;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        banner = findViewById(R.id.banner);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        register = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressbar);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String userName = username.getText().toString();
                String pw = password.getText().toString();
                String ar = address.getText().toString();
                String p = phone.getText().toString();
                if ((TextUtils.isEmpty(userName) && TextUtils.isEmpty(pw)) || pw.length() < 6 ){
                    Toast.makeText(RegisterActivity.this,"Please enter...",Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
    }
}
