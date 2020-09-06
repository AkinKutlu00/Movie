package com.aagames.movieroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aagames.movieroulette.adapters.FriendAdapter;
import com.aagames.movieroulette.adapters.MovieAdapterPlus;
import com.aagames.movieroulette.objects.FriendItem;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<FriendItem> friendlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        recyclerView = findViewById(R.id.friendsRV);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);

        friendlist = new ArrayList<>();

        friendlist.add(new FriendItem("Akın","akinkutlu@gmail.com"));
        friendlist.add(new FriendItem("boom","boom@gmail.com"));
        friendlist.add(new FriendItem("Deniz","deniz@gmail.com"));

        mAdapter = new FriendAdapter(getApplicationContext(),friendlist);
        recyclerView.setAdapter(mAdapter);
    }
}