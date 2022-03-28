package com.example.finalproject.NavBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.finalproject.Bills.BillsFragment;
import com.example.finalproject.Home.HomeFragment;
import com.example.finalproject.Login.LoginActivity;
import com.example.finalproject.Profile.ProfileFragment;
import com.example.finalproject.R;
import com.example.finalproject.UserQR.User_qrFragment;
import com.google.firebase.auth.FirebaseAuth;

public class BottomNavActivity extends AppCompatActivity{

    FragmentManager fragmentManager;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new HomeFragment()).commit();
        MeowBottomNavigation navBottom=findViewById(R.id.navBottom);
        navBottom.add(new MeowBottomNavigation.Model(0,R.drawable.person));
        navBottom.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        navBottom.add(new MeowBottomNavigation.Model(2,R.drawable.list));
        navBottom.add(new MeowBottomNavigation.Model(3,R.drawable.qr_code));
        navBottom.add(new MeowBottomNavigation.Model(4,R.drawable.logout));
        mAuth=FirebaseAuth.getInstance();
        navBottom.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });
         navBottom.setOnShowListener(new MeowBottomNavigation.ShowListener() {
             @Override
             public void onShowItem(MeowBottomNavigation.Model item) {
                 switch (item.getId()){
                     case 0:
                         fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new ProfileFragment()).commit();
                     break;
                     case 1:
                        fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new HomeFragment()).commit();
                     break;
                     case 2:
                         fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new BillsFragment()).commit();
                     break;
                     case 3:
                         fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new User_qrFragment()).commit();
                     break;
                     case 4:
                         mAuth.signOut();
                         startActivity(new Intent(BottomNavActivity.this, LoginActivity.class));
                         finish();
                     break;
                 }
             }
         });

    }



}