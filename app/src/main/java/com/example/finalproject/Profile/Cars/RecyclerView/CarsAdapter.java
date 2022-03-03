package com.example.finalproject.Profile.Cars.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsHolder> {
    ArrayList<String>strings;

    public CarsAdapter(ArrayList<String> strings) {
        this.strings = strings;
    }

    @NonNull
    @Override
    public CarsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.walltes_item,parent,false);
        CarsHolder holder=new CarsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarsHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class CarsHolder extends RecyclerView.ViewHolder {
        public CarsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
