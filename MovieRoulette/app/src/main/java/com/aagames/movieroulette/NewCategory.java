package com.aagames.movieroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewCategory extends AppCompatActivity {

    Button create;
    String categoryName;
    EditText catNameEt;
    EditText movieNameEt;
    String id;
    FirebaseAuth auth;
    int next;
    Button add;
    MovieList ml;
    int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        final DatabaseReference listRef =FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "movielists" );
        create = (Button) findViewById(R.id.createCat);
        catNameEt = (EditText) findViewById(R.id.catNameEt);
        movieNameEt = (EditText) findViewById(R.id.movieNameEt);


        final ArrayList<MovieList> allList = new ArrayList<>();
        
        add = (Button) findViewById(R.id.add);
        add.setVisibility(View.INVISIBLE);

         ml = new MovieList();

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


                         if( allList.get( i ).getName().equals( categoryName ) )
                         {

                             ml = allList.get( i );

                             currentPosition = i;

                         }
                     }
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

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryName = catNameEt.getText().toString();
                listRef.child( ""+next ).setValue(new MovieList(categoryName));
                create.setVisibility(View.INVISIBLE);
                catNameEt.setFocusable(false);
                Toast.makeText(getApplicationContext(),categoryName+" category has created. You can add movies.",Toast.LENGTH_SHORT).show();
                add.setVisibility(View.VISIBLE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 String movieName = movieNameEt.getText().toString();
                 //System.out.println(movieName+"o ne oyle");
              // System.out.println("all "+allList.get(0).getName());

                // System.out.println("boş olma "+ml.getName());


                //ml.addMovie(new MovieItem("movieName", " imagecode"));
                 ml.addMovie(new MovieItem(movieName, movieName+" imagecode"));
                 FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists").child(""+currentPosition).setValue(ml);

               //FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists").setValue(movieLists);
            }



        });




    }

}
