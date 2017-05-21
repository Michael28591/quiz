package com.a1694670.michael.finalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class quiz extends AppCompatActivity {


    TextView txt_que;
    RadioButton chA,chB,chC,chD,sel;
    ImageView btn_nxt;

    private int index;
    int total=0;
    int correct=0;
    int wrong=0;
    RadioGroup rg;

    ArrayList<Quizquestions> question;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Que");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        rg = (RadioGroup) findViewById(R.id.radioGroup);

        txt_que = (TextView) findViewById(R.id.txt_qque);

        chA = (RadioButton) findViewById(R.id.rd_chA);
        chB = (RadioButton) findViewById(R.id.rd_chB);
        chC = (RadioButton) findViewById(R.id.rd_chC);
        chD = (RadioButton) findViewById(R.id.rd_chD);


        btn_nxt =(ImageView) findViewById(R.id.btn_qnxt);




        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                question = new ArrayList<Quizquestions>();

                for (DataSnapshot qs : dataSnapshot.getChildren()) {
                    question.add(qs.getValue(Quizquestions.class));
                }

                index = 0;
                dispQue(index);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void dispQue(int index)
    {

        if(index < question.size())
        {
            txt_que.setText(question.get(index).getQuestion());
            chA.setText(question.get(index).getChA());
            chB.setText(question.get(index).getChB());
            chC.setText(question.get(index).getChC());
            chD.setText(question.get(index).getChD());
        }
        else{
            Intent i=new Intent(this,Results.class);
            i.putExtra("correct",correct);
            i.putExtra("wrong",wrong);
            i.putExtra("total",total);
            startActivity(i);
            Toast.makeText(this, "Total number of questions is " + total, Toast.LENGTH_SHORT).show();



        }


    }

    private boolean checkCorrect()
    {
        int selected = rg.getCheckedRadioButtonId();

        sel =(RadioButton) findViewById(selected);

        if(index < question.size()) {
            if (sel.getText().equals(question.get(index).getCorrectAns()))
            {
                return true;
            }
            else {
                return false;
            }
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_SHORT).show();
        }

        return false;
    }

        public void NextQues(View v) {
            if (checkCorrect())
            {
                Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_SHORT).show();
                index++;
                correct++;
                total++;
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_SHORT).show();
                index++;
                wrong++;
                total++;
            }

            dispQue(index);

        }



}
