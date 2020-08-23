package com.aagames.movieroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<MovieItem> movieList1 = new ArrayList<>();
        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));

        movieList1.add(new MovieItem("Snowpiercer","Snowpiercer image"));
        movieList1.add(new MovieItem("Parasite","Parasite image"));
        movieList1.add(new MovieItem("Watchmen","Watchmen image"));
        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.get(0).setRevealed(true);

        final int maxNumber= movieList1.size();

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,10);
        mAdapter = new MovieAdapter(movieList1);

        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);

        random = findViewById(R.id.randomButton);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(maxNumber);
                Toast.makeText(getApplicationContext()," "+ randomNumber,Toast.LENGTH_SHORT).show();

                movieList1.get(randomNumber).setRevealed(true);
                Toast.makeText(getApplicationContext()," "+ randomNumber,Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();


            }
        });


    }


}
