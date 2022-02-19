package com.example.finalproject.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.finalproject.R;

public class First_SignUp extends AppCompatActivity {

    EditText NewName,NewEmail,NewPassword,ConfirmNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        inti();
    }

    private void inti() {
        NewName=findViewById(R.id.NewName);
        NewEmail=findViewById(R.id.LoginEmail);
        NewPassword=findViewById(R.id.LoginPassword);
        ConfirmNewPassword=findViewById(R.id.ConfirmNewPassword);
    }

    public void Next(View view) {
        startActivity(new Intent(First_SignUp.this,Second_SignUp.class));
    }
}