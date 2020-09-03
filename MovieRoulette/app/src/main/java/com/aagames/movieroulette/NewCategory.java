package com.aagames.movieroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewCategory extends AppCompatActivity {

    Button create;
    String categoryName;
    EditText catNameEt;
    EditText movieNameEt;
    String id;
    FirebaseAuth auth;
    int next;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        auth = FirebaseAuth.getInstance();
        id = auth.getUid();

        create = (Button) findViewById(R.id.createCat);
        catNameEt = (EditText) findViewById(R.id.catNameEt);
        movieNameEt = (EditText) findViewById(R.id.movieNameEt);

        add = (Button) findViewById(R.id.add);
        add.setVisibility(View.INVISIBLE);


        System.out.println("Noluyo öyle"+categoryName);


        final DatabaseReference listRef =FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "movielists" );

        listRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                next=0;
                // lists = new ArrayList<MovieList>();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    next++;


                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryName = catNameEt.getText().toString();
                listRef.child( ""+next ).setValue(new MovieList(categoryName));
                create.setVisibility(View.INVISIBLE);
                catNameEt.setFocusable(false);
                Toast.makeText(getApplicationContext(),categoryName+" category has created. You can add movies.",Toast.LENGTH_SHORT).show();
                add.setVisibility(View.VISIBLE);
            }
        });

    }
}