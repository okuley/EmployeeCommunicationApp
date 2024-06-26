package com.egyening.employeecommunicationapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egyening.employeecommunicationapp.adapter.StaffDirectoryRecyclerAdapter;
import com.egyening.employeecommunicationapp.utils.firebaseUtils;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;


public class StaffDirectoryFragment extends Fragment {
    RecyclerView recyclerView;
    StaffDirectoryRecyclerAdapter adapter;

    public StaffDirectoryFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_staff_directory, container, false);
        recyclerView = view.findViewById(R.id.staff_directory_fragment_recycler_view);
        setupRecyclerView();
        return view;
    }

    public void  setupRecyclerView(){

        Query query=firebaseUtils.allUserCollectionReference()
                .orderBy("email",Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Staff> options=new FirestoreRecyclerOptions.Builder<Staff>()
                .setQuery(query, Staff.class).build();

        adapter= new StaffDirectoryRecyclerAdapter(options,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onStart(){
        super.onStart();
        if(adapter!=null){
            adapter.startListening();
        }

    }
    @Override
    public void onStop(){
        super.onStop();
        if(adapter!=null){
            adapter.stopListening();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
}