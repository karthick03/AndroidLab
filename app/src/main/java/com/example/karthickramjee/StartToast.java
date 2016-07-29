package com.example.karthickramjee.login;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by karthickramjee on 24/07/16.
 */
public class StartToast extends Service {


    String message="Hello World.Toast Displayed!!!";
    Thread t;
    private Handler handler;
    @Override
    public int onStartCommand(Intent intent,int flags,int start)
    {
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        };
        t=new Thread()
        {
            public void run()
            {
                Message mess=new Message();
                mess.obj=message;
                handler.sendMessage(mess);
            }
        };
        t.start();
        return 1;
    }
    public void onCreate()
    {
        super.onCreate();
        //t.start();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
