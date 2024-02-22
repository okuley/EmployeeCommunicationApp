package com.egyening.employeecommunicationapp.utils;

import android.content.Context;
import android.widget.Toast;

public class AndroidUtils {

    public static void displayToast(Context context, String message){

        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
