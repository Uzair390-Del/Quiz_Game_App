package com.example.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView mail;
    TextView password;
    Button signIn;
    SignInButton signInGoogle;
    TextView signUp;
    TextView forgetPassword;
    ProgressBar progressBar;
    FirebaseAuth auth=FirebaseAuth.getInstance();
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
            progressBar=findViewById(R.id.progressBarSignIn);
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String userName=mail.getText().toString();
                    String userPassword=password.getText().toString();
                    signInFirebase(userName,userPassword);


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
    public void signInFirebase(String userName, String userPassord) {
        progressBar.setVisibility(View.VISIBLE);
        signIn.setClickable(false);
        auth.signInWithEmailAndPassword(userName, userPassord).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Sign In Successfull!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Sign In is not Successfull!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=auth.getCurrentUser();
        if (user!=null)
        {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();

        }
    }
}