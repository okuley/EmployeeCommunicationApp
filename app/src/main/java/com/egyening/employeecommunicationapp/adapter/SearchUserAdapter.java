package com.egyening.employeecommunicationapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.egyening.employeecommunicationapp.R;
import com.egyening.employeecommunicationapp.Staff;
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
    holder.firstnametxt.setText(model.getFirstName());
    holder.lasnametxt.setText(model.getLastName());
    holder.emailtxt.setText(model.getEmail());
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_user_recycler_row,parent,false);
        return new UserViewHolder(view);
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
            TextView firstnametxt;
            TextView lasnametxt;
            TextView emailtxt;
            ImageView profilePic;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            firstnametxt= itemView.findViewById(R.id.firstname_txt);
            lasnametxt= itemView.findViewById(R.id.lastname_txt);
            emailtxt=itemView.findViewById(R.id.email_txt);
            profilePic=itemView.findViewById(R.id.profile_pic_img);

        }
    }
}
