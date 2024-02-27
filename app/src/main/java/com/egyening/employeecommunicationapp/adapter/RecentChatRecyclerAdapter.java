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

    public RecentChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatRoom> options, Context context) {
        super(options);
        this. context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatRoomViewHolder holder, int position, @NonNull ChatRoom model) {
        //firebaseUtils.getSomeUserFromChatRoom(model.getStaffIds())
                //.get().addOnCompleteListener(task -> {
              //  Staff someUser=task.getResult().toObject(Staff.class);
                //holder.firstname_txt.setText(someUser.getFirstName());
                holder.lastmessage_txt.setText(model.getLastMessage());
                holder.lastmessagetime_txt.setText(firebaseUtils.timestampToString(model.getLastMessageTimestamp()));
                //});

    }

    @NonNull
    @Override
    public ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recent_chat_recycler_row,parent,false);
        return new ChatRoomViewHolder(view);
    }

    class ChatRoomViewHolder extends RecyclerView.ViewHolder{

        TextView firstname_txt;

        TextView lastmessage_txt;

        TextView lastmessagetime_txt;
        ImageView profilePic;
        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname_txt= itemView.findViewById(R.id.firstname_txt);
            lastmessage_txt= itemView.findViewById(R.id.last_message_txt);
            lastmessagetime_txt=itemView.findViewById(R.id.last_message_time_txt);
            profilePic=itemView.findViewById(R.id.profile_pic_img);
           

        }
    }
}
