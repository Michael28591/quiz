package com.a1694670.michael.finalquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class delQues extends AppCompatActivity {



    //ArrayList<Quizquestions> question1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_ques);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();





        myRef.child("Que").addValueEventListener(new ValueEventListener() {

            //TextView txt_que1=(TextView) findViewById(R.id.allques);

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> areas = new ArrayList<String>();
                int index=0;
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {

                    String ques = areaSnapshot.child("question").getValue(String.class);

                    areas.add(ques);

                }

                Spinner areaSpinner = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(delQues.this, android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                areaSpinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void deleteQues(View v) {
        Spinner allQues=(Spinner) findViewById(R.id.spinner);
        //Toast.makeText(this, allQues.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference();
        myRef1.child("Que").orderByChild("question").equalTo(allQues.getSelectedItem().toString()).addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef1 = database.getReference();
                myRef1.child("Que").child(dataSnapshot.getKey()).setValue(null);
                //Toast.makeText(delQues.this, "Questio Deleted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
