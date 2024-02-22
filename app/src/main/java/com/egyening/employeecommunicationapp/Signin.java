package com.egyening.employeecommunicationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //User is signed in use an intent to move to another activity
        }
    }

    public void signin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Signin.this, "Authentication success. Use an intent to move to a new activity",
                                    Toast.LENGTH_SHORT).show();
                            //user has been signed in, use an intent to move to the next activity
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "signInWithEmail:failure",
                                    task.getException());
                            Toast.makeText(Signin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }

                    }
                });
    }

    public void signinButtonClicked( View view){
        EditText email = findViewById(R.id.signup_email);
        EditText password = findViewById(R.id.signup_password);

        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();

        signin(sEmail, sPassword);
    }
}