package com.example.finalproject.SignUp;
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

public class SetupProfileActivity extends AppCompatActivity{


        private static final int REQUEST_CODE = 101;
        ImageView NemProfileImage;
        EditText NewName,NewCity,NewPhoneNumber,NewNID;

        Uri imageUri;
        FirebaseAuth mAuth;
        FirebaseUser mUser;
        DatabaseReference mRef;
        StorageReference storageRef;
        ProgressDialog mLoadingBar;
        String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);
        inti();
    }

    private void inti() {
        NewName=findViewById(R.id.NewName);
        NemProfileImage=findViewById(R.id.NemProfileImage);
        NewCity=findViewById(R.id.NewCity);
        NewPhoneNumber=findViewById(R.id.NewPhoneNumber);
        NewNID=findViewById(R.id.NewNID);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");
        storageRef = FirebaseStorage.getInstance().getReference().child("ProfileImages");
        mLoadingBar=new ProgressDialog(this);
        password=getIntent().getStringExtra("password");
    }

    public void UploadImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void setup(View view) {
        String Username = NewName.getText().toString();
        String City = NewCity.getText().toString();
        String Phone = NewPhoneNumber.getText().toString();
        String NID = NewNID.getText().toString();

        if (Username.isEmpty() || Username.length() < 3) {
            showError(NewName, "Username is not valid");
        } else if (City.isEmpty() || City.length() < 3) {
            showError(NewCity, "City is not valid");
        } else if (Phone.isEmpty() || Phone.length() < 11) {
            showError(NewPhoneNumber, "Phone number is not valid");
        } else if (NID.isEmpty() || NID.length() < 14) {
            showError(NewNID, "National ID is not valid");
        } else if (imageUri == null) {
            Toast.makeText(this, "Please select your image", Toast.LENGTH_SHORT).show();
        } else {

            mLoadingBar.setTitle("Adding setup profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            storageRef.child(mUser.getUid()).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        storageRef.child(mUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                HashMap hashMap=new HashMap();
                                hashMap.put("username",Username);
                                hashMap.put("userId",mUser.getUid());
                                hashMap.put("email",mUser.getEmail());
                                hashMap.put("password",password);
                                hashMap.put("city",City);
                                hashMap.put("phone",Phone);
                                hashMap.put("nationalID",NID);
                                hashMap.put("profileImage",uri.toString());


                                mRef.child(mUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        startActivity(new Intent(SetupProfileActivity.this, BottomNavActivity.class));
                                        mLoadingBar.dismiss();
                                        Toast.makeText(SetupProfileActivity.this, "Setup profile completed", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        mLoadingBar.dismiss();
                                        Toast.makeText(SetupProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }else {
                        mLoadingBar.dismiss();
                        Toast.makeText(SetupProfileActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            NemProfileImage.setImageURI(imageUri);
        }
    }

    private void showError(EditText field, String errorText) {
        field.setError(errorText);
        field.requestFocus();
    }
}