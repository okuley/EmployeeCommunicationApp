package com.egyening.employeecommunicationapp.adapter;


import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

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
import com.egyening.employeecommunicationapp.ChatRoom;
import com.egyening.employeecommunicationapp.Staff;
import com.egyening.employeecommunicationapp.utils.AndroidUtils;
import com.egyening.employeecommunicationapp.utils.firebaseUtils;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class RecentChatRecyclerAdapter  extends FirestoreRecyclerAdapter<ChatRoom, RecentChatRecyclerAdapter.ChatRoomViewHolder> {
    Context context;
    Staff otherUser;

    public RecentChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatRoom> options, Context context) {
        super(options);
        this. context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatRoomViewHolder holder, int position, @NonNull ChatRoom model) {
        firebaseUtils.getSomeUserFromChatRoom(model.getStaffIds())
                .get().addOnCompleteListener(task -> {
                   if(task.isSuccessful()){
                       otherUser =task.getResult().toObject(Staff.class);
                       //otherUser= AndroidUtils.getStaffFromIntent();
                       if (otherUser != null) {
                           holder.firstnametxt.setText(otherUser.getFirstName());

                       }

                       holder.lastmessage_txt.setText(model.getLastMessage());
                       holder.lastmessagetime_txt.setText(firebaseUtils.timestampToString(model.getLastMessageTimestamp()));

                       holder.itemView.setOnClickListener(v->{
                           Intent intent =new Intent(context, ChatActivity.class);
                           if (otherUser != null) {
                               AndroidUtils.passUserAsIntent(intent, otherUser);
                               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                               context.startActivity(intent);
                           }

                       });
                   }




                });



    }

    @NonNull
    @Override
    public ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recent_chat_recycler_row,parent,false);
        return new ChatRoomViewHolder(view);
    }

    class ChatRoomViewHolder extends RecyclerView.ViewHolder{

        TextView firstnametxt;

        TextView lastmessage_txt;

        TextView lastmessagetime_txt;
        ImageView profilePic;
        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            firstnametxt= itemView.findViewById(R.id.firstnametxt);
            lastmessage_txt= itemView.findViewById(R.id.last_message_txt);
            lastmessagetime_txt=itemView.findViewById(R.id.last_message_time_txt);
            profilePic=itemView.findViewById(R.id.profile_pic_img);
           

        }
    }
}
