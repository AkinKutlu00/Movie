package com.aagames.movieroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager2;

    private Button random;
    Button plus;
    Button minus;
    Spinner spinner;
    List< String > filter;
    TextView title;

    //silinecek
    Button button;

    int maxNumber;
    int spinnerNo;
    int mod;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mod=0;

        //silinecek
        final Intent intent = new Intent(this, Login.class);

        button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);

            }
        });

       //silin sonu


        title = (TextView) findViewById(R.id.title);


        title.setText("Mubi Top 100");

        spinner = findViewById(R.id.spinner);

        final ArrayList<MovieItem> movieList1 = new ArrayList<>();
        final ArrayList<MovieItem> movieListBlue = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Imdb");


        //myRef.child("0").child("revealed").setValue(true);
        //movieList1.get(0).setRevealed(true);

        filter = new ArrayList<>();

        filter.add("Imdb");
        filter.add("Rotten Tomatoes");
        filter.add("Mubi");
        filter.add("Comedy");
        filter.add("Science Fiction");
        filter.add("Documentary");
        filter.add("New Category");


        TextView tv= (TextView) findViewById(R.id.title);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, filter );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        spinner.setAdapter( adapter );
        spinner.setSelection(0);


        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,10);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);




        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mod < 2){
                    mod++;;

                }

                if(mod == 0){
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),10);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapter(getApplicationContext(),movieList1);
                    myRecyclerView.setAdapter(mAdapter);


                }else if(mod == 1){
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapterPlus(getApplicationContext(),movieList1);
                    myRecyclerView.setAdapter(mAdapter);


                }else{
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapterPlus(getApplicationContext(),movieList1);
                    myRecyclerView.setAdapter(mAdapter);

                }

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mod > 0){
                    mod--;

                }

                if(mod == 0){
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),10);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapter(getApplicationContext(),movieList1);
                    myRecyclerView.setAdapter(mAdapter);


                }else if(mod == 1){
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapterPlus(getApplicationContext(),movieList1);
                    myRecyclerView.setAdapter(mAdapter);


                }else{
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapterPlus(getApplicationContext(),movieList1);
                    myRecyclerView.setAdapter(mAdapter);

                }
            }
        });


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

        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {

                title.setText(spinner.getSelectedItem().toString()+" (30" +"/"+"100)");

                if(position == 6){


                    startActivity(new Intent(getApplicationContext(),NewCategory.class));
                }


            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ) {

            }
        });
    }



    }





