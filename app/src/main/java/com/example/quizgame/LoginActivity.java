package com.example.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.common.SignInButton;

public class LoginActivity extends AppCompatActivity {
    TextView mail;
    TextView password;
    Button signIn;
    SignInButton signInGoogle;
    TextView signUp;
    TextView forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            mail= findViewById(R.id.editTextLoginEmail);
            password= findViewById(R.id.editTextLoginPassword);
            signIn= findViewById(R.id.buttonLoginSignIn);
            signInGoogle= findViewById(R.id.buttonLoginGoogleSignIn);
            signUp= findViewById(R.id.textViewLoginSignUp);
            forgetPassword= findViewById(R.id.textViewLoginForgetPassword);
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            signInGoogle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(LoginActivity.this,Sign_Up_Page.class);
                    startActivity(intent);

                }
            });
            forgetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return insets;
        });
    }
}