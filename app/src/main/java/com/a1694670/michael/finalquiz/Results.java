package com.a1694670.michael.finalquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle extras = getIntent().getExtras();
        int total= extras.getInt("total");
        int correct=extras.getInt("correct");
        int wrong=extras.getInt("wrong");

       EditText ttl,crrct,wrng;
        ttl=(EditText) findViewById(R.id.total);
        crrct=(EditText) findViewById(R.id.correct);
        wrng=(EditText) findViewById(R.id.wrong);
        ttl.setText(String.valueOf(total));
        crrct.setText(String.valueOf(correct));
        wrng.setText(String.valueOf(wrong));



    }
}
