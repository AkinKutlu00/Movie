package com.aagames.movieroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView myRecyclerView;
    ArrayList<String> categories;
    private FirebaseAuth auth;

    Button logOut;
    Button newCategory;

    //ArrayList<MovieList> lists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        auth = FirebaseAuth.getInstance();
        final String id = auth.getUid();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists");

        categories = new ArrayList<>();

        mAdapter = new CategoryAdapter(getApplicationContext(),categories);

        logOut = (Button) findViewById(R.id.logoutbtn);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                auth.signOut();

            }
        });


        newCategory = (Button) findViewById(R.id.newcat);

        newCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewCategory.class));


            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               // lists = new ArrayList<MovieList>();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    MovieList list =  ( MovieList ) shot.getValue( MovieList.class );

                    //lists.add( list );
                    categories.add( list.getName() );
                    mAdapter.notifyDataSetChanged();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //mAdapter.notifyDataSetChanged();

        //categories.add("AAA");
        //categories.add("BBB");
        //categories.add("CCC");


       // mAdapter = new CategoryAdapter(getApplicationContext(),categories);

        myRecyclerView = findViewById(R.id.rv);
        //myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,3);
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);



    }
}