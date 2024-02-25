package com.egyening.employeecommunicationapp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class firebaseUtils {

public static String getCurrentStaffEmail(){
    return  Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
}

    public static DocumentReference CurrentStaffDetails(){
        return FirebaseFirestore.getInstance().collection("users").document(getCurrentStaffEmail());
    }
    public static CollectionReference allUserCollectionReference(){

      return   FirebaseFirestore.getInstance().collection("users");
    }

    public static DocumentReference getChatRoomReference(String chatRoomId){
        return FirebaseFirestore.getInstance().collection("chatrooms").document(chatRoomId);
    }

    public static String chatRoomId(String userId1,String userId2){
        if(userId1.hashCode()<userId2.hashCode()){
            return userId1+"_"+userId2;
        }else {
            return userId2+"_"+userId2;
        }
    }
    public static CollectionReference getChatRoomMessageReference( String chatRoomId){
    return getChatRoomReference(chatRoomId).collection("chats");
    }
}
