package com.aagames.movieroulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MovieAdapterBig extends RecyclerView.Adapter<MovieAdapterBig.MovieViewHolder> {

    private ArrayList<MovieItem> mMovieList;

    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;


    public MovieAdapterBig(Context context, ArrayList<MovieItem> movieList, String child){

        this.context=context;
        mMovieList= movieList;
        myRef = database.getReference("movielists").child(child);

    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view_1, parent, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {

        MovieItem currentItem = mMovieList.get(position);

        if(currentItem.getRevealed()){
            holder.movieNameCb.setChecked(true);
            //holder.movieNameTv.setTextColor(Color.rgb(255, 0, 0));
            //holder.movieImageView.setBackgroundColor(Color.rgb(255, 0, 0));;
        }else{
            holder.movieNameCb.setChecked(false);
            //holder.movieImageView.setBackgroundColor(Color.rgb(0, 0, 255));;
        }
        holder.movieNameCb.setText(""+mMovieList.get(position).getName());


        holder.movieNameCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieItem currentItem = mMovieList.get(position);
                if(currentItem.getRevealed()){
                    myRef.child(position+"").child("revealed").setValue(false);

                    //holder.movieNameTv.setTextColor(Color.rgb(255, 0, 0));
                    //holder.movieImageView.setBackgroundColor(Color.rgb(255, 0, 0));;
                }else{
                    myRef.child(position+"").child("revealed").setValue(true);

                    //holder.movieImageView.setBackgroundColor(Color.rgb(0, 0, 255));;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        public CheckBox movieNameCb;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameCb = itemView.findViewById(R.id.checkBox);


        }
    }


}
