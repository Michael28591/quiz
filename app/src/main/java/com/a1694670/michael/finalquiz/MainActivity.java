package com.a1694670.michael.finalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void play(View v) {
        Intent i = new Intent(getApplicationContext(),quiz.class);
        startActivity(i);
    }
    public void addQues(View v) {
        Intent i = new Intent(getApplicationContext(),addQues.class);
        startActivity(i);
    }
    public void delQues(View v) {
        Intent i = new Intent(getApplicationContext(),delQues.class);
        startActivity(i);
    }
    public void editQues(View v) {
        Intent i = new Intent(getApplicationContext(),SelectToEdit.class);
        startActivity(i);
    }


}
