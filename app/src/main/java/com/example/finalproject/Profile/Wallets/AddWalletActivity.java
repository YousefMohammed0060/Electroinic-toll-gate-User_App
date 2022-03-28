package com.example.finalproject.Profile.Wallets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.R;

public class AddWalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
    }

    public void AddNewWallet(View view) {
        startActivity(new Intent(AddWalletActivity.this, WalletsActivity.class));
        finish();
    }
}