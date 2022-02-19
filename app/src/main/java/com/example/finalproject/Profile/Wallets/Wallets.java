package com.example.finalproject.Profile.Wallets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.finalproject.Profile.Wallets.RecyclerView.WalletsAdapter;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class Wallets extends AppCompatActivity {

    RecyclerView WalletsRv;
    ArrayList<String> strings=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets);
        strings.add("Wallet1");
        strings.add("Wallet2");
        strings.add("Wallet3");
        strings.add("Wallet4");
        WalletsRv=findViewById(R.id.WalletsRv);
        WalletsAdapter walletsAdapter=new WalletsAdapter(strings);
        WalletsRv.setAdapter(walletsAdapter);
        RecyclerView.LayoutManager LOM=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        WalletsRv.setLayoutManager(LOM);

    }
}