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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieItem> mMovieList;

    Context context;


    public MovieAdapter(Context context, ArrayList<MovieItem> movieList){

        this.context=context;
        mMovieList= movieList;

    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view, parent, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {

        MovieItem currentItem = mMovieList.get(position);

        if(currentItem.getRevealed()){
            holder.movieImageView.setBackgroundColor(Color.rgb(255, 0, 0));;
        }else{

            holder.movieImageView.setBackgroundColor(Color.rgb(0, 0, 255));;
        }
         holder.movieNameTv.setText(""+position);
        holder.movieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,mMovieList.get(position).getName(),Toast.LENGTH_SHORT).show();



            }
        });

        holder.movieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog builder=new Dialog(v.getContext());
                View view=LayoutInflater.from(context).inflate(R.layout.moviepopup,null);

                TextView tvname=(TextView)view.findViewById(R.id.name);
                tvname.setText(mMovieList.get(position).getName());

                Button close = (Button) view.findViewById(R.id.close);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();


                    }
                });

                builder.setContentView(view);





                builder.show();
            }

        });

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        public TextView movieNameTv;
        public ImageView movieImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTv = itemView.findViewById(R.id.nameMovie);
            movieImageView = itemView.findViewById(R.id.movieImage);

        }
    }


}
