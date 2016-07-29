package com.example.karthickramjee.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignClass extends AppCompatActivity {


    EditText password,name,email;
    Button register;
    private int up=0,low=0,no=0,spl=0,xtra=0,len=0,points=0,max=8;
    private char c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        password=(EditText)findViewById(R.id.password);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        register=(Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameval=name.getText().toString();
                String emailval=email.getText().toString();
                String passval=password.getText().toString();
                if(nameval.matches(""))
                    name.setError("Name Required");
                if(emailval.matches(""))
                    email.setError("Email Required");
                if(passval.matches(""))
                    password.setError("Password Required");
                calcStr(passval);
            }
        });
    }
    private void showMessage(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    private void calcStr(String pass)
    {
        int len=pass.length();
        if(len==0)
        {
            showMessage("Invalid Input ");
            return;
        }
        if(len<=5) points++;
        else
        if(len<=10) points+=2;
        else
            points+=3;
        for(int i=0;i<len;i++)
        {
            c=pass.charAt(i);
            if(c >='a' && c<='z') { if(low==0) points++;	low=1;}
            else
            {
                if(c >='A' && c<='Z') {if(up==0) points++;	up=1;}
                else
                {
                    if(c >='0' && c<='9') {if(no==0) 	points++;	no=1;}
                    else
                    {
                        if(c == '_' || c == '@') { if(spl==0) 	points+=1;	spl=1;}
                        else
                        {
                            if(xtra==0)	points+=2;
                            xtra=1;

                        }
                    }
                }
            }
        }
        if(points<=3) showMessage("Password Strength : LOW ");
        else
        if(points<=6) showMessage("Password Strength : MEDIUM ");
        else
        if(points<=9) showMessage("Password Strength : HIGH ");
        points=0;len=0;up=0;low=0;no=0;xtra=0;spl=0;
    }

}
