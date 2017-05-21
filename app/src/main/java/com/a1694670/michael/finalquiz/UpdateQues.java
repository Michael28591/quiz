package com.a1694670.michael.finalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateQues extends AppCompatActivity {

      EditText edt_que, edt_chA,edt_chB,edt_chC,edt_chD,edt_correctAns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ques);
        Bundle extras = getIntent().getExtras();
        String ques= extras.getString("ques");
        String key=extras.getString("key");

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database1.getReference("Que");
        edt_que = (EditText) findViewById(R.id.edt_que);

        edt_chB = (EditText) findViewById(R.id.edt_chB);
        edt_chC = (EditText) findViewById(R.id.edt_chC);
        edt_chD = (EditText) findViewById(R.id.edt_chD);
        edt_correctAns = (EditText) findViewById(R.id.edt_correctAns);
        edt_chA = (EditText) findViewById(R.id.edt_chA);

        myRef1.child(key).child("question").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    edt_que.setText(snapshot.getValue().toString());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef1.child(key).child("chA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    edt_chA.setText(snapshot.getValue().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef1.child(key).child("chB").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    edt_chB.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef1.child(key).child("chC").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    edt_chC.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef1.child(key).child("chD").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    edt_chD.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef1.child(key).child("correctAns").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    edt_correctAns.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void updateQuestion(View v) {
        Bundle extras = getIntent().getExtras();
        String ques= extras.getString("ques");
        String key=extras.getString("key");
        edt_que = (EditText) findViewById(R.id.edt_que);

        edt_chB = (EditText) findViewById(R.id.edt_chB);
        edt_chC = (EditText) findViewById(R.id.edt_chC);
        edt_chD = (EditText) findViewById(R.id.edt_chD);
        edt_correctAns = (EditText) findViewById(R.id.edt_correctAns);
        edt_chA = (EditText) findViewById(R.id.edt_chA);
        String que=edt_que.getText().toString();
        String chA=edt_chA.getText().toString();
        String chB=edt_chB.getText().toString();
        String chC=edt_chC.getText().toString();
        String chD=edt_chD.getText().toString();
        String correctAns=edt_correctAns.getText().toString();
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        final DatabaseReference myRef1 = database1.getReference("Que");
        DatabaseReference nestedChild = myRef1.child(key);

        nestedChild.child("chA").setValue(chA);
        nestedChild.child("chB").setValue(chB);
        nestedChild.child("chC").setValue(chC);
        nestedChild.child("chD").setValue(chD);
        nestedChild.child("correctAns").setValue(correctAns);
        nestedChild.child("question").setValue(que);

        Toast.makeText(this, "Question Updated", Toast.LENGTH_SHORT).show();


    }

}
