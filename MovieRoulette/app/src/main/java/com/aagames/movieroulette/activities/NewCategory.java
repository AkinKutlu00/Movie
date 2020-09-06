package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.MovieAdapterPlus;
import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.objects.MovieList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewCategory extends AppCompatActivity {

    Button create;

    TextView catNameEt;
    EditText movieNameEt;
    String id;
    FirebaseAuth auth;
    int next;
    Button add;
    MovieList ml;
    int currentPosition;
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);


        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        final DatabaseReference listRef =FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "movielists" );

        catNameEt = (TextView) findViewById(R.id.catNameEt);
        movieNameEt = (EditText) findViewById(R.id.movieNameEt);

        final String listname = getIntent().getStringExtra( "listname" );

        catNameEt.setText(listname);
        myRecyclerView = (RecyclerView)  findViewById(R.id.mRV);

        ml = new MovieList();

        mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        myRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MovieAdapterPlus(getApplicationContext(),ml.getMovies(), listname);
        myRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        final ArrayList<MovieList> allList = new ArrayList<>();
        
        add = (Button) findViewById(R.id.add);
        //add.setVisibility(View.INVISIBLE);



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


                 if( allList.size() != 0 ) {
                     for ( int i = 0; i < allList.size(); i++ ) {


                         if( allList.get( i ).getName().equals( listname ) )
                         {

                             ml = allList.get( i );

                             currentPosition = i;

                         }
                     }
                     mAdapter = new MovieAdapterPlus(getApplicationContext(),ml.getMovies(), listname);
                     myRecyclerView.setAdapter(mAdapter);
                     mAdapter.notifyDataSetChanged();
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });





        //System.out.println("Noluyo öyle"+categoryName);




        listRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                next=0;
                // lists = new ArrayList<MovieList>();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    next++;


                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




              movieName = movieNameEt.getText().toString();

                 //System.out.println(movieName+"o ne oyle");
              // System.out.println("all "+allList.get(0).getName());

                // System.out.println("boş olma "+ml.getName());


                //ml.addMovie(new MovieItem("movieName", " imagecode"));
                 ml.addMovie(new MovieItem(movieName, movieName+" imagecode"));
                 FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists").child(""+currentPosition).setValue(ml);


            


                  mAdapter = new MovieAdapterPlus(getApplicationContext(),ml.getMovies(), listname);
                  myRecyclerView.setAdapter(mAdapter);
                  mAdapter.notifyDataSetChanged();

               //FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists").setValue(movieLists);
            }



        });




    }

}
