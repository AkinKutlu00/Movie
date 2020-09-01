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

import com.google.firebase.auth.FirebaseAuth;
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

    String listName;

    //silinecek
    Button button;
    int maxNumber ;//= 100;
    int spinnerNo;
    int mod;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef2 = database.getReference("movielists");
    final ArrayList<MovieItem> movieList1 = new ArrayList<>();
    private FirebaseAuth auth;
    DatabaseReference myRef;
    final ArrayList<Object> movieLists = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mod=0;
        listName = "1";
        final ArrayList<MovieItem> movieList2 = new ArrayList<>();

        auth = FirebaseAuth.getInstance();

        final String id = auth.getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists");

        //silinecek
        final Intent intent = new Intent(this, Login.class);

        button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                auth.signOut();

            }
        });

       //silin sonu


        title = (TextView) findViewById(R.id.title);


        title.setText("Mubi Top 100");

        spinner = findViewById(R.id.spinner);


       // final ArrayList<MovieItem> movieList2 = new ArrayList<>();
        final ArrayList<MovieItem> movieListBlue = new ArrayList<>();



        DatabaseReference imdb = database.getReference("movielists").child("imdb");

        //FirebaseDatabase.getInstance().getReference().child("movielists").child("Rotten Tomatoes").setValue(movieList2);
        //myRef.child("0").child("revealed").setValue(true);
        //movieList1.get(0).setRevealed(true);

        filter = new ArrayList<>();

        filter.add("New Category");
        filter.add("Mubi");
        filter.add("Rotten Tomatoes");
        filter.add("Imdb");
        filter.add("Comedy");
        filter.add("Science Fiction");
        filter.add("Documentary");


        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {

                title.setText(spinner.getSelectedItem().toString()+" (30" +"/"+"100)");

                changeDatabase(position-1+"");

                 if(position == 0){


                    startActivity(new Intent(getApplicationContext(),NewCategory.class));
                }


            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ) {

            }
        });


        TextView tv= (TextView) findViewById(R.id.title);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, filter );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        spinner.setAdapter( adapter );
        spinner.setSelection(1);


        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,10);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);

        random = findViewById(R.id.randomButton);
        random.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int randomNumber;


               do{


                   randomNumber = new Random().nextInt(movieList1.size());
                   System.out.println("Random"+randomNumber+"-----"+movieList1.size());
               }while (movieList1.get(randomNumber).getRevealed() );

                Toast.makeText(getApplicationContext()," "+ randomNumber,Toast.LENGTH_SHORT).show();



                //movieList1.get(randomNumber).setRevealed(true);
                //Toast.makeText(getApplicationContext()," "+ randomNumber,Toast.LENGTH_SHORT).show();

                final Dialog builder=new Dialog(v.getContext());
                View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.moviepopup,null);

                TextView tvname=(TextView)view.findViewById(R.id.name);
                tvname.setText(movieList1.get(randomNumber).getName());

                TextView tvfoto=(TextView)view.findViewById(R.id.foto);
                tvfoto.setText(movieList1.get(randomNumber).getImageCode());

                Button close = (Button) view.findViewById(R.id.close);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();


                    }
                });


                Button yes = (Button) view.findViewById(R.id.yes);
                Button no = (Button) view.findViewById(R.id.no);

                final int finalRandomNumber = randomNumber;
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //movieList1.get(randomNumber).setRevealed(true);
                        myRef.child(listName).child(finalRandomNumber +"").child("revealed").setValue(true);

                        builder.dismiss();

                    }
                });


                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myRef.child(listName).child(finalRandomNumber +"").child("revealed").setValue(false);
                        builder.dismiss();
                    }
                });
                builder.setContentView(view);

                builder.show();

            }
        });



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mod < 2){
                    mod++;;

                }

                if(mod == 0){
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),10);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapter(getApplicationContext(),movieList1, listName);
                    myRecyclerView.setAdapter(mAdapter);


                }else if(mod == 1){
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapterPlus(getApplicationContext(),movieList1,listName);
                    myRecyclerView.setAdapter(mAdapter);


                }else{
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapterBig(getApplicationContext(),movieList1,listName);
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
                    mAdapter = new MovieAdapter(getApplicationContext(),movieList1, listName);
                    myRecyclerView.setAdapter(mAdapter);


                }else if(mod == 1){
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapterPlus(getApplicationContext(),movieList1,listName);
                    myRecyclerView.setAdapter(mAdapter);


                }else if(mod == 2){
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
                    myRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapterBig(getApplicationContext(),movieList1,listName);
                    myRecyclerView.setAdapter(mAdapter);

                }
            }
        });




        mAdapter = new MovieAdapter(getApplicationContext(),movieList1,listName);


        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);


        System.out.println("maxnumber: "+  maxNumber);



    }


    public void changeDatabase(String childName ){
        DatabaseReference db  = myRef.child(childName);

        listName = childName;

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movieList1.clear();

                maxNumber = (int) snapshot.getChildrenCount();
                for( DataSnapshot dataSnapshot1: snapshot.getChildren() )
                {
                    MovieItem n = dataSnapshot1.getValue( MovieItem.class );
                    //System.out.println(n.getName()+" hakan ");
                    movieList1.add( n );
                }

                System.out.println("Blue :" + maxNumber);
                myRecyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mod =0;
        mLayoutManager = new GridLayoutManager(getApplicationContext(),10);
        mAdapter = new MovieAdapter(getApplicationContext(),movieList1,listName);
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);
    }


    }





