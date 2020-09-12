package com.aagames.movieroulette.activities;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.tmdb.api.TmdbRetrofit;
import com.aagames.movieroulette.tmdb.constant.ConstantTmdb;
import com.aagames.movieroulette.tmdb.data.castandcrew.MovieInfo;
import com.aagames.movieroulette.tmdb.data.search.MovieResult;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePopUp extends Activity {
    TextView textViewName;
    ImageView imageView;
    Button close;
    Button moreInfo;
    TextView infoTv;
    TextView crewTv;
    int mod;
    String overview;
    String crew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pop_up);

        final String movieName = getIntent().getStringExtra( "MovieName" );
        final int movieID = getIntent().getIntExtra( "MovieID" ,-1);
        final String movieImageCode = getIntent().getStringExtra( "MovieImageCode" );

        overview="";
        crew="";

        mod=0;

        textViewName = findViewById(R.id.name);
        imageView = findViewById(R.id.imageViewPop);
        moreInfo = findViewById(R.id.infoBtn);
        infoTv = findViewById(R.id.infoTv);
        crewTv = findViewById(R.id.crewTv);

        infoTv.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        getWindow().setLayout((int) (width*.9),(int) (height*.7));
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mod==0){
                    infoTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    infoTv.setPadding(50,20,20,0);
                    crewTv.setPadding(50,20,20,0);
                    moreInfo.setText("Less Info");
                    mod++;

                    getWindow().setLayout((int) (width*.9),(int) (height));
                    getMovieInfo(movieID);


                }else{
                    infoTv.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                    moreInfo.setText("More Info");
                    infoTv.setText("");
                    crewTv.setText("");
                    mod--;
                    getWindow().setLayout((int) (width*.9),(int) (height*.7));

                }


            }
        });




        textViewName.setText(movieName);

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/original"+movieImageCode).override((int)(width*.625),(int)(height*.55)).into( imageView);




        close = findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MoviePopUp.super.onBackPressed();

            }
        });


    }

    public void getMovieInfo(int movieId){
        crew="";
        overview="";



        Call<MovieInfo> call= TmdbRetrofit.getInstance().getApiService().getMovieCredits(movieId,ConstantTmdb.APIKEY);

        call.enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(@NonNull Call<MovieInfo> call, @NonNull Response<MovieInfo> response) {

                MovieInfo results= response.body();

                if(results!=null){

                    for(int i=0; i<results.getCrew().size(); i++){
                        if(results.getCrew().get(i).getJob().equals("Director")){
                            crew= crew+"Director: "+results.getCrew().get(i).getName()+"\n";

                            i=results.getCrew().size();
                        }

                    }

                    int counter;
                    counter =results.getCast().size();
                    if(counter>4){
                        counter=4;
                    }

                    crew= crew+"Crew :";
                    for(int i=0; i<counter; i++){

                            crew= crew+results.getCast().get(i).getName()+", ";


                    }

                    crewTv.setText(crew);

                }



            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {
                System.out.println("olmadı: ");
            }
        });


        Call<MovieResult> call2= TmdbRetrofit.getInstance().getApiService().getMovieInformation(movieId,ConstantTmdb.APIKEY);
        call2.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {

                MovieResult results= response.body();

                overview = overview + "Genres: "+results.getGenres()+"\n";

                overview = overview + "Vote Average:"+ results.getVoteAverage()+"\n";

                overview= overview + "Overview: "+results.getOverview();



                infoTv.setText(overview);


            }


            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                System.out.println("olmadı: ");
            }
        });




    }
}