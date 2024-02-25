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
import com.egyening.employeecommunicationapp.Staff;
import com.egyening.employeecommunicationapp.utils.AndroidUtils;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchUserAdapter  extends FirestoreRecyclerAdapter<Staff, SearchUserAdapter.UserViewHolder> {
    Context context;

    public SearchUserAdapter(@NonNull FirestoreRecyclerOptions<Staff> options, Context context) {
        super(options);
        this. context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull Staff model) {
        holder.emailtxt.setText(model.getEmail());
        holder.firstnametxt.setText(model.getFirstName());
        holder.lastnametxt.setText(model.getLastName());


        holder.itemView.setOnClickListener(v->{
            Intent intent =new Intent(context, ChatActivity.class);
            AndroidUtils.passUserAsIntent(intent,model);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
        });

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_user_recycler_row,parent,false);
        return new UserViewHolder(view);
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
            TextView firstnametxt;
            TextView lastnametxt;
            TextView emailtxt;
            ImageView profilePic;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            firstnametxt= itemView.findViewById(R.id.firstname_txt);
            lastnametxt= itemView.findViewById(R.id.lastname_txt);
            emailtxt=itemView.findViewById(R.id.email_txt);
            profilePic=itemView.findViewById(R.id.profile_pic_img);

        }
    }
}
