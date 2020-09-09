package com.aagames.movieroulette.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.activities.SendList;
import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.objects.MovieList;
import com.aagames.movieroulette.objects.UserItem;
import com.aagames.movieroulette.tmdb.data.MovieResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MovieSearchShowAdapter extends RecyclerView.Adapter<MovieSearchShowAdapter.MovieSearchShowViewHolder> {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    final String id = auth.getUid();
    final DatabaseReference listRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "friends" );
    int currentPosition;

    private ArrayList<MovieResult> movieResultsArray;
    MovieList ml;
    Context context;




    public MovieSearchShowAdapter(Context context, ArrayList<MovieResult> movieResultsArray, int currentPosition, MovieList ml){
        this.movieResultsArray=movieResultsArray;
        this.context=context;
        this.currentPosition = currentPosition;
        this.ml=ml;


    }


    @NonNull
    @Override
    public MovieSearchShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_movie_show_card_view, parent, false);
        MovieSearchShowViewHolder mvh = new MovieSearchShowViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieSearchShowViewHolder holder, final int position) {

        final MovieResult currentItem = movieResultsArray.get(position);

        holder.movieNameTv.setText(currentItem.getOriginalTitle() + "(" +currentItem.getReleasedate().substring(0,4) + ") " +"Vote Rate: "+currentItem.getVoteAverage());

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieItem movieItem = new MovieItem(currentItem.getOriginalTitle() ,currentItem.getPosterPath() ,currentItem.getId());

                ml.addMovie(movieItem);
                FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists").child(""+currentPosition).setValue(ml);


            }
        });







    }

    @Override
    public int getItemCount() {
        return movieResultsArray.size();
    }

    public static class MovieSearchShowViewHolder extends RecyclerView.ViewHolder{

        public TextView movieNameTv;
        public Button addBtn;


        public MovieSearchShowViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTv = itemView.findViewById(R.id.movieName);
            addBtn = itemView.findViewById(R.id.add);

        }
    }


}
