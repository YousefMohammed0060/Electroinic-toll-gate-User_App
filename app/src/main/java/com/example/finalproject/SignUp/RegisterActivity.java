package com.example.finalproject.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Login.LoginActivity;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText NewEmail, NewPassword, ConfirmNewPassword;
    TextView alreadyHaveAccount;

    FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inti();
    }

    private void inti() {
        NewEmail = findViewById(R.id.NewEmail);
        NewPassword = findViewById(R.id.NewPassword);
        ConfirmNewPassword = findViewById(R.id.ConfirmNewPassword);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(this);
    }

    public void Register(View view) {
        String Email = NewEmail.getText().toString();
        String Password = NewPassword.getText().toString();
        String confirmPassword = ConfirmNewPassword.getText().toString();

        if (Email.isEmpty() || !Email.contains("@") || !Email.contains(".")) {
            showError(NewEmail, "Email is not valid");
        } else if (Password.isEmpty() || Password.length() < 6) {
            showError(NewPassword, "Password is too short");
        } else if (!confirmPassword.equals(Password)) {
            showError(ConfirmNewPassword, "Password did not match!");
        } else {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait,While check your data");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mLoadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, SetupProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        mLoadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showError(EditText field, String errorText) {
        field.setError(errorText);
        field.requestFocus();
    }
}