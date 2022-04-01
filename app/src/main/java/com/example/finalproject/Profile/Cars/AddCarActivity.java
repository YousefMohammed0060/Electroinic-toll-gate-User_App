package com.example.finalproject.Profile.Cars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.Home.HomeFragment;
import com.example.finalproject.NavBar.BottomNavActivity;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddCarActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mAddCar;
    FirebaseUser mUser;
    ProgressDialog mLoadingBar;
    EditText carModel, carNumber, carLetters;
    String carModelStr, carNumberStr, carLetterStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        carModel = findViewById(R.id.NewCarModel);
        carNumber = findViewById(R.id.NewCarNumber);
        carLetters = findViewById(R.id.NewCarLetters);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mAddCar = FirebaseDatabase.getInstance().getReference().child("Cars");
        mLoadingBar = new ProgressDialog(this);

    }

    public void AddNewCar(View view) {
        carModelStr = carModel.getText().toString();
        carNumberStr = carNumber.getText().toString();
        carLetterStr = carLetters.getText().toString();
        // validation
        if (carModelStr.isEmpty()) {
            showError(carModel, "Please Enter your car Model");
        } else if (carNumberStr.isEmpty() || carNumberStr.length() < 4) {
            showError(carNumber, "Please Enter a valid car number between 2 to 4 numbers");
        } else if (carLetterStr.isEmpty() || carLetterStr.length() < 3) {
            showError(carLetters, "Please Enter a valid car letters between 2 to 4 letters");
        } else {
            mLoadingBar.setTitle("Adding setup profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            HashMap hashMap = new HashMap();
            hashMap.put("UserID", mUser.getUid());
            hashMap.put("CarModel", carModelStr);
            hashMap.put("CarNumbers", carNumberStr);
            hashMap.put("CarLetters", carLetterStr);

            mAddCar.child(mUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    mLoadingBar.dismiss();
                    startActivity(new Intent(AddCarActivity.this, BottomNavActivity.class));
                    Toast.makeText(AddCarActivity.this, "Add wallet completed", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mLoadingBar.dismiss();
                    Toast.makeText(AddCarActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                    finish();
                }
            });
        }

    }

    private void showError(EditText field, String s) {
        field.setError(s);
        field.requestFocus();
    }
}
