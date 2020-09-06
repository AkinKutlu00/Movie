package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.objects.MovieList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendList extends AppCompatActivity {

    String id;
    FirebaseAuth auth;
    MovieList ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_list);

        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        final ArrayList<MovieList> allList = new ArrayList<>();


        final DatabaseReference listRef =FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "movielists" );

        final DatabaseReference sendRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child("FlFB7hbCzLXVXfHZRGfV8e3eLOH3").child( "movielists" );


        listRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterable< DataSnapshot > cList = snapshot.getChildren();

                allList.clear();
                for ( DataSnapshot list : cList )
                {
                    MovieList thelist = list.getValue( MovieList.class );
                    allList.add( thelist );
                }


                //ml = allList.get( 11 );


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ml = new MovieList("Akın");
       // System.out.println(ml.getName());

       // FirebaseDatabase.getInstance().getReference().child( "users" ).child("FlFB7hbCzLXVXfHZRGfV8e3eLOH3").child("movielists").child("8").setValue(ml);

    }
}