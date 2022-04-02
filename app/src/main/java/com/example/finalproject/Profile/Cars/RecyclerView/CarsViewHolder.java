package com.example.finalproject.Profile.Cars.RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

public class CarsViewHolder  extends RecyclerView.ViewHolder {
   public TextView carName;

    public CarsViewHolder(@NonNull View itemView) {
        super(itemView);
        carName = itemView.findViewById(R.id.CarNameTv);
    }
}
