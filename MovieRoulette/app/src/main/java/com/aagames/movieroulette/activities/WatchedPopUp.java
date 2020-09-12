package com.aagames.movieroulette.activities;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    String newtitleName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched_pop_up);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        final String titleName = getIntent().getStringExtra( "title" );
        newtitleName=titleName;
        myRecyclerView= findViewById(R.id.recyle);
        Button save;




        getWindow().setLayout((int) (width*.9),(int) (height*.7));



        myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists");
        listName = "1";





        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterable< DataSnapshot > cList = snapshot.getChildren();

                int i=0;

                allList.clear();
                for ( DataSnapshot list : cList )
                {
                    MovieList thelist = list.getValue( MovieList.class );
                    i++;
                    if( thelist.getName().equals( titleName ) )
                    {

                        i--;
                        currentList = thelist;
                        listName = ""+i;
                        mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
                        myRecyclerView.setLayoutManager(mLayoutManager);
                        mAdapter = new MovieAdapterBig(getApplicationContext(),currentList.getMovies());
                        myRecyclerView.setAdapter(mAdapter);
                        break;
                    }

                }




            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMovie();

            }
        });



    }


    @Override
    protected void onDestroy() {
        saveMovie();
        super.onDestroy();
    }

    public void saveMovie(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterable< DataSnapshot > cList = snapshot.getChildren();

                int i=0;

                allList.clear();
                for ( DataSnapshot list : cList )
                {
                    MovieList thelist = list.getValue( MovieList.class );
                    i++;
                    if( thelist.getName().equals( newtitleName ) )
                    {
                        i--;
                        myRef.child(i+"").setValue(currentList);



                        break;
                    }

                }



            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        System.out.println("Heyo");

        Toast.makeText(getApplicationContext(),"List Saved",Toast.LENGTH_SHORT).show();
    }
}