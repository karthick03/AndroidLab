package com.example.karthickramjee.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoSuggest extends AppCompatActivity {

    AutoCompleteTextView act;
    String[] language ={"C","C++","Java",".NET","FORTRAN","Android","ASP.NET","PHP"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_suggest);
        act=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, language);

        act.setThreshold(1);
        act.setAdapter(adapter);
    }
}
