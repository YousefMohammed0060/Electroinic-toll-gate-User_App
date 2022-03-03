package com.example.finalproject.Profile.EditProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class EditProfile extends AppCompatActivity implements IPickResult {

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
        PickImageDialog.build(new PickSetup()).show(this);
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            EditProfileImage.setImageBitmap(r.getBitmap());

        } else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}