package com.egyening.employeecommunicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Landing extends AppCompatActivity {
 ProgressBar progressBar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        progressBar  = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getUserDetails();
                finish();
            }
        },5000);
    }

    public void getUserDetails(){
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Intent intent=new Intent(Landing.this, Signin.class);
            intent.putExtra("email",currentUser.getEmail());
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(Landing.this, SignupSignin.class);
            startActivity(intent);
        }

    }
}