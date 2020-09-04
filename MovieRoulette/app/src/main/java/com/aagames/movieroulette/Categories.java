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
    ArrayList<String> adminCategories;
    private FirebaseAuth auth;
    ArrayList<String> missing;


    Button logOut;
    Button newCategory;
    Button send;

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


        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SendList.class));
            }
        });

        categories = new ArrayList<>();
        adminCategories = new ArrayList<>();

        mAdapter = new CategoryAdapter(getApplicationContext(), categories);

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
                openDialog();
                //startActivity(new Intent(getApplicationContext(), NewCategory.class));


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

        adminRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminCategories.clear();
                // lists = new ArrayList<MovieList>();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    MovieList list =  ( MovieList ) shot.getValue( MovieList.class );

                    //lists.add( list );
                    adminCategories.add( list.getName() );
                    //mAdapter.notifyDataSetChanged();
                    System.out.println("admin"+ list.getName() );

                }

                for(int i=0;i<adminCategories.size();i++){
                    //System.out.println("lost of gravity:" + adminCategories.get(i) );
                    String miss=findMissing(categories, adminCategories.get(i));
                    if(!miss.equals("")){
                        missing.add(miss);
                    }



                }

                for(int i=0;i<missing.size();i++){
                    // bulduklarını ekle


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