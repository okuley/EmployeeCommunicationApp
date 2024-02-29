package com.egyening.employeecommunicationapp;

import static com.egyening.employeecommunicationapp.utils.firebaseUtils.timestampToString;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.egyening.employeecommunicationapp.utils.firebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewsAndUpdatesActivity extends AppCompatActivity {

    ImageButton backbutton;
    EditText updateMessageInput;
    ImageButton sendbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_and_updates);

        backbutton =findViewById(R.id.backbutton);
        updateMessageInput=findViewById(R.id.updates_txt);
        sendbutton=findViewById(R.id.sendUpdate);

        backbutton.setOnClickListener(v->{
            onBackPressed();

        });

        sendbutton.setOnClickListener(v->{
            String message=updateMessageInput.getText().toString();
            String sender=firebaseUtils.getCurrentStaffEmail();
            String timestamp= timestampToString(Timestamp.now());
            if(message.isEmpty()){
                return;
            }
            sendMessageUpdate(message,sender,timestamp);
        });
    }

    public void sendMessageUpdate( String message,String sender,String timestamp){

        NewsAndUpdates newsAndUpdates= new NewsAndUpdates(message, sender, timestamp);
        FirebaseFirestore.getInstance().collection("updates").add(newsAndUpdates)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            updateMessageInput.setText("");
                        }
                    }
                });

    }
}