package com.egyening.employeecommunicationapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.egyening.employeecommunicationapp.ChatActivity;
import com.egyening.employeecommunicationapp.R;
import com.egyening.employeecommunicationapp.ChatMessage;
import com.egyening.employeecommunicationapp.utils.AndroidUtils;
import com.egyening.employeecommunicationapp.utils.firebaseUtils;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChatRecyclerAdapter  extends FirestoreRecyclerAdapter<ChatMessage, ChatRecyclerAdapter.ChatViewHolder> {
    Context context;

    public ChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessage> options, Context context) {
        super(options);
        this. context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatViewHolder holder, int position, @NonNull ChatMessage model) {
        if(model.getSenderId().equals(firebaseUtils.getCurrentStaffEmail())){
            holder.leftChatLayout.setVisibility(View.GONE);
            holder.rightChatLayout.setVisibility(View.VISIBLE);
            holder.rightChatTxt.setText(model.getMessage());
        }else {
            holder.rightChatLayout.setVisibility(View.GONE);
            holder.leftChatLayout.setVisibility(View.VISIBLE);
            holder.leftChatTxt.setText(model.getMessage());
        }

    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chat_message_recycler_row,parent,false);
        return new ChatViewHolder(view);
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{

        LinearLayout leftChatLayout,rightChatLayout;
        TextView leftChatTxt,rightChatTxt;


        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
           leftChatLayout=itemView.findViewById(R.id.left_chat_layout);
           rightChatLayout=itemView.findViewById(R.id.right_chat_layout);
           leftChatTxt=itemView.findViewById(R.id.left_chat_textview);
           rightChatTxt=itemView.findViewById(R.id.right_chat_textview);

        }
    }
}
