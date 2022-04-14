package com.example.finalproject.Profile.Cars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.Profile.Wallets.WalletDetailsActivity;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CarDetailsActivity extends AppCompatActivity {

    EditText EditCarModel,EditCarNumber,EditCarLetters,carID;
    String carKey;

    DatabaseReference carRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        inti();
    }

    private void inti() {
        EditCarModel=findViewById(R.id.EditCarModel);
        EditCarNumber=findViewById(R.id.EditCarNumber);
        EditCarLetters=findViewById(R.id.EditCarLetters);
        carID=findViewById(R.id.carID);
        carID.setEnabled(false);
        carKey= getIntent().getStringExtra("carKey");

        carRef = FirebaseDatabase.getInstance().getReference().child("Cars").child(carKey);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        LoadCar();

    }

    private void LoadCar() {
        carRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    EditCarModel.setText(snapshot.child("CarModel").getValue().toString());
                    EditCarNumber.setText(snapshot.child("CarNumbers").getValue().toString());
                    EditCarLetters.setText(snapshot.child("CarLetters").getValue().toString());
                    carID.setText("Car Id: "+snapshot.child("UserID").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void EditCar(View view) {
        HashMap hashMap=new HashMap();
        hashMap.put("UserID",carKey);
        hashMap.put("CarModel",EditCarModel.getText().toString());
        hashMap.put("CarNumbers",EditCarNumber.getText().toString());
        hashMap.put("CarLetters",EditCarLetters.getText().toString());
        carRef.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    Toast.makeText(CarDetailsActivity.this, "Car Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CarDetailsActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}