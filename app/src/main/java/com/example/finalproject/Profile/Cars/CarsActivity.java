package com.example.finalproject.Profile.Cars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Profile.Cars.RecyclerView.CarsModel;

import com.example.finalproject.Profile.Cars.RecyclerView.CarsViewHolder;
import com.example.finalproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CarsActivity extends AppCompatActivity {
    RecyclerView CarsRV;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mUserRef, carRef;
    FirebaseRecyclerAdapter<CarsModel, CarsViewHolder> carAdapter;
    FirebaseRecyclerOptions<CarsModel> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        CarsRV=findViewById(R.id.CarsRV);
        CarsRV.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        carRef = FirebaseDatabase.getInstance().getReference().child("Cars");
        loadCars();

    }

    private void loadCars() {
        options = new FirebaseRecyclerOptions.Builder<CarsModel>().setQuery(carRef, CarsModel.class).build();
        carAdapter = new FirebaseRecyclerAdapter<CarsModel, CarsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CarsViewHolder holder,  int position, @NonNull CarsModel model) {
                if (model.getUserID().equals(mUser.getUid())){
                    holder.carName.setText(" "+ model.getCarModel());
                }else {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }

            }

            @NonNull
            @Override
            public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cars_item,parent,false);

                return new CarsViewHolder(view);
            }
        };
        carAdapter.startListening();
        CarsRV.setAdapter(carAdapter);
    }

    public void AddCar(View view) {

    }
}