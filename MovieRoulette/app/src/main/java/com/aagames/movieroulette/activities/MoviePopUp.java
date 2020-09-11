package com.aagames.movieroulette.activities;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.aagames.movieroulette.R;
import com.bumptech.glide.Glide;

public class MoviePopUp extends Activity {
    TextView textViewName;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pop_up);

        final String movieName = getIntent().getStringExtra( "MovieName" );
        final int movieID = getIntent().getIntExtra( "MovieID" ,-1);
        final String movieImageCode = getIntent().getStringExtra( "MovieImageCode" );


        textViewName = findViewById(R.id.name);
        imageView = findViewById(R.id.imageViewPop);


        textViewName.setText(movieName);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int  width = displayMetrics.widthPixels;

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/original"+movieImageCode).override((int)(width*.625),(int)(height*.55)).into( imageView);


        getWindow().setLayout((int) (width*.9),(int) (height*.85));
    }
}