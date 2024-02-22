package com.egyening.employeecommunicationapp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class firebaseUtils {

    public static String currentUserId(){

        return FirebaseAuth.getInstance().getUid();
    }
    public static DocumentReference currentUserInfo(){

        return FirebaseFirestore.getInstance().collection("employees").document(currentUserId());
    }
}
