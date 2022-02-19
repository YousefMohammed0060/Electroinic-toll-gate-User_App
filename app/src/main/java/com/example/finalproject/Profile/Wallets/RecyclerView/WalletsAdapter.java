package com.example.finalproject.Profile.Wallets.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.ArrayList;

public class WalletsAdapter extends RecyclerView.Adapter<WalletsAdapter.WalletsHolder> {
    ArrayList<String>strings;

    public WalletsAdapter(ArrayList<String> strings) {
        this.strings = strings;
    }

    @NonNull
    @Override
    public WalletsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.walltes_item,parent,false);
        WalletsHolder holder=new WalletsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WalletsHolder holder, int position) {
        holder.WalletName.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class WalletsHolder extends RecyclerView.ViewHolder {
        TextView WalletName;
        public WalletsHolder(@NonNull View itemView) {
            super(itemView);
            WalletName=itemView.findViewById(R.id.WalletName);
        }
    }
}
