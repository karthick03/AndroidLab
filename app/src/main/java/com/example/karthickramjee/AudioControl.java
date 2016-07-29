package com.example.karthickramjee.login;

import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class AudioControl extends AppCompatActivity {

    Button ring,vib,silent,record,stop,play;
    AudioManager am;
    MediaRecorder mr;
    String output=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_control);
        ring=(Button)findViewById(R.id.ring);
        silent=(Button)findViewById(R.id.silent);
        vib=(Button)findViewById(R.id.vibrate);
        record=(Button)findViewById(R.id.record);
        stop=(Button)findViewById(R.id.stop);
        play=(Button)findViewById(R.id.play);
        stop.setEnabled(false);
        play.setEnabled(false);
        output= Environment.getExternalStorageDirectory().getAbsolutePath()+"/recording.3gp";
        mr=new MediaRecorder();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mr.setOutputFile(output);
        am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(AudioControl.this, "Normal mode set", Toast.LENGTH_SHORT).show();
            }
        });
        vib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(AudioControl.this, "Vibration mode set", Toast.LENGTH_SHORT).show();

            }
        });
        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(AudioControl.this, "Silent mode set", Toast.LENGTH_SHORT).show();

            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mr=new MediaRecorder();
                    mr.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                    mr.setOutputFile(output);
                    mr.prepare();
                    mr.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                record.setEnabled(false);
                ring.setEnabled(false);
                silent.setEnabled(false);
                play.setEnabled(false);
                vib.setEnabled(false);
                stop.setEnabled(true);
                Toast.makeText(AudioControl.this, "Recording...", Toast.LENGTH_SHORT).show();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mr.stop();
                mr.release();
                mr=null;
                stop.setEnabled(false);
                ring.setEnabled(true);
                silent.setEnabled(true);
                vib.setEnabled(true);
                play.setEnabled(true);
                record.setEnabled(true);
                Toast.makeText(AudioControl.this, "Audio Recorded Successfully!!!", Toast.LENGTH_SHORT).show();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer m=new MediaPlayer();
                try {
                    m.setDataSource(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    m.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                m.start();
                Toast.makeText(AudioControl.this, "Audio Playing...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
