package com.example.finalproject.Bills;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BillsFragment extends Fragment {
    View view;
    RecyclerView billsRv;


    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mUserRef, billRef;
    FirebaseRecyclerAdapter<billModel, billViewHolder> adapter;
    FirebaseRecyclerOptions<billModel> options;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BillsFragment() {
    }

    public static BillsFragment newInstance(String param1, String param2) {
        BillsFragment fragment = new BillsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_bills, container, false);

        inti();
        LoadBills();
        return view;
    }

    private void inti() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        billRef = FirebaseDatabase.getInstance().getReference().child("Bills");
        billsRv=view.findViewById(R.id.billsRv);
        billsRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void LoadBills() {
        options = new FirebaseRecyclerOptions.Builder<billModel>().setQuery(billRef, billModel.class).build();
        adapter=new FirebaseRecyclerAdapter<billModel, billViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull billViewHolder holder, int position, @NonNull billModel model) {
                if (mUser.getUid().equals(model.getUserID())){
                    if (model.getStatus().equals("Success")){
                        holder.BillStatus.setText(model.getStatus());
                        holder.BillStatus.setTextColor(ContextCompat.getColor(view.getContext(), R.color.Green));
                        holder.BillName.setText(model.getUsername());
                        holder.BillDate.setText(model.getDate());
                        holder.BillPrice.setText(model.getPrice()+" L.E");
                    }else if (model.getStatus().equals("Failed")){
                        holder.BillStatus.setText(model.getStatus());
                        holder.BillStatus.setTextColor(ContextCompat.getColor(view.getContext(), R.color.Red));
                        holder.BillName.setText(model.getUsername());
                        holder.BillDate.setText(model.getDate());
                        holder.BillPrice.setText(model.getPrice()+" L.E");
                    }

                }else {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }

            @NonNull
            @Override
            public billViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.bill_item, parent, false);
                return new billViewHolder(view);
            }
        };
        adapter.startListening();
        billsRv.setAdapter(adapter);
    }
}