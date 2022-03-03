package com.example.finalproject.SignUp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalproject.Login.Login;
import com.example.finalproject.NavBar.BottomNav;
import com.example.finalproject.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class Second_SignUp extends AppCompatActivity implements IPickResult {

    ImageView NemProfileImage;
    EditText NewCity,NewPhoneNumber,NewNID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sign_up);
        inti();
    }

    private void inti() {
        NemProfileImage=findViewById(R.id.NemProfileImage);
        NewCity=findViewById(R.id.NewCity);
        NewPhoneNumber=findViewById(R.id.NewNID);
        NewNID=findViewById(R.id.NewNID);

    }

    public void UploadImage(View view) {
        PickImageDialog.build(new PickSetup()).show(this);
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            NemProfileImage.setImageBitmap(r.getBitmap());

        } else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void SignUp(View view) {
        startActivity(new Intent(Second_SignUp.this, BottomNav.class));
    }
}