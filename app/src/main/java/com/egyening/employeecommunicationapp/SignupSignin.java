package com.egyening.employeecommunicationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SignupSignin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText firstnametxt;

    EditText lastnametxt;

    EditText emailtxt;

    EditText passwordtxt;
    Button signupbtn;
    //String staffId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_signin);
        mAuth = FirebaseAuth.getInstance();

        firstnametxt =findViewById(R.id.firstname);
        lastnametxt = findViewById(R.id.lastname);
        emailtxt= findViewById(R.id.signup_email);
        passwordtxt=findViewById(R.id.signup_password);
        signupbtn=findViewById(R.id.signup);

    }



    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //User is signed in use an intent to move to another activity
        }
    }*/

    public void signup(String firstName,String lastName,String email,String password,String staffId){
        Map<String, Object> user = new HashMap<>();
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        user.put("password", password);
        user.put("staffId", staffId);
        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        mAuth.createUserWithEmailAndPassword(email, password);
                        Intent intent =new Intent(SignupSignin.this, MainActivity.class);
                        startActivity(intent);

                        Log.d("SignupSignin", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("SignupSignin", "Error adding document", e);
                    }
                });
    }





    public void signupButtonClicked( View view){
      String  firstName=firstnametxt.getText().toString();
        String lastName=lastnametxt.getText().toString();
        String email=emailtxt.getText().toString();
        String password=passwordtxt.getText().toString();
        String staffId=UUID.randomUUID().toString().replace("-", "");

        signup(firstName, lastName,email,password,staffId);
    }


}