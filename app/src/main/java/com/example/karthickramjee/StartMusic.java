package com.example.karthickramjee.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by karthickramjee on 24/07/16.
 */
public class StartMusic extends AppCompatActivity {
    TextView status;
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            status.setText(String.valueOf(level) + "%");
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        status=(TextView)findViewById(R.id.status);
    }
    public void playAudio(View view) {
        status.setVisibility(View.GONE);
        Intent objIntent = new Intent(this, PlayMusic.class);
        startService(objIntent);
    }

    public void stopAudio(View view) {
        status.setVisibility(View.GONE);
        Intent objIntent = new Intent(this, PlayMusic.class);
        stopService(objIntent);
    }
    public void startToast(View view)
    {
        status.setVisibility(View.GONE);
        Intent objIntent = new Intent(this, StartToast.class);
        startService(objIntent);
    }
    public void getBattery(View view)
    {
        status.setVisibility(View.VISIBLE);
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    public void customIntent(View view)
    {
        status.setVisibility(View.GONE);
        Intent intent=new Intent();
        intent.setAction("com.example.CUSTOM_INTENT");
        sendBroadcast(intent);
    }
}
