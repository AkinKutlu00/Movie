package com.aagames.movieroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
                    System.out.println(  "heyy " + categories.get( 0 ));
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