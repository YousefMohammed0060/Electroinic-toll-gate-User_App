package com.example.finalproject.Profile.Wallets;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

public class WalletsViewHolder extends RecyclerView.ViewHolder {
    TextView WalletName;
    public WalletsViewHolder(@NonNull View itemView) {
        super(itemView);
        WalletName=itemView.findViewById(R.id.WalletName);
    }
}
