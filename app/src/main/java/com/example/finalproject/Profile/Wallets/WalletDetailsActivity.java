package com.example.finalproject.Profile.Wallets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletDetailsActivity extends AppCompatActivity {

    EditText WalletID,EditWalletName,EditWalletBalance;
    String walletKey;
    DatabaseReference walletRef, mRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_details);
        init();
    }

    private void init() {
        walletKey = getIntent().getStringExtra("walletKey");
        WalletID=findViewById(R.id.WalletID);
        EditWalletName=findViewById(R.id.EditWalletName);
        EditWalletBalance=findViewById(R.id.EditWalletBalance);
        WalletID.setEnabled(false);
        walletRef = FirebaseDatabase.getInstance().getReference().child("Wallets").child(walletKey);
        mRef = FirebaseDatabase.getInstance().getReference().child("Wallets");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        LoadWallet();
    }

    private void LoadWallet() {
        walletRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    WalletID.setText("Wallet ID: "+walletKey);
                    EditWalletName.setText(snapshot.child("WalletName").getValue().toString());
                    EditWalletBalance.setText(snapshot.child("WalletBalance").getValue().toString());
                }else {
                    Toast.makeText(WalletDetailsActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WalletDetailsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void EditWallet(View view) {
    }
}