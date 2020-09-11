package com.aagames.movieroulette.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.R;
import com.bumptech.glide.Glide;
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
    int height;
    int  width;


    public MovieAdapterPlus(Context context, ArrayList<MovieItem> movieList, String child, int height, int width){
        auth = FirebaseAuth.getInstance();
        String id = auth.getUid();
        this.context=context;
        mMovieList= movieList;
        myRef = database.getReference("users").child(id).child("movielists").child(child).child("movies");
        this.height = height;
        this.width = width;


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

        holder.cardView.setLayoutParams(new RelativeLayout.LayoutParams((int) (width/(3.5)),(int) ((height)/11.5)));


         //holder.movieNameTv.setTextSize((height-100)*0.024f);


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
                builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                View view=LayoutInflater.from(context).inflate(R.layout.moviepopup,null);

                TextView tvname=(TextView)view.findViewById(R.id.name);
                tvname.setText(mMovieList.get(position).getName());

                //TextView tvfoto=(TextView)view.findViewById(R.id.foto);
               // tvfoto.setText(mMovieList.get(position).getImageCode());

                Button close = (Button) view.findViewById(R.id.close);
                ImageView imageView =(ImageView) view.findViewById(R.id.imageViewPop);

                Glide.with(context).load("https://image.tmdb.org/t/p/original"+mMovieList.get(position).getImageCode()).into( imageView);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();


                    }
                });


                Button yes = (Button) view.findViewById(R.id.yes);
                Button no = (Button) view.findViewById(R.id.no);
                //ImageButton star = (ImageButton) view.findViewById(R.id.fav);

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
        public CardView cardView;
        public TextView movieNameTv;
        public ImageView movieImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTv = itemView.findViewById(R.id.nameMovie);
            movieImageView = itemView.findViewById(R.id.movieImage);
            cardView =  itemView.findViewById(R.id.cardViewPlus);

        }
    }


}
