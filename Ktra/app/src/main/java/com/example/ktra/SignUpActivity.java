package com.example.ktra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.concurrent.Executor;

public class SignUpActivity extends BaseActivity  {
    private Button signupBtn;
    private EditText eUser, ePass;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String email = eUser.getText().toString();
                    String pass = ePass.getText().toString();
                    if(pass.length()<6){
                        Toast.makeText(SignUpActivity.this, "Your password must be 6 character", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener( SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isComplete()) {
                                Log.i("Unil", "onComplete: ");
                                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                            } else {
                                Log.i("Unil", "Failure:" + task.getException());
                                Toast.makeText(SignUpActivity.this, "Auth Failure", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

        });
    }

    private void initView() {
        signupBtn = findViewById(R.id.signupBtn);
        eUser = findViewById(R.id.userEdt);
        ePass = findViewById(R.id.passEdt);
        tvLogin = findViewById(R.id.tvLogin);
    }

//    @Override
//    public void onClick(View view) {
//         if (view == signupBtn) {
//            String email = eUser.getText().toString();
//            String pass = ePass.getText().toString();
//            if(pass.length()<6){
//                Toast.makeText(this, "Your password must be 6 character", Toast.LENGTH_SHORT).show();
//            }
//            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener( SignUpActivity.this, new OnCompleteListener<AuthResult>() {
//
//                @Override
//                public void onComplete(Task<AuthResult> task) {
//                    if (task.isComplete()) {
//                        Log.i("Unil", "onComplete: ");
//                        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
//                    } else {
//                        Log.i("Unil", "Failure:" + task.getException());
//                        Toast.makeText(SignUpActivity.this, "Auth Failure", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
}
