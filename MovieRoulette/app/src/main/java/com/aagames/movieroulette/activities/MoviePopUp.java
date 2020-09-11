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

import com.aagames.movieroulette.R;
import com.bumptech.glide.Glide;

public class MoviePopUp extends Activity {
    TextView textViewName;
    ImageView imageView;
    Button close;
    Button moreInfo;
    TextView infoTv;
    int mod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pop_up);

        final String movieName = getIntent().getStringExtra( "MovieName" );
        final int movieID = getIntent().getIntExtra( "MovieID" ,-1);
        final String movieImageCode = getIntent().getStringExtra( "MovieImageCode" );


        mod=0;

        textViewName = findViewById(R.id.name);
        imageView = findViewById(R.id.imageViewPop);
        moreInfo = findViewById(R.id.infoBtn);
        infoTv = findViewById(R.id.infoTv);

        infoTv.setVisibility(View.INVISIBLE);
        infoTv.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mod==0){
                    infoTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    infoTv.setVisibility(View.VISIBLE);
                    infoTv.setPadding(50,20,20,0);
                    moreInfo.setText("Less Info");
                    mod++;
                    getWindow().setLayout((int) (width*.9),(int) (height));

                }else{
                    infoTv.setVisibility(View.INVISIBLE);
                    infoTv.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                    moreInfo.setText("More Info");
                    mod--;
                    getWindow().setLayout((int) (width*.9),(int) (height*.7));

                }


            }
        });




        textViewName.setText(movieName);




        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/original"+movieImageCode).override((int)(width*.625),(int)(height*.55)).into( imageView);


        getWindow().setLayout((int) (width*.9),(int) (height*.7));

        close = findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MoviePopUp.super.onBackPressed();

            }
        });


    }
}