package com.example.e_voting_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        loginButton = findViewById(R.id.loginButton);
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String pass = password.getText().toString();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please enter valid email/password details", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    Toast.makeText(MainActivity2.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Sign in failed, attempt to create a new account
                                    mAuth.createUserWithEmailAndPassword(email, pass)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        // Account creation success
                                                        Toast.makeText(MainActivity2.this, "Account Created", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        // Account creation failed
                                                        Toast.makeText(MainActivity2.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            }
                        });
            }
        });

    }
}