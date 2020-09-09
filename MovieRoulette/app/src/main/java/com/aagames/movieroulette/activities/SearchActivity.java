package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.MovieSearchShowAdapter;
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
    private RecyclerView myRecyclerViewSearch;

    SearchView searchView;
    RecyclerView recyclerView;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

            searchView = findViewById(R.id.search_view);
            recyclerView = findViewById(R.id.searchRV);

        //

        mSearchLayoutManager= new LinearLayoutManager(getApplicationContext());
        myRecyclerViewSearch.setLayoutManager(mSearchLayoutManager);
        myRecyclerViewSearch.setAdapter(mSearchAdapter);

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

                    if(results!=null){

                       // mSearchAdapter = new MovieSearchShowAdapter(getApplicationContext(), (ArrayList<MovieResult>) response.body().getMovieResults(), currentPosition, ml);

                    }





                /*for(int i=0; i<response.body().getMovieResults().size(); i++){
                    System.out.println("Title: " + response.body().getMovieResults().get(i).getOriginalTitle());
                    System.out.println("Date: " + response.body().getMovieResults().get(i).getReleasedate());
                    System.out.println("Vote Rate: " + response.body().getMovieResults().get(i).getVoteAverage());

                    System.out.println("ID: " + response.body().getMovieResults().get(i).getId());
                    System.out.println("Poster: " + response.body().getMovieResults().get(i).getPosterPath());
                    System.out.println("------------------------------------------------------ ");

                }*/


                }

                @Override
                public void onFailure(Call<MovieSearchListRecieved> call, Throwable t) {
                    System.out.println("olmadı: ");
                }
            });
        }

    }
