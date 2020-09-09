package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.MovieSearchShowAdapter;
import com.aagames.movieroulette.objects.MovieList;
import com.aagames.movieroulette.tmdb.api.TmdbClient;
import com.aagames.movieroulette.tmdb.constant.ConstantTmdb;
import com.aagames.movieroulette.tmdb.data.MovieResult;
import com.aagames.movieroulette.tmdb.data.MovieSearchListRecieved;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mSearchLayoutManager;
    private RecyclerView.Adapter mSearchAdapter;


    SearchView searchView;
    RecyclerView recyclerView;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

            searchView = findViewById(R.id.search_view);
            recyclerView = findViewById(R.id.searchRV);

        ArrayList<MovieResult> movieResultsArray  = new ArrayList<MovieResult>();

        MovieList movieList = new MovieList();

        mSearchLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mSearchLayoutManager);
        mSearchAdapter = new MovieSearchShowAdapter(getApplicationContext(),movieResultsArray,-1,movieList);
        recyclerView.setAdapter(mSearchAdapter);

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


        }

        public void searchMovie(String query){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ConstantTmdb.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            TmdbClient myInterface = retrofit.create(TmdbClient.class);

            Call<MovieSearchListRecieved> call=  myInterface.getMovies(ConstantTmdb.APIKEY,query);

            call.enqueue(new Callback<MovieSearchListRecieved>() {
                @Override
                public void onResponse(@NonNull Call<MovieSearchListRecieved> call, @NonNull Response<MovieSearchListRecieved> response) {

                    MovieSearchListRecieved results= response.body();
                    MovieList movieList = new MovieList();
                    if(results!=null){

                       mSearchAdapter = new MovieSearchShowAdapter(getApplicationContext(), (ArrayList<MovieResult>) response.body().getMovieResults(), -1, movieList);
                        recyclerView.setAdapter(mSearchAdapter);
                    }



                }

                @Override
                public void onFailure(Call<MovieSearchListRecieved> call, Throwable t) {
                    System.out.println("olmadı: ");
                }
            });
        }

    }
