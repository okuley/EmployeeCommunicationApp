package com.egyening.employeecommunicationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.egyening.employeecommunicationapp.adapter.SearchUserAdapter;
import com.egyening.employeecommunicationapp.utils.firebaseUtils;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SearchUser extends AppCompatActivity {
EditText searchInput;

ImageButton searchBtn;

ImageButton backbtn;
RecyclerView recyclerView;

SearchUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        searchInput=findViewById(R.id.search_user_edittext);
        searchBtn=findViewById(R.id.search_user_button);
        backbtn=findViewById(R.id.searchbackbutton);
        recyclerView=findViewById(R.id.user_result_recyclerview);

        searchInput.requestFocus();

        backbtn.setOnClickListener(v->{
          onBackPressed();

        });

        searchBtn.setOnClickListener(v->{
            String searchInputData=searchInput.getText().toString();
            if(searchInputData.isEmpty()){
                searchInput.setError("Please enter lastname");
                return;
            }else {
                setSearchRecyclerView(searchInputData);
            }
        });
    }

    public void setSearchRecyclerView( String searchInputData){

        Query query= firebaseUtils.allUserCollectionReference()
                .whereGreaterThanOrEqualTo("email",searchInputData);
               // .whereGreaterThanOrEqualTo("Lastname",searchInputData);

        FirestoreRecyclerOptions<Staff> options=new FirestoreRecyclerOptions.Builder<Staff>()
                .setQuery(query, Staff.class).build();

        adapter= new SearchUserAdapter(options,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }

    @Override
    protected void onStart(){
        super.onStart();
        if(adapter!=null){
            adapter.startListening();
        }

    }
    @Override
    protected void onStop(){
        super.onStop();
        if(adapter!=null){
            adapter.stopListening();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(adapter!=null){
            adapter.startListening();
        }
    }
}