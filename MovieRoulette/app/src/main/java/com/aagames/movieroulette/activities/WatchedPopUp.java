package com.aagames.movieroulette.activities;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.MovieAdapterBig;
import com.aagames.movieroulette.objects.MovieList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WatchedPopUp extends Activity {
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseReference myRef;
    final ArrayList<MovieList> allList = new ArrayList<>();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    final String id = auth.getUid();
    MovieList currentList;
    String listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched_pop_up);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;

        myRecyclerView= findViewById(R.id.recyle);


        getWindow().setLayout((int) (width*.9),(int) (height*.7));

        final String titleName = getIntent().getStringExtra( "title" );

        myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists");
        listName = "1";

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterable< DataSnapshot > cList = snapshot.getChildren();

                allList.clear();
                for ( DataSnapshot list : cList )
                {
                    MovieList thelist = list.getValue( MovieList.class );
                    allList.add( thelist );

                }


                if( allList.size() != 0 ) {
                    for ( int i = 0; i < allList.size(); i++ ) {

                        // When found the note set texts of text and title EditText to the name and the text of note
                        //      Change their sizes and styles according to the information taken from Firebase
                        if( allList.get( i ).getName().equals( titleName ) )
                        {


                            currentList = allList.get( i );
                            listName = ""+i;
                            mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
                            myRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MovieAdapterBig(getApplicationContext(),currentList.getMovies(),listName);
                            myRecyclerView.setAdapter(mAdapter);



                        }
                    }
                }

                myRecyclerView.setHasFixedSize(true);
                mLayoutManager = new GridLayoutManager(getApplicationContext(),1);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}