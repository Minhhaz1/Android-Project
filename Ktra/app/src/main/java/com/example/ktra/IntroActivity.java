package com.example.ktra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends BaseActivity  {
    private Button loginBtn, signupBtn;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initView();
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,SignUpActivity.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser()!=null){
               startActivity(new Intent(IntroActivity.this,MainActivity.class));
            }else {
                startActivity(new Intent(IntroActivity.this,LoginActivity.class));

            }
            }
        });

    }

    private void initView() {
        signupBtn = findViewById(R.id.signupBtn);
        loginBtn = findViewById(R.id.loginBtn);
        tvSignUp = findViewById(R.id.tvSignup);
    }

//    @Override
//    public void onClick(View view) {
//        if(view == loginBtn){
//            if(mAuth.getCurrentUser()!=null){
//               startActivity(new Intent(IntroActivity.this,MainActivity.class));
//            }else {
//                startActivity(new Intent(IntroActivity.this,LoginActivity.class));
//
//            }
//        }
//        if(view == signupBtn){
//            startActivity(new Intent(IntroActivity.this,SignUpActivity.class));
//
//        }
//    }

}