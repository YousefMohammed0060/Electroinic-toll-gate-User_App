package com.example.finalproject.Profile.Cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.R;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
    }

    public void AddNewCar(View view) {
        startActivity(new Intent(AddCarActivity.this, CarsActivity.class));
        finish();
    }
}