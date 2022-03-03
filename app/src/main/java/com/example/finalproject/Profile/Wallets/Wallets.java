package com.example.finalproject.Profile.Wallets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.Profile.Cars.AddCar;
import com.example.finalproject.Profile.Cars.Cars;
import com.example.finalproject.Profile.Wallets.RecyclerView.WalletsAdapter;
import com.example.finalproject.R;

import java.util.ArrayList;

public class Wallets extends AppCompatActivity {

    RecyclerView WalletsRv;
    ArrayList<String> strings=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets);
        WalletsRv=findViewById(R.id.WalletsRv);
        WalletsAdapter walletsAdapter=new WalletsAdapter(strings);
        WalletsRv.setAdapter(walletsAdapter);
        RecyclerView.LayoutManager LOM=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        WalletsRv.setLayoutManager(LOM);

    }


    public void AddWallet(View view) {
        startActivity(new Intent(Wallets.this, AddWallet.class));
        finish();
    }
}