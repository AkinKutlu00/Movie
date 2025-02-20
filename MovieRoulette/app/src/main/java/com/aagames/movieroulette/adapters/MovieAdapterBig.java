package com.aagames.movieroulette.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MovieAdapterBig extends RecyclerView.Adapter<MovieAdapterBig.MovieViewHolder> {

    private ArrayList<MovieItem> mMovieList;

    Context context;
    private FirebaseAuth auth;


    public MovieAdapterBig(Context context, ArrayList<MovieItem> movieList){
        auth = FirebaseAuth.getInstance();
        String id = auth.getUid();
        this.context=context;
        mMovieList= movieList;


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
                    currentItem.setRevealed(false);
                    holder.movieNameCb.setChecked(false);
                   // myRef.child(position+"").child("revealed").setValue(false);

                    //holder.movieNameTv.setTextColor(Color.rgb(255, 0, 0));
                    //holder.movieImageView.setBackgroundColor(Color.rgb(255, 0, 0));;
                }else{
                    currentItem.setRevealed(true);
                    holder.movieNameCb.setChecked(true);
                    //myRef.child(position+"").child("revealed").setValue(true);

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
