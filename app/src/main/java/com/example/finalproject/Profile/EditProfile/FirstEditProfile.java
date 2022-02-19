package com.example.finalproject.Profile.EditProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.finalproject.R;

public class FirstEditProfile extends AppCompatActivity {

    EditText EditName,EditEmail,EditPassword,EditNID,EditPhoneNumber,EditCity,OldPassword;
    ImageView EditProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        inti();
    }

    private void inti() {
        EditName=findViewById(R.id.EditName);
        EditEmail=findViewById(R.id.EditEmail);
        EditPassword=findViewById(R.id.EditPassword);
        EditNID=findViewById(R.id.EditNID);
        EditPhoneNumber=findViewById(R.id.EditPhoneNumber);
        EditCity=findViewById(R.id.EditCity);
        OldPassword=findViewById(R.id.OldPassword);
        EditProfileImage=findViewById(R.id.EditProfileImage);
    }


    public void Edit(View view) {
    }

    public void EditImage(View view) {
    }
}