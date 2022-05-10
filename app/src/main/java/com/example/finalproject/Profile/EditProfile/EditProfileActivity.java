package com.example.finalproject.Profile.EditProfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    EditText EditName,EditNID,EditPhoneNumber,EditCity;
    ImageView EditProfileImage;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        inti();
    }

    private void inti() {
        EditName=findViewById(R.id.EditName);
        EditNID=findViewById(R.id.EditNID);
        EditPhoneNumber=findViewById(R.id.EditPhoneNumber);
        EditCity=findViewById(R.id.EditCity);
        EditProfileImage=findViewById(R.id.EditProfileImage);

        EditNID.setEnabled(false);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mUserRef= FirebaseDatabase.getInstance().getReference().child("Users");

        LoadUser();
    }

    private void LoadUser() {
        mUserRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String profileImageUrl=snapshot.child("profileImage").getValue().toString();
                    String City=snapshot.child("city").getValue().toString();
                    String Phone=snapshot.child("phone").getValue().toString();
                    String NID=snapshot.child("nationalID").getValue().toString();
                    String Username=snapshot.child("username").getValue().toString();

                    Picasso.get().load(profileImageUrl).into(EditProfileImage);
                    EditName.setText(Username);
                    EditCity.setText(City);
                    EditPhoneNumber.setText(Phone);
                    EditNID.setText(NID);

                }else {
                    Toast.makeText(EditProfileActivity.this, "Data not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void Edit(View view) {

    }

    public void EditImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            EditProfileImage.setImageURI(imageUri);
        }
    }


}