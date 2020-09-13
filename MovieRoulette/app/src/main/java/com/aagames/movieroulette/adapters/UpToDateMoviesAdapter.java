package com.aagames.movieroulette.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.activities.MoviePopUp;
import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.objects.MovieList;
import com.aagames.movieroulette.tmdb.data.search.MovieResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpToDateMoviesAdapter extends RecyclerView.Adapter<UpToDateMoviesAdapter.UpToDateMoviesViewHolder> {

    private ArrayList<MovieResult> mMovieList;

    Context context;

    String id;

    int height;
    int  width;



    public UpToDateMoviesAdapter(Context context, ArrayList<MovieResult> movieList, int height, int width){
        this.context=context;
        mMovieList= movieList;
        this.height = height;
        this.width = width;


    }


    @NonNull
    @Override
    public UpToDateMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.uptodate_card_view, parent, false);
        UpToDateMoviesViewHolder mvh = new UpToDateMoviesViewHolder(v);
        return mvh;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final UpToDateMoviesViewHolder holder, final int position) {

        final MovieResult currentItem = mMovieList.get(position);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (width/(2.3)),(int) ((height)/4));
        layoutParams.setMargins(10,0,0,20);

        holder.cardView.setLayoutParams(layoutParams);


        holder.movieNameTv.setText(currentItem.getOriginalTitle());
        holder.movieNameTv.setTextSize(15);

        Glide.with(context).load("https://image.tmdb.org/t/p/original"+currentItem.getPosterPath()).apply(new RequestOptions().override(200, 300)).into( holder.movieImageView);


        holder.movieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoviePopUp.class);
                intent.putExtra( "MovieName", currentItem.getOriginalTitle());
                intent.putExtra( "MovieID", currentItem.getId());
                intent.putExtra( "MovieImageCode", currentItem.getPosterPath());
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public static class UpToDateMoviesViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView movieNameTv;
        public ImageView movieImageView;

        public UpToDateMoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTv = itemView.findViewById(R.id.nameMovie);
            movieImageView = itemView.findViewById(R.id.movieImage);
            cardView =  itemView.findViewById(R.id.cardViewPlus);

        }
    }


}