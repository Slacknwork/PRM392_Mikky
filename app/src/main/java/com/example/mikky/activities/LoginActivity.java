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

public class LoginActivity extends AppCompatActivity {
    private EditText username,password;
    private ProgressBar progressBar;
    private Button login;
    private TextView registerLink;
    private AccessInterface accessInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.progressbar);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerLink = findViewById(R.id.registerlink);
        login = findViewById(R.id.login);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String userName = username.getText().toString();
                String pw = password.getText().toString();
                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pw)){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,"Please enter username/password",Toast.LENGTH_SHORT).show();
                }else {
                    login(userName,pw);
                }
            }
        });
    }
    private void login(String username, String pw){
        accessInterface = RetrofitInstance.getRetrofit().create(AccessInterface.class);
        accessInterface.getUser(username,pw).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Toast.makeText(LoginActivity.this,"Login successfully!",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(LoginActivity.this,"Wrong Username/Password!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Wrong Username/Password!",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
