package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.aagames.movieroulette.dialogs.AddCatDialog;
import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.MovieAdapter;
import com.aagames.movieroulette.adapters.MovieAdapterBig;
import com.aagames.movieroulette.adapters.MovieAdapterPlus;
import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.objects.MovieList;
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
    private FirebaseAuth auth = FirebaseAuth.getInstance();

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
    int revealedNumber;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<MovieItem> movieList1 = new ArrayList<>();

    DatabaseReference databaseReferenceProfile;
    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle myToggle;
    MovieList currentList;

    Toolbar toolbar;
    final String id = auth.getUid();
    final ArrayList<MovieList> allList = new ArrayList<>();

    DatabaseReference myRef;
    int height;
    int width;
     String titleName;

    ArrayList<String> movieListNames;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_adapter_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mod=0;
        listName = "1";
        final ArrayList<MovieItem> movieList2 = new ArrayList<>();
        titleName = getIntent().getStringExtra( "categoryname" );

        toolbar = findViewById( R.id.toolBar );
        setSupportActionBar( toolbar );


        databaseReferenceProfile = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "profile" );


        toolbar.setTitle(titleName);
        movieListNames= new ArrayList<>();

        myDrawerLayout = (DrawerLayout)findViewById( R.id.drawer );
        myToggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.string.open, R.string.close );
        myDrawerLayout.addDrawerListener( myToggle );
        myToggle.syncState();
        NavigationView navigationView = findViewById( R.id.navigation_view );
        navigationView.setNavigationItemSelectedListener( this );
        final View headerView = navigationView.getHeaderView( 0 );

        final TextView name = ( TextView ) headerView.findViewById( R.id.name );
        final TextView mail = ( TextView ) headerView.findViewById( R.id.mail );
        final TextView count = ( TextView ) headerView.findViewById( R.id.count );
        final ImageButton showNotif = (ImageButton) headerView.findViewById( R.id.notif );

        showNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });

        FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("notifications").addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                count.setText(""+(int) dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {

            }
        });


        databaseReferenceProfile.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                String theName = dataSnapshot.child( "name" ).getValue( String.class );
                String theEmail = dataSnapshot.child( "email" ).getValue( String.class );

                name.setText(theName);
                mail.setText(theEmail);
            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {

            }
        });

        myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterable< DataSnapshot > cList = snapshot.getChildren();

                allList.clear();
                for ( DataSnapshot list : cList )
                {
                    MovieList thelist = list.getValue( MovieList.class );
                    allList.add( thelist );
                    movieListNames.add(thelist.getName());
                }


                if( allList.size() != 0 ) {
                    for ( int i = 0; i < allList.size(); i++ ) {

                        // When found the note set texts of text and title EditText to the name and the text of note
                        //      Change their sizes and styles according to the information taken from Firebase
                        if( allList.get( i ).getName().equals( titleName ) )
                        {


                            currentList = allList.get( i );
                            movieList1 = allList.get( i ).getMovies();
                            listName = ""+i;
                            updateRv();



                        }
                    }
                }

                revealedNumber=0;
                for(int i=0;i<movieList1.size();i++){
                    //Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/original"+ movieList1.get(i).getImageCode()).preload();

                    if(movieList1.get(i).getRevealed()){
                        revealedNumber++;
                    }

                }

                getSupportActionBar().setTitle(titleName+" ("+revealedNumber+ "/"+ movieList1.size()+")");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,10);
        

        //ImageView imageView = findViewById(R.id.imageView);
        //Glide.with(MainActivity.this).load("https://image.tmdb.org/t/p/original/5KCVkau1HEl7ZzfPsKAPM0sMiKc.jpg").into(imageView);
        random = findViewById(R.id.randomButton);
        random.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int randomNumber;
               if( movieList1.size() == revealedNumber){
                   Toast.makeText(getApplicationContext(),"You have watched all the movies on this list.",Toast.LENGTH_SHORT).show();

               }else{
                   do{


                       randomNumber = new Random().nextInt(movieList1.size());
                       System.out.println("Random"+randomNumber+"-----"+movieList1.size());
                   }while (movieList1.get(randomNumber).getRevealed() );

                   Intent intent = new Intent(MainActivity.this, MoviePopUp.class);
                   intent.putExtra( "MovieName", movieList1.get(randomNumber).getName());
                   intent.putExtra( "MovieID", movieList1.get(randomNumber).getMovieid());
                   intent.putExtra( "MovieImageCode", movieList1.get(randomNumber).getImageCode());
                   intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                   startActivity(intent);



               }



            }
        });



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;



        this.getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        this.getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setTitle(titleName+" (0/0)");
        mAdapter = new MovieAdapterPlus(getApplicationContext(),movieList1,listName,height,width);



        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);




    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()) {
            case R.id.left:
                if(mod > 0){
                    item.setIcon(R.drawable.ic_baseline_apps_24);
                    mod--;
                }else {
                    item.setIcon(R.drawable.ic_baseline_movie_24);
                    mod++;
                }
                updateRv();
                break;
            case R.id.point:
                Intent intent = new Intent( getApplicationContext(), WatchedPopUp.class );
                intent.putExtra("title",currentList.getName());
                startActivity( intent);
                break;

        }


        // Navigation Drawer handling
        if ( myToggle.onOptionsItemSelected(item) ) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected( @NonNull MenuItem menuItem ) {

        switch (menuItem.getItemId()){
            case R.id.profile:
                break;
            case R.id.categories:
                startActivity( new Intent(getApplicationContext(), Categories.class ) );
                break;
            case R.id.newCategory:
                AddCatDialog catDialog = new AddCatDialog(allList.size(), movieListNames);
                catDialog.show(getSupportFragmentManager(),"example");
                break;
            case R.id.uptodate:
                startActivity( new Intent(getApplicationContext(), UpToDateCategories.class ) );
                break;
            case R.id.friends:
                startActivity( new Intent(getApplicationContext(), FriendsActivity.class ) );
                break;
            case R.id.log_out:
                auth.signOut();
                startActivity( new Intent( getApplicationContext(), Login.class ) );
                finish();
                break;
        }

        //close navigation drawer
        myDrawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }

    public void updateRv(){
        if(mod == 0){

            mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
            myRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MovieAdapterPlus(getApplicationContext(),movieList1,listName,height,width);
            myRecyclerView.setAdapter(mAdapter);


        }else if(mod == 1){

            mLayoutManager = new GridLayoutManager(getApplicationContext(),10);
            myRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MovieAdapter(getApplicationContext(),movieList1, listName, height,width);
            myRecyclerView.setAdapter(mAdapter);


        }

    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }



}





