package com.egyening.employeecommunicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ChatFragment chatFragment;

    StaffDirectoryFragment staffDirectoryFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatFragment = new ChatFragment();
        staffDirectoryFragment =new StaffDirectoryFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }
}