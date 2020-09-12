package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.MovieAdapterPlus;
import com.aagames.movieroulette.adapters.UpToDateMoviesAdapter;
import com.aagames.movieroulette.tmdb.api.TmdbRetrofit;
import com.aagames.movieroulette.tmdb.constant.ConstantTmdb;
import com.aagames.movieroulette.tmdb.data.search.MovieResult;
import com.aagames.movieroulette.tmdb.data.search.MovieSearchListRecieved;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpToDateCategories extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    RecyclerView recyclerView;
    ArrayList<MovieResult> list;
    int height;
    int width;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_to_date_categories);

        recyclerView=findViewById(R.id.recyleView);

        list = new ArrayList<>();

        Button one = (Button) findViewById(R.id.now);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) findViewById(R.id.popular);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.top);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.upcoming);
        four.setOnClickListener(this);



        findList("now_playing");
        //findList("popular");
        //findList("top_rated");
       // findList("upcoming");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
         width = displayMetrics.widthPixels;

        mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new UpToDateMoviesAdapter(getApplicationContext(),list,height,width);
        recyclerView.setAdapter(mAdapter);




    }

    public void findList(String name){

        Call<MovieSearchListRecieved> call= TmdbRetrofit.getInstance().getApiService().getList(name,ConstantTmdb.APIKEY);


        call.enqueue(new Callback<MovieSearchListRecieved>() {
            @Override
            public void onResponse(@NonNull Call<MovieSearchListRecieved> call, @NonNull Response<MovieSearchListRecieved> response) {

                MovieSearchListRecieved results= response.body();

                if(results!=null){
                    list = (ArrayList<MovieResult>) results.getMovieResults();


                   System.out.println("Doğru mu:"+results.getMovieResults().get(0).getOriginalTitle());
                    mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                    recyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new UpToDateMoviesAdapter(getApplicationContext(),list,height,width);
                    recyclerView.setAdapter(mAdapter);


                }



            }

            @Override
            public void onFailure(Call<MovieSearchListRecieved> call, Throwable t) {
                System.out.println("olmadı: ");
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.now:
                // do your code
                findList("now_playing");
                break;
            case R.id.popular:
                // do your code
                findList("popular");
                break;
            case R.id.top:
                findList("top_rated");
                // do your code
                break;
            case R.id.upcoming:
                findList("upcoming");
                break;
    }


}}