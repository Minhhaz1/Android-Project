package com.example.ktra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends BaseActivity  {
    private Button loginBtn;
    private EditText eUser, ePass;
    private TextView tvSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String email = eUser.getText().toString();
                    String pass = ePass.getText().toString();
                    if(!email.isEmpty() && !pass.isEmpty()){
                        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                }else{
                                    Toast.makeText(LoginActivity.this, "Failed"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(LoginActivity.this, "Please fill", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }
    private void initView() {
        loginBtn = findViewById(R.id.loginBtn);
        eUser = findViewById(R.id.userEdt);
        ePass = findViewById(R.id.passEdt);
        tvSignUp = findViewById(R.id.tvSignup);
    }

//
}