package com.aagames.movieroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button random;
    int maxNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<MovieItem> movieList1 = new ArrayList<>();
        final ArrayList<MovieItem> movieListBlue = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Imdb");


        //myRef.child("0").child("revealed").setValue(true);
        //movieList1.get(0).setRevealed(true);



        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,10);







        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movieList1.clear();
                movieListBlue.clear();
                maxNumber = (int) snapshot.getChildrenCount();
                for( DataSnapshot dataSnapshot1: snapshot.getChildren() )
                {


                    MovieItem n = dataSnapshot1.getValue( MovieItem.class );
                    //System.out.println(n.getName()+" hakan ");
                    movieList1.add( n );
                    if(n.getRevealed() == false){
                        movieListBlue.add(n);


                    }



                }


                maxNumber=movieListBlue.size();
                System.out.println("Blue :" + maxNumber);
                myRecyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mAdapter = new MovieAdapter(getApplicationContext(),movieList1);


        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);


        System.out.println("maxnumber: "+  maxNumber);




    }


}
