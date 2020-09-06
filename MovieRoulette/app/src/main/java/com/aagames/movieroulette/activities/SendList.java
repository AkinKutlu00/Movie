package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.CategoryAdapter;
import com.aagames.movieroulette.adapters.SendAdapter;
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
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    ArrayList<MovieList> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_list);

        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        final ArrayList<MovieList> allList = new ArrayList<>();

        final String recieverId = getIntent().getStringExtra( "id" );

        categories = new ArrayList<>();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categories.clear();
                // lists = new ArrayList<MovieList>();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    MovieList list =  ( MovieList ) shot.getValue( MovieList.class );

                    //lists.add( list );
                    categories.add( list );
                    mAdapter.notifyDataSetChanged();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        RecyclerView recyclerView = findViewById(R.id.send_rv);
        recyclerView.setHasFixedSize(true);
        mAdapter = new SendAdapter(getApplicationContext(), categories, recieverId);
        mLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);




    }
}