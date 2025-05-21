package com.example.quizgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_Up_Page extends AppCompatActivity {
    EditText mail;
    EditText password;
    Button signUp;
    ProgressBar progressBar;
    FirebaseAuth auth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            mail= findViewById(R.id.editTextSignUpEmail);
            password= findViewById(R.id.editTextSignUpPassword);
            signUp= findViewById(R.id.buttonSignUpSignUp);
            progressBar= findViewById(R.id.progressBarSignUp);
            progressBar.setVisibility(View.INVISIBLE);
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signUp.setClickable(false);

                    String userName=mail.getText().toString();
                    String userPassword=password.getText().toString();
                    signUpFirebase(userName,userPassword);

                }
            });

            return insets;
        });

    }
    public void signUpFirebase(String userName, String userPassord){
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(userName,userPassord).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Sign_Up_Page.this, "Your Account is Created!", Toast.LENGTH_LONG).show();
                    finish();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(Sign_Up_Page.this, "There is a problem, Please try again later!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}