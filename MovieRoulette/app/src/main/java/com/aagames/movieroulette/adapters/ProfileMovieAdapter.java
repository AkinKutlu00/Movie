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

public class ProfileMovieAdapter extends RecyclerView.Adapter<ProfileMovieAdapter.ProfileMovieViewHolder> {

    private ArrayList<Integer> mMovieList;

    Context context;

    String id;

    int height;
    int  width;



    public ProfileMovieAdapter(Context context, ArrayList<Integer> movieList, int height, int width){
        this.context=context;
        mMovieList= movieList;
        this.height = height;
        this.width = width;


    }


    @NonNull
    @Override
    public ProfileMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.uptodate_card_view, parent, false);
        ProfileMovieViewHolder mvh = new ProfileMovieViewHolder(v);
        return mvh;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ProfileMovieViewHolder holder, final int position) {



        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (width/(3.3)),(int) ((height)/2.5));
        layoutParams.setMargins(10,0,0,20);

        holder.cardView.setLayoutParams(layoutParams);
        holder.cardView.setCardBackgroundColor(Color.GREEN);
        holder.cardView.setCardElevation(0);

        holder.movieNameTv.setText(""+mMovieList.get(position));
        holder.movieNameTv.setTextSize(15);




    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public static class ProfileMovieViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView movieNameTv;
        public ImageView movieImageView;

        public ProfileMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTv = itemView.findViewById(R.id.nameMovie);
            movieImageView = itemView.findViewById(R.id.movieImage);
            cardView =  itemView.findViewById(R.id.cardViewPlus);

        }
    }


}