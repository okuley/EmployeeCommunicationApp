package com.egyening.employeecommunicationapp.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.egyening.employeecommunicationapp.Staff;

public class AndroidUtils {

    public static void displayToast(Context context, String message){

        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void passUserAsIntent(Intent intent, Staff model){
        intent.putExtra("firstname",model.getFirstName());
        intent.putExtra("lastname",model.getLastName());
        intent.putExtra("email",model.getEmail());
        intent.putExtra("staffId",model.getStaffId());
    }

    public static Staff getStaffFromIntent(Intent intent){
        Staff staff=new Staff();
        staff.setFirstName(intent.getStringExtra("firstname"));
        staff.setLastName(intent.getStringExtra("lastname"));
        staff.setEmail(intent.getStringExtra("email"));
        staff.setStaffId(intent.getStringExtra("staffId"));
        return staff;
    }
}
