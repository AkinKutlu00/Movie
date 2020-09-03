package com.aagames.movieroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NewCategory extends AppCompatActivity {

    Button create;
    String categoryName;
    EditText catNameEt;
    String id;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        create = findViewById(R.id.createCat);

        auth = FirebaseAuth.getInstance();
        catNameEt = (EditText) findViewById(R.id.catNameEt);
        id = auth.getUid();

        categoryName = catNameEt.getText().toString();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "movielists" ).setValue(new MovieList(categoryName));


            }
        });

    }
}