package com.egyening.employeecommunicationapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.egyening.employeecommunicationapp.adapter.NewsAndUpdatesRecyclerAdapter;
import com.egyening.employeecommunicationapp.utils.firebaseUtils;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class NewsAndUpdatesFragment extends Fragment {
RecyclerView recyclerView;

NewsAndUpdatesRecyclerAdapter adapter;

ImageButton createUpdatebutton;
    public NewsAndUpdatesFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_news_and_updates, container, false);
        recyclerView = view.findViewById(R.id.updates_fragment_recycler_view);
        setupRecyclerView();
        createUpdatebutton= view.findViewById(R.id.createUpdate);
        createUpdatebutton.setOnClickListener((v)->{
            startActivity(new Intent(getActivity(),NewsAndUpdatesActivity.class));
        });
        return view;
    }

    public void  setupRecyclerView(){

        Query query=firebaseUtils.allUpdatesCollectionReference()
                .orderBy("timestamp",Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<NewsAndUpdates> options=new FirestoreRecyclerOptions.Builder<NewsAndUpdates>()
                .setQuery(query, NewsAndUpdates.class).build();

        adapter= new NewsAndUpdatesRecyclerAdapter(options,getContext());
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