package com.example.karthickramjee.login;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class FontClass extends AppCompatActivity {

    Button color,inc,dec;
    TextView text;
    private int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_class);
        color=(Button)findViewById(R.id.color);
        inc=(Button)findViewById(R.id.inc);
        dec=(Button)findViewById(R.id.dec);
        text=(TextView) findViewById(R.id.text);
        size=16;
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextSize(size++);
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setTextSize(size--);
            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                text.setTextColor(color);
            }
        });
    }
}
