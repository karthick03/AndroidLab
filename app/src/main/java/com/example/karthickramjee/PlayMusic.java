package com.example.karthickramjee.login;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PlayMusic extends Service {


    MediaPlayer player;
    public void onCreate()
    {
        super.onCreate();
        player=MediaPlayer.create(this,R.raw.someaudio);
        player.setLooping(true);
        player.setVolume(100,100);
    }
    public int onStartCommand(Intent intent,int flags,int start)
    {
        player.start();
        return 1;
    }
    public void onStop()
    {
        player.stop();
        player.release();
    }
    public void onPause()
    {
        player.stop();
        player.release();
    }
    public void onDestroy()
    {
        player.stop();
        player.release();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
