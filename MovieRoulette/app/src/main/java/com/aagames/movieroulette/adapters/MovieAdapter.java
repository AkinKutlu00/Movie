package com.aagames.movieroulette.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.R;
import com.aagames.movieroulette.objects.MovieList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieItem> mMovieList;

    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private FirebaseAuth auth;
    String id;
    MovieList fav;
    int height;
    int  width;



    public MovieAdapter(Context context, ArrayList<MovieItem> movieList, String child, int height, int width){
        auth = FirebaseAuth.getInstance();
         id = auth.getUid();
        this.context=context;
        mMovieList= movieList;
        myRef = database.getReference("users").child(id).child("movielists").child(child).child("movies");
        this.height = height;
        this.width = width;


    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view, parent, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {

        final MovieItem currentItem = mMovieList.get(position);

        if(currentItem.getRevealed()){
            holder.movieNameTv.setText("");

            if(currentItem.getImageCode()!=null&&currentItem.getImageCode()!=""&&currentItem.getImageCode()!="null"){
               System.out.println("iamgecode: " +currentItem.getImageCode());
                Glide.with(context).load("https://image.tmdb.org/t/p/original"+currentItem.getImageCode()).apply(new RequestOptions().override(200, 200)).into( holder.movieImageView);

            }else{
                System.out.println("HOOOOP");
            }

            holder.movieImageView.setBackgroundColor(Color.rgb(0, 0, 0));;

            //holder.movieImageView.setImageResource(R.drawable.back);
           // holder.movieImageView.setBackgroundColor(Color.rgb(255, 0, 0));;
        }else{

           holder.movieNameTv.setText(""+(position+1));
            holder.movieImageView.setImageResource(R.drawable.back2);

            holder.movieImageView.setBackgroundColor(Color.rgb(0, 0, 0));;
        }




        fav = new MovieList();

        database.getReference("users").child(id).child("movielists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for( DataSnapshot dataSnapshot1: snapshot.getChildren() )
                {
                    MovieList n = ( MovieList ) dataSnapshot1.getValue( MovieList.class );
                    if(n.getName().equals("favorites")){
                        fav = n;
                    }



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.cardView.setLayoutParams(new RelativeLayout.LayoutParams(width/12, height/15));


       // Glide.with(context).load("https://image.tmdb.org/t/p/original"+currentItem.getImageCode()).preload();

        holder.movieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog builder=new Dialog(v.getContext());
                View view=LayoutInflater.from(context).inflate(R.layout.moviepopup,null);


                builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                TextView tvname=(TextView)view.findViewById(R.id.name);
                tvname.setText(mMovieList.get(position).getName());

               ImageView imageView =(ImageView) view.findViewById(R.id.imageViewPop);
               // tvfoto.setText(mMovieList.get(position).getImageCode());

                Glide.with(context).load("https://image.tmdb.org/t/p/original"+currentItem.getImageCode()).into( imageView);

               // Glide.with(view).load("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY209_CR0,0,140,209_AL_.jpg").into(imageView);


                Button close = (Button) view.findViewById(R.id.close);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();


                    }
                });


                Button yes = (Button) view.findViewById(R.id.yes);
                Button no = (Button) view.findViewById(R.id.no);
               // ImageButton star = (ImageButton) view.findViewById(R.id.fav);

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
                        //mMovieList.get(position).setRevealed(false);
                        myRef.child(position+"").child("revealed").setValue(false);
                        builder.dismiss();
                    }
                });


                /*star.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fav.addMovie(currentItem);
                        database.getReference("users").child(id).child("movielists").child("0").setValue(fav);
                    }
                });*/

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
            cardView =  itemView.findViewById(R.id.movieNormalcardView);

        }
    }


}
