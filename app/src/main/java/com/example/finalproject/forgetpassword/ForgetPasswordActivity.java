package com.example.finalproject.forgetpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText inputEmail;
    Button sendBtn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        inputEmail = findViewById(R.id.email_forgetPassET);
        sendBtn = findViewById(R.id.sendbutton);
        mAuth = FirebaseAuth.getInstance();

    }

    public void sendButton(View view) {
        String Email = inputEmail.getText().toString();
        if (Email.isEmpty()) {

            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(ForgetPasswordActivity.this, "Please Check Your Email ", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(ForgetPasswordActivity.this, "Email Not Sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
}