package com.example.karthickramjee.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.lib.recaptcha.ReCaptcha;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements ReCaptcha.OnShowChallengeListener, ReCaptcha.OnVerifyAnswerListener{

    private static final String PUBLIC_KEY  = "6LfbFSUTAAAAALBTWR_QTwBNBOq83bpHZhPExcTB";
    private static final String PRIVATE_KEY = "6LfbFSUTAAAAACdPzhZrGS1I9JOVczP4fC-X03m1";

    private EditText answer,username,password;
    private ReCaptcha   reCaptcha;
    private ProgressBar progress;
    private Button signup,login;
    private boolean checkvalue=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reCaptcha = (ReCaptcha)this.findViewById(R.id.recaptcha);
        answer    = (EditText)this.findViewById(R.id.answer);
        progress  = (ProgressBar)this.findViewById(R.id.progress);
        signup  = (Button) this.findViewById(R.id.signup);
        login  = (Button) this.findViewById(R.id.login);
        username    = (EditText)this.findViewById(R.id.username);
        password    = (EditText)this.findViewById(R.id.password);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignClass.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer();
                if(username.getText().toString().equals("karthi") && password.getText().toString().equals("karthi") && checkvalue)
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
            }
        });
        /*this.findViewById(R.id.verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer();
            }
        });*/
        this.findViewById(R.id.reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkvalue=false;
                answer.setText("");
                showChallenge();
            }
        });

        showChallenge();

    }

    @Override
    public void onChallengeShown(final boolean shown) {
        this.progress.setVisibility(View.GONE);

        if (shown) {
            // If a CAPTCHA is shown successfully, displays it for the user to enter the words
            this.reCaptcha.setVisibility(View.VISIBLE);
            checkvalue=false;
        } else {
            Toast.makeText(this, R.string.show_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnswerVerified(final boolean success) {
        if (success) {
            checkvalue=true;
            answer.setText("");
            showChallenge();
            Toast.makeText(this, R.string.verification_success, Toast.LENGTH_SHORT).show();
        } else {
            checkvalue=false;
            answer.setText("");
            showChallenge();
            Toast.makeText(this, R.string.verification_failed, Toast.LENGTH_SHORT).show();
        }

        // (Optional) Shows the next CAPTCHA
        //this.showChallenge();
        this.progress.setVisibility(View.GONE);
    }

    private void showChallenge() {
        // Displays a progress bar while downloading CAPTCHA
        this.progress.setVisibility(View.VISIBLE);
        this.reCaptcha.setVisibility(View.GONE);

        this.reCaptcha.setLanguageCode("en");
        this.reCaptcha.showChallengeAsync(LoginActivity.PUBLIC_KEY, this);
    }

    private void verifyAnswer() {
        if (TextUtils.isEmpty(this.answer.getText())) {
            Toast.makeText(this, R.string.instruction, Toast.LENGTH_SHORT).show();
        } else {
            // Displays a progress bar while submitting the answer for verification
            this.progress.setVisibility(View.VISIBLE);
            this.reCaptcha.verifyAnswerAsync(LoginActivity.PRIVATE_KEY, this.answer.getText().toString(), this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
