package com.egyening.employeecommunicationapp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class firebaseUtils {

    public static CollectionReference allUserCollectionReference(){

      return   FirebaseFirestore.getInstance().collection("users");
    }
}
