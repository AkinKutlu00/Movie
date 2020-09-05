package com.aagames.movieroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager2;

    private Button random;
    Button plus;
    Button minus;

    List< String > filter;
    TextView title;
    ArrayList<MovieList> lists;

    String listName;

    //silinecek
    Button button;
    int maxNumber ;
    int mod;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<MovieItem> movieList1 = new ArrayList<>();
    private FirebaseAuth auth;

    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle myToggle;
    MovieList currentList;

    Toolbar toolbar;


    MovieList movies = new MovieList( "Mubi" );

    DatabaseReference myRef;
    final DatabaseReference myRef5 = FirebaseDatabase.getInstance().getReference().child( "ListOfMovies" ).child( "mubi" );
    final ArrayList<Object> movieLists = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mod=0;
        listName = "1";
        final ArrayList<MovieItem> movieList2 = new ArrayList<>();

        toolbar = findViewById( R.id.toolBar );
        setSupportActionBar( toolbar );


        TextView tv= (TextView) findViewById(R.id.title);

        auth = FirebaseAuth.getInstance();
        title = (TextView) findViewById(R.id.title);

        final String titleName = getIntent().getStringExtra( "categoryname" );
        toolbar.setTitle(titleName);
        title.setText(titleName);

        final ArrayList<MovieList> allList = new ArrayList<>();

        myDrawerLayout = (DrawerLayout)findViewById( R.id.drawer );
        myToggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.string.open, R.string.close );
        myDrawerLayout.addDrawerListener( myToggle );
        myToggle.syncState();
        NavigationView navigationView = findViewById( R.id.navigation_view );
        navigationView.setNavigationItemSelectedListener( this );
        View headerView = navigationView.getHeaderView( 0 );


        final String id = auth.getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists");

        //??????????????????????

        myRef.addValueEventListener(new ValueEventListener() {
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

                        // When found the note set texts of text and title EditText to the name and the text of note
                        //      Change their sizes and styles according to the information taken from Firebase
                        if( allList.get( i ).getName().equals( titleName ) )
                        {
                            title.setText( ( allList.get( i ) ).getName() );

                            currentList = allList.get( i );
                            movieList1 = allList.get( i ).movies;
                            listName = ""+i;
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
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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

        Button cat = (Button) findViewById(R.id.cat);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), Categories.class) );
            }
        });




        DatabaseReference imdb = database.getReference("movielists").child("imdb");


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

                //Toast.makeText(getApplicationContext()," "+ randomNumber,Toast.LENGTH_SHORT).show();



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
                        myRef.child(listName).child("movies").child(finalRandomNumber +"").child("revealed").setValue(true);

                        builder.dismiss();

                    }
                });


                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myRef.child(listName).child("movies").child(finalRandomNumber +"").child("revealed").setValue(false);
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

        this.getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        this.getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setTitle(titleName);
        mAdapter = new MovieAdapter(getApplicationContext(),movieList1,listName);



        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);


        System.out.println("maxnumber: "+  maxNumber);


    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        // Navigation Drawer handling
        if ( myToggle.onOptionsItemSelected(item) ) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected( @NonNull MenuItem menuItem ) {
        if( menuItem.getItemId() == R.id.profile ) {
            //Intent intent = new Intent( getApplicationContext(), ProfileActivity.class );
            //startActivity( intent );
        }

        else if( menuItem.getItemId() == R.id.categories ) {
            startActivity( new Intent(getApplicationContext(), Categories.class ) );
        }

        else if( menuItem.getItemId() == R.id.friends ) {
           // Intent intent = new Intent( getApplicationContext(), Slider.class );
            //startActivity(intent);
        }

        else if( menuItem.getItemId() == R.id.log_out ) {
            auth.signOut();
            startActivity( new Intent( getApplicationContext(), Login.class ) );
            finish();
        }


        //close navigation drawer
        myDrawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }


}





