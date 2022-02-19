package com.example.finalproject.NavBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.finalproject.Bills.Bills;
import com.example.finalproject.Home.Home;
import com.example.finalproject.Profile.Profile;
import com.example.finalproject.R;
import com.example.finalproject.UserQR.UserQR;

public class BottomNav extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        fragmentManager=getSupportFragmentManager();
        MeowBottomNavigation navBottom=findViewById(R.id.navBottom);
        navBottom.add(new MeowBottomNavigation.Model(0,R.drawable.person));
        navBottom.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        navBottom.add(new MeowBottomNavigation.Model(2,R.drawable.list));
        navBottom.add(new MeowBottomNavigation.Model(3,R.drawable.qr_code));
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
                         fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new Profile()).commit();
                     break;
                     case 1:
                        fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new Home()).commit();
                     break;
                     case 2:
                         fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new Bills()).commit();
                     break;
                     case 3:
                         fragmentManager.beginTransaction().replace(R.id.ContanierLayout,new UserQR()).commit();
                     break;
                 }
             }
         });

    }
}