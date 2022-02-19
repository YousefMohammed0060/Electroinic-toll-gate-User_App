package com.example.finalproject.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.NavBar.BottomNav;
import com.example.finalproject.R;
import com.example.finalproject.SignUp.First_SignUp;

import java.util.Locale;

public class Login extends AppCompatActivity {

    EditText LoginEmail,LoginPassword;
    TextView ForgotPassword,SignUPTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        LoginEmail=findViewById(R.id.LoginEmail);
        LoginPassword=findViewById(R.id.LoginPassword);
        ForgotPassword=findViewById(R.id.ForgotPassword);
        SignUPTv=findViewById(R.id.SignUPTv);
    }

    public void Register(View view) {
        startActivity(new Intent(Login.this, First_SignUp.class));
    }

    public void Login(View view) {
        startActivity(new Intent(Login.this, BottomNav.class));
    }


}