package com.egyening.employeecommunicationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton searchButton;
    ChatFragment chatFragment;

    StaffDirectoryFragment staffDirectoryFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatFragment = new ChatFragment();
        staffDirectoryFragment =new StaffDirectoryFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        searchButton =findViewById(R.id.searchbutton);

        searchButton.setOnClickListener((v)->{
            startActivity(new Intent(MainActivity.this,SearchUser.class));
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.chat){
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,chatFragment).commit();
                }
                if(item.getItemId()==R.id.staff_directory){
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,staffDirectoryFragment).commit();
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.chat);

    }
}