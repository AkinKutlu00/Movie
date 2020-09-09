package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aagames.movieroulette.AddCatDialog;
import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.CategoryAdapter;
import com.aagames.movieroulette.objects.MovieList;
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
    ArrayList<String> adminCategories;
    private FirebaseAuth auth;
    ArrayList<String> missing;

    Button newCategory;


    //ArrayList<MovieList> lists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        auth = FirebaseAuth.getInstance();
        final String id = auth.getUid();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists");

        DatabaseReference adminRef = FirebaseDatabase.getInstance().getReference().child( "ListOfMovies" );

        missing = new ArrayList<>();

        categories = new ArrayList<>();
        adminCategories = new ArrayList<>();

        mAdapter = new CategoryAdapter(getApplicationContext(), categories);



        newCategory = (Button) findViewById(R.id.newcat);

        newCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
               // startActivity(new Intent(getApplicationContext(), SearchActivity.class));


            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categories.clear();
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

        myRecyclerView = findViewById(R.id.rv);
        //myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,3);
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);



    }

    public static String findMissing(ArrayList<String> list , String target) {
        int pos = 0;
        while ( pos < list.size()-1 && !(list.get(pos).equals(target)) ){
            pos++;
        }

        if ( (list.get(pos)).equals(target)){
            return "";
        }else{
            return target;
        }

    }


    public void openDialog() {
        AddCatDialog catDialog = new AddCatDialog(categories.size());
        catDialog.show(getSupportFragmentManager(),"example");
    }




}