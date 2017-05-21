package com.a1694670.michael.finalquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class addQues extends AppCompatActivity {

    EditText edt_que,edt_chA,edt_chB,edt_chC,edt_chD,edt_correctAns;

    int count,mx;

    ArrayList<Integer> ch;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Que");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ques);

        edt_que = (EditText) findViewById(R.id.edt_que);
        edt_chA = (EditText) findViewById(R.id.edt_chA);
        edt_chB = (EditText) findViewById(R.id.edt_chB);
        edt_chC = (EditText) findViewById(R.id.edt_chC);
        edt_chD = (EditText) findViewById(R.id.edt_chD);
        edt_correctAns = (EditText) findViewById(R.id.edt_correctAns);


        count = 0;


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ch  = new ArrayList<Integer>();
                for(DataSnapshot qs : dataSnapshot.getChildren())
                {
                    ch.add(Integer.parseInt(qs.getKey()));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

        public void onClickAddNew(View v) {

            String que,chA,chB,chC,chD,correctAns;

            que = edt_que.getText().toString();
            chA = edt_chA.getText().toString();
            chB = edt_chB.getText().toString();
            chC = edt_chC.getText().toString();
            chD = edt_chD.getText().toString();
            correctAns = edt_correctAns.getText().toString();



            if(ch.isEmpty())
            {
                mx = 0;
            }else
            {
                mx = Collections.max(ch);
                mx++;
            }

            String cnt = String.valueOf(mx);


            DatabaseReference nestedChild = myRef.child(cnt);

            nestedChild.child("chA").setValue(chA);
            nestedChild.child("chB").setValue(chB);
            nestedChild.child("chC").setValue(chC);
            nestedChild.child("chD").setValue(chD);
            nestedChild.child("correctAns").setValue(correctAns);
            nestedChild.child("question").setValue(que);

            Toast.makeText(getApplicationContext(),"Total number of questions is "+(mx-1),Toast.LENGTH_SHORT).show();

        }


}
