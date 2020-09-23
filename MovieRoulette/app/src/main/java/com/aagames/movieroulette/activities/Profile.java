package com.aagames.movieroulette.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.FriendAdapter;
import com.aagames.movieroulette.adapters.MovieAdapterPlus;
import com.aagames.movieroulette.adapters.ProfileMovieAdapter;
import com.aagames.movieroulette.adapters.UpToDateMoviesAdapter;
import com.aagames.movieroulette.tmdb.data.search.MovieResult;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    int height;
    int width;

    ArrayList<Integer> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        list= new ArrayList<>();
        list.add(123);
        list.add(124);
        list.add(125);
        list.add(126);

        recyclerView = findViewById(R.id.recyleViewProfile);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ProfileMovieAdapter(getApplicationContext(),list,height,width);
        recyclerView.setAdapter(mAdapter);









    }
}