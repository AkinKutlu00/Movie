package com.aagames.movieroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView myRecyclerView;
    ArrayList<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        categories = new ArrayList<>();

        categories.add("AAA");
        categories.add("BBB");
        categories.add("CCC");


        mAdapter = new CategoryAdapter(getApplicationContext(),categories);

        myRecyclerView = findViewById(R.id.rv);
        //myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,3);
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);



    }
}