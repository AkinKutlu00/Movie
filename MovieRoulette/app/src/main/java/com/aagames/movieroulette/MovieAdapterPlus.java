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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MovieAdapterPlus extends RecyclerView.Adapter<MovieAdapterPlus.MovieViewHolder> {

    private ArrayList<MovieItem> mMovieList;

    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Imdb");
    private FirebaseAuth auth;


    public MovieAdapterPlus(Context context, ArrayList<MovieItem> movieList, String child){
        auth = FirebaseAuth.getInstance();
        String id = auth.getUid();
        this.context=context;
        mMovieList= movieList;
        myRef = database.getReference("users").child(id).child("movielists").child(child);


    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view_5x7, parent, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {

        MovieItem currentItem = mMovieList.get(position);

        if(currentItem.getRevealed()){
            holder.movieImageView.setImageResource(R.drawable.back2);
            //holder.movieNameTv.setTextColor(Color.rgb(255, 0, 0));
            //holder.movieImageView.setBackgroundColor(Color.rgb(255, 0, 0));;
        }else{
            holder.movieImageView.setImageResource(R.drawable.back);
            //holder.movieImageView.setBackgroundColor(Color.rgb(0, 0, 255));;
        }
        holder.movieNameTv.setText(""+mMovieList.get(position).getName());
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

                TextView tvfoto=(TextView)view.findViewById(R.id.foto);
                tvfoto.setText(mMovieList.get(position).getImageCode());

                Button close = (Button) view.findViewById(R.id.close);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();


                    }
                });


                Button yes = (Button) view.findViewById(R.id.yes);
                Button no = (Button) view.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //mMovieList.get(position).setRevealed(true);
                        myRef.child(position+"").child("revealed").setValue(true);

                        builder.dismiss();

                    }
                });


                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myRef.child(position+"").child("revealed").setValue(false);
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
