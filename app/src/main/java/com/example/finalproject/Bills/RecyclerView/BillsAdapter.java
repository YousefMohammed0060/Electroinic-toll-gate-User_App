package com.example.finalproject.Bills.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.ArrayList;

public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.BillsHolder> {

    ArrayList<Model> bills=new ArrayList<>();
    int red =  Color.rgb(255, 0, 0);
    int green =  Color.rgb(0, 255, 0);
    public BillsAdapter(ArrayList<Model> bills) {
        this.bills = bills;
    }

    @NonNull
    @Override
    public BillsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_item,parent,false);
        BillsHolder holder=new BillsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillsHolder billsHolder, int position) {
        if (bills.get(position).Status == "Failed"){
            billsHolder.BillStatus.setTextColor(red);
            billsHolder.BillStatus.setText(bills.get(position).Status);
        }else {
            billsHolder.BillStatus.setTextColor(green);
            billsHolder.BillStatus.setText(bills.get(position).Status);
        }
        billsHolder.BillName.setText(bills.get(position).Name);
        billsHolder.BillDate.setText(bills.get(position).Date);
        billsHolder.BillTime.setText(bills.get(position).Time);
        billsHolder.BillPrice.setText(bills.get(position).Price);
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    class BillsHolder extends RecyclerView.ViewHolder{
        TextView BillStatus,BillName,BillDate,BillTime,BillPrice;
        public BillsHolder(@NonNull View itemView) {
            super(itemView);
            BillStatus=itemView.findViewById(R.id.BillStatus);
            BillName=itemView.findViewById(R.id.BillName);
            BillDate=itemView.findViewById(R.id.BillDate);
            BillTime=itemView.findViewById(R.id.BillTime);
            BillPrice=itemView.findViewById(R.id.BillPrice);
        }
    }
}
