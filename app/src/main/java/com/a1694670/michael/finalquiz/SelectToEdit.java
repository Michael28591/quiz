package com.a1694670.michael.finalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectToEdit extends AppCompatActivity {
    Spinner allQues;
    private String key;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_to_edit);

        allQues   =(Spinner) findViewById(R.id.spinner);
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
                    //areas.add("[" + index + "] " +ques);
                    areas.add(ques);
                    index++;
                }

                Spinner areaSpinner = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(SelectToEdit.this, android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                areaSpinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void editques(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Que");
       // Toast.makeText(this, allQues.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        myRef.orderByChild("question").equalTo(allQues.getSelectedItem().toString()).addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                               for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                                   //String clubkey = childSnapshot.getKey();


                                                   key=childSnapshot.getKey().toString();
                                                   i =new Intent(SelectToEdit.this,UpdateQues.class);
                                                   i.putExtra("ques", allQues.getSelectedItem().toString());
                                                   i.putExtra("key",key);
                                                   startActivity(i);
                                                    }
                                           }

                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {

                                           }
                                       }
                );


    }
}
