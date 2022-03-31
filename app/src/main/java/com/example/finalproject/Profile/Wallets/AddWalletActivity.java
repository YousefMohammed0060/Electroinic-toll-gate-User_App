package com.example.finalproject.Profile.Wallets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.NavBar.BottomNavActivity;
import com.example.finalproject.R;
import com.example.finalproject.SignUp.SetupProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddWalletActivity extends AppCompatActivity {

    EditText NewWalletName, NewWalletBalance;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mWalletRef;
    ProgressDialog mLoadingBar;

    int WalletBalance = 0;
    String sWalletBalance,WalletName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
        init();
    }

    private void init() {
        NewWalletName = findViewById(R.id.NewWalletName);
        NewWalletBalance = findViewById(R.id.NewWalletBalance);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mWalletRef = FirebaseDatabase.getInstance().getReference().child("Wallets");
        mLoadingBar = new ProgressDialog(this);
    }

    public void AddNewWallet(View view) {
        WalletName = NewWalletName.getText().toString();
        sWalletBalance = NewWalletBalance.getText().toString();
        checkBalance();
        if (WalletName.isEmpty() || WalletName.length() < 3) {
            showError(NewWalletName, "Name is too short");
        } else if (sWalletBalance.isEmpty()) {
            showError(NewWalletBalance, "balance should be entered");
        } else if (WalletBalance<10) {
            showError(NewWalletBalance, "balance should be 10 and higher");
        }else {

            mLoadingBar.setTitle("Adding setup profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            HashMap hashMap = new HashMap();
            hashMap.put("UserID", mUser.getUid());
            hashMap.put("WalletName", WalletName);
            hashMap.put("WalletBalance", WalletBalance);


            mWalletRef.child(WalletName+mUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    mLoadingBar.dismiss();
                    startActivity(new Intent(AddWalletActivity.this, WalletsActivity.class));
                    Toast.makeText(AddWalletActivity.this, "Add wallet completed", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mLoadingBar.dismiss();
                    Toast.makeText(AddWalletActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

    }

    private void checkBalance() {
        try {
            WalletBalance=new Integer(sWalletBalance).intValue();
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, " enter numbers only", Toast.LENGTH_SHORT).show();
        }
    }


    private void showError(EditText field, String errorText) {
        field.setError(errorText);
        field.requestFocus();
    }
}