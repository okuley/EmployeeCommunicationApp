package com.egyening.employeecommunicationapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.egyening.employeecommunicationapp.ChatActivity;
import com.egyening.employeecommunicationapp.R;
import com.egyening.employeecommunicationapp.NewsAndUpdates;
import com.egyening.employeecommunicationapp.utils.AndroidUtils;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NewsAndUpdatesRecyclerAdapter  extends FirestoreRecyclerAdapter<NewsAndUpdates, NewsAndUpdatesRecyclerAdapter.NewsAndUpdatesViewHolder> {
    Context context;

    public NewsAndUpdatesRecyclerAdapter(@NonNull FirestoreRecyclerOptions<NewsAndUpdates> options, Context context) {
        super(options);
        this. context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsAndUpdatesViewHolder holder, int position, @NonNull NewsAndUpdates model) {
        holder.messagetxt.setText(model.getMessage());
        holder.sender.setText(model.getSenderId());
        holder.time.setText(model.getTimestamp());
        

    }

    @NonNull
    @Override
    public NewsAndUpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_and_updates_row,parent,false);
        return new NewsAndUpdatesViewHolder(view);
    }

    class NewsAndUpdatesViewHolder extends RecyclerView.ViewHolder{
        TextView messagetxt;
        TextView sender;
        TextView time;
        
        public NewsAndUpdatesViewHolder(@NonNull View itemView) {
            super(itemView);
            messagetxt= itemView.findViewById(R.id.update);
            sender= itemView.findViewById(R.id.sender);
            time=itemView.findViewById(R.id.time);
            

        }
    }
}
