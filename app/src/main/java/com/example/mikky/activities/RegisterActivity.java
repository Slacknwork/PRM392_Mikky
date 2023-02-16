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
import com.example.mikky.instances.RetrofitInstance;
import com.example.mikky.models.User;
import com.example.mikky.services.AccessInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText username,password,address,phone;
    private TextView banner;
    private Button register;
    private ProgressBar progressBar;
    private AccessInterface accessInterface;
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
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this,"Please enter matching requirement!",Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User();
                    user.setUsername(userName);
                    user.setPassword(pw);
                    user.setAddress(ar);
                    user.setPhonenumber(Integer.parseInt(p));
                    user.setRole(0);
                    register(user);
                }
            }
        });
    }
    private void register(User user){
        accessInterface = RetrofitInstance.getRetrofit().create(AccessInterface.class);
        accessInterface.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null){
                    Toast.makeText(RegisterActivity.this,"Success create an account!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(RegisterActivity.this,"Account already exits!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
