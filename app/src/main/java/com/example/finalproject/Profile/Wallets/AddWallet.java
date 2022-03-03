package com.example.finalproject.Profile.Wallets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.Profile.Cars.AddCar;
import com.example.finalproject.Profile.Cars.Cars;
import com.example.finalproject.R;

public class AddWallet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
    }

    public void AddNewWallet(View view) {
        startActivity(new Intent(AddWallet.this, Wallets.class));
        finish();
    }
}