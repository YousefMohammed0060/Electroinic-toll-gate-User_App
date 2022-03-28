package com.example.finalproject.Profile.Cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.Profile.Cars.RecyclerView.CarsAdapter;
import com.example.finalproject.R;

import java.util.ArrayList;

public class CarsActivity extends AppCompatActivity {
    RecyclerView CarsRV;
    ArrayList<String> CarsName=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        CarsName.add(" Car 1");
        CarsName.add(" Car 2");
        CarsName.add(" Car 3");
        CarsName.add(" Car 4");
        CarsName.add(" Car 5");
        CarsRV=findViewById(R.id.CarsRV);
        CarsAdapter carsAdapter=new CarsAdapter(CarsName);
        CarsRV.setAdapter(carsAdapter);
        RecyclerView.LayoutManager LOM=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        CarsRV.setLayoutManager(LOM);
    }

    public void AddCar(View view) {
        startActivity(new Intent(CarsActivity.this, AddCarActivity.class));
        finish();
    }
}