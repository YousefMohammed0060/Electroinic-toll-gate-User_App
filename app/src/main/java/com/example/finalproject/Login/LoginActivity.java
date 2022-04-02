package com.example.finalproject.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.NavBar.BottomNavActivity;
import com.example.finalproject.R;
import com.example.finalproject.SignUp.RegisterActivity;
import com.example.finalproject.forgetpassword.ForgetPasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText LoginEmail,LoginPassword;
    TextView ForgotPassword,SignUPTv;

    FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        LoginEmail=findViewById(R.id.LoginEmail);
        LoginPassword=findViewById(R.id.LoginPassword);
        ForgotPassword=findViewById(R.id.ForgotPassword);
        SignUPTv=findViewById(R.id.SignUPTv);

        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(this);
        SignUPTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }



    public void Login(View view) {
        String Email=LoginEmail.getText().toString();
        String Password=LoginPassword.getText().toString();

        if(Email.isEmpty() || !Email.contains("@") ||!Email.contains(".")){
            showError(LoginEmail,"Email is not valid");
        }else if (Password.isEmpty() || Password.length()<6){
            showError(LoginPassword,"Password is too short");
        }else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait,While check your data");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mLoadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Login is successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this, BottomNavActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }else {
                        mLoadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showError(EditText field, String errorText) {
        field.setError(errorText);
        field.requestFocus();
    }

    public void forgetPassTV(View view) {
       startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
    }
}