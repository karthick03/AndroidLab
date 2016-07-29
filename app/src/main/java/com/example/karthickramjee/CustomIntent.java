package com.example.karthickramjee.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by karthickramjee on 24/07/16.
 */
public class CustomIntent extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Toast from Custom Intent", Toast.LENGTH_SHORT).show();
    }
}
