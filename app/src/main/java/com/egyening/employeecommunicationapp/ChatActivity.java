package com.egyening.employeecommunicationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.egyening.employeecommunicationapp.adapter.ChatRecyclerAdapter;
import com.egyening.employeecommunicationapp.adapter.SearchUserAdapter;
import com.egyening.employeecommunicationapp.utils.AndroidUtils;
import com.egyening.employeecommunicationapp.utils.firebaseUtils;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.sql.Array;
import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {
Staff someUser;
EditText messageInput;
ImageButton sendbutton;
ImageButton backbutton;

TextView someUserName;
RecyclerView recyclerView;
String chatRoomId;

ChatRoom chatRoom;
ChatRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        someUser= AndroidUtils.getStaffFromIntent(getIntent());
        //Intent intent= new Intent(ChatActivity.this,ChatRoom.class);
       // AndroidUtils.passUserAsIntent(intent,someUser);
        chatRoomId= firebaseUtils.chatRoomId(firebaseUtils.getCurrentStaffEmail(),someUser.getEmail());

        messageInput=findViewById(R.id.chat_input);
        sendbutton=findViewById(R.id.sendchatbutton);
        backbutton=findViewById(R.id.chatbackbutton);
        someUserName=findViewById(R.id.username);
        recyclerView=findViewById(R.id.chat_view_holder);

        backbutton.setOnClickListener(v->{
            onBackPressed();

        });

        someUserName.setText(someUser.getFirstName());
        getOrCreateChatRoom();
        setUpChatRecylerView();
        sendbutton.setOnClickListener(v->{
            String message=messageInput.getText().toString();
            if(message.isEmpty()){
                return;
            }
            sendMessageToUser(message);
        });
    }

    public  void getOrCreateChatRoom(){
   firebaseUtils.getChatRoomReference(chatRoomId).get().addOnCompleteListener(task-> {

       if(task.isSuccessful()){
          chatRoom=task.getResult().toObject(ChatRoom.class);

          if(chatRoom==null){
              ChatRoom chatRoom1  = new ChatRoom(
                      chatRoomId,
                      Arrays.asList(firebaseUtils.getCurrentStaffEmail(),someUser.getEmail()),
                     /// Arrays.asList(firebaseUtils.getCurrentUseruid(),someUser.getStaffId()),
                      Timestamp.now(),
                      ""
              );
              firebaseUtils.getChatRoomReference(chatRoomId).set(chatRoom1);
          }
       }
   });


    }
    public void  sendMessageToUser( String message){
        chatRoom.setLastMessageTimestamp(Timestamp.now());
        chatRoom.setLastMessageSenderId(firebaseUtils.getCurrentStaffEmail());
        chatRoom.setLastMessage(message);
        firebaseUtils.getChatRoomReference(chatRoomId).set(chatRoom);
    ChatMessage chatMessage= new ChatMessage(message,firebaseUtils.getCurrentStaffEmail(),Timestamp.now());
    firebaseUtils.getChatRoomMessageReference(chatRoomId).add(chatMessage)
            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    messageInput.setText("");
                }
                }
            });
    }

    public void setUpChatRecylerView(){
        Query query= firebaseUtils.getChatRoomMessageReference(chatRoomId)
                .orderBy("timestamp",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ChatMessage> options=new FirestoreRecyclerOptions.Builder<ChatMessage>()
                .setQuery(query, ChatMessage.class).build();

        adapter= new ChatRecyclerAdapter(options,getApplicationContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }


}