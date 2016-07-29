package com.example.karthickramjee.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ExerciseFour extends AppCompatActivity {

    EditText text;
    Button b7;
    TextToSpeech t1;
    TextView font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_four);
        text=(EditText)findViewById(R.id.text);
        b7=(Button)findViewById(R.id.button7);
        font=(TextView)findViewById(R.id.font);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }
    public void alertDialog(View view)
    {
        font.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        b7.setVisibility(View.GONE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, you wanted to make decision???");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ExerciseFour.this,"You clicked yes button",Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ExerciseFour.this,"You clicked no button",Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setTitle("AlertDialog");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void autoSuggest(View view)
    {
        font.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        b7.setVisibility(View.GONE);
        Intent intent=new Intent(ExerciseFour.this,AutoSuggest.class);
        startActivity(intent);
    }
    public void mediaControl(View view)
    {
        text.setVisibility(View.GONE);
        b7.setVisibility(View.GONE);
        font.setVisibility(View.GONE);
        Intent intent=new Intent(ExerciseFour.this,AudioControl.class);
        startActivity(intent);
    }
    public void textToVoice(View view)
    {
        font.setVisibility(View.GONE);
        text.setVisibility(View.VISIBLE);
        b7.setVisibility(View.VISIBLE);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = text.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void appNavigation(View view)
    {
        text.setVisibility(View.GONE);
        b7.setVisibility(View.GONE);
        font.setVisibility(View.GONE);
        Intent intent=new Intent(ExerciseFour.this,ListApps.class);
        startActivity(intent);
    }
    public void customFont(View view)
    {
        text.setVisibility(View.GONE);
        b7.setVisibility(View.GONE);
        Typeface custom=Typeface.createFromAsset(this.getAssets(),"fonts/uniq.ttf");
        font.setTypeface(custom);
        font.setVisibility(View.VISIBLE);
    }
    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
