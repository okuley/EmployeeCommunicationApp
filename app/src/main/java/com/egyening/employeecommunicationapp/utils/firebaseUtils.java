package com.egyening.employeecommunicationapp.utils;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.List;
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
            return userId2+"_"+userId1;
        }
    }
    public static CollectionReference getChatRoomMessageReference( String chatRoomId){
    return getChatRoomReference(chatRoomId).collection("chats");
    }

    public static CollectionReference getAllChatRoomCollectionReference(){
        return FirebaseFirestore.getInstance().collection("chatrooms");
    }

    public static DocumentReference getSomeUserFromChatRoom(List<String> staffIds){
    if(staffIds.get(0).equals(firebaseUtils.getCurrentStaffEmail())){
        return allUserCollectionReference().document(staffIds.get(1));
    }else {
        return allUserCollectionReference().document(staffIds.get(0));
    }
    }

    public static  String timestampToString(Timestamp timestamp){
    return new SimpleDateFormat("HH:MM").format(timestamp.toDate());
    }
}
