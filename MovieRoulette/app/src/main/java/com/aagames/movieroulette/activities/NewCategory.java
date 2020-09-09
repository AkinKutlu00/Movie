package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.MovieAdapterPlus;
import com.aagames.movieroulette.adapters.MovieSearchShowAdapter;
import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.objects.MovieList;
import com.aagames.movieroulette.tmdb.api.TmdbClient;
import com.aagames.movieroulette.tmdb.constant.ConstantTmdb;
import com.aagames.movieroulette.tmdb.data.MovieResult;
import com.aagames.movieroulette.tmdb.data.MovieSearchListRecieved;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewCategory extends AppCompatActivity {

    Button create;
    SearchView searchView;

    TextView catNameEt;

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

        searchView = findViewById(R.id.search_view);

        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        final DatabaseReference listRef =FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "movielists" );

        catNameEt = (TextView) findViewById(R.id.catNameEt);


        final String listname = getIntent().getStringExtra( "listname" );

        catNameEt.setText(listname);
        myRecyclerView = (RecyclerView)  findViewById(R.id.searchRV);

        ml = new MovieList();

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(mLayoutManager);

        //mAdapter.notifyDataSetChanged();

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

                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });







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


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchMovie(newText);
                return false;
            }
        });




       /* add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 ml.addMovie(new MovieItem(movieName, movieName+" imagecode",-1));
                 FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists").child(""+currentPosition).setValue(ml);

                  mAdapter = new MovieAdapterPlus(getApplicationContext(),ml.getMovies(), listname);
                  myRecyclerView.setAdapter(mAdapter);
                  mAdapter.notifyDataSetChanged();


            }



        });*/





    }

    public void searchMovie(String query){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantTmdb.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbClient myInterface = retrofit.create(TmdbClient.class);

        Call<MovieSearchListRecieved> call=  myInterface.getMovies(ConstantTmdb.APIKEY,query,false);

        call.enqueue(new Callback<MovieSearchListRecieved>() {
            @Override
            public void onResponse(@NonNull Call<MovieSearchListRecieved> call, @NonNull Response<MovieSearchListRecieved> response) {

                MovieSearchListRecieved results= response.body();

                if(results!=null){
                    mAdapter = new MovieSearchShowAdapter(getApplicationContext(),(ArrayList<MovieResult>) response.body().getMovieResults(), currentPosition,ml);
                    myRecyclerView.setAdapter(mAdapter);
                    System.out.println("current"+currentPosition);


                    //mSearchAdapter = new MovieSearchShowAdapter(getApplicationContext(), (ArrayList<MovieResult>) response.body().getMovieResults(), -1, movieList);
                    //recyclerView.setAdapter(mSearchAdapter);
                }



            }

            @Override
            public void onFailure(Call<MovieSearchListRecieved> call, Throwable t) {
                System.out.println("olmadı: ");
            }
        });
    }

}
