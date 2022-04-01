package com.example.finalproject.Profile.Wallets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WalletsActivity extends AppCompatActivity {

    RecyclerView WalletsRv;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mUserRef, walletRef;
    FirebaseRecyclerAdapter<WalletsModel, WalletsViewHolder> adapter;
    FirebaseRecyclerOptions<WalletsModel> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets);
        inti();
    LoadWallets();
    }

    private void inti() {
        WalletsRv=findViewById(R.id.WalletsRv);
        WalletsRv.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        walletRef = FirebaseDatabase.getInstance().getReference().child("Wallets");
    }

    private void LoadWallets() {
        options = new FirebaseRecyclerOptions.Builder<WalletsModel>().setQuery(walletRef, WalletsModel.class).build();
        adapter=new FirebaseRecyclerAdapter<WalletsModel, WalletsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull WalletsViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull WalletsModel model) {
                if (model.getUserID().equals(mUser.getUid())){
                    holder.WalletName.setText(" "+model.WalletName);
                }else {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(WalletsActivity.this,WalletDetailsActivity.class);
                        intent.putExtra("walletKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public WalletsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.walltes_item, parent, false);
                return new WalletsViewHolder(view);
            }
        };
        adapter.startListening();
        WalletsRv.setAdapter(adapter);
    }


    public void AddWallet(View view) {
        startActivity(new Intent(WalletsActivity.this, AddWalletActivity.class));
        finish();
    }
}