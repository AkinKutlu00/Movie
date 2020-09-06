package com.aagames.movieroulette.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.activities.MainActivity;
import com.aagames.movieroulette.activities.NewCategory;
import com.aagames.movieroulette.R;
import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.objects.MovieList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendAdapter extends RecyclerView.Adapter<SendAdapter.SendViewHolder> {

    Context context;
    ArrayList<MovieList> categories;
    String recieverId;
    ArrayList<MovieList> recieverCat;



    public SendAdapter(Context context, ArrayList<MovieList> categories, String recieverId ){
        this.context=context;
        this.categories= categories;
        this.recieverId = recieverId;
        recieverCat = new ArrayList<>();

    }


    @NonNull
    @Override
    public SendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_view, parent, false);
        SendViewHolder mvh = new SendViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final SendViewHolder holder, final int position) {

        holder.categoryNameBtn.setText(categories.get(position).getName());

        FirebaseDatabase.getInstance().getReference().child( "users" ).child(recieverId).child("movielists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recieverCat.clear();
                // lists = new ArrayList<MovieList>();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    MovieList list =  ( MovieList ) shot.getValue( MovieList.class );

                    //lists.add( list );
                    recieverCat.add( list );

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.categoryNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recieverCat.add(categories.get(position));

                FirebaseDatabase.getInstance().getReference("users").child(recieverId).child("movielists").setValue(recieverCat);
            }
        });




    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class SendViewHolder extends RecyclerView.ViewHolder{

        public Button categoryNameBtn;
        public TextView categoryNameTv;
        public ImageView categoryImageView;

        public SendViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryNameTv = itemView.findViewById(R.id.nameMovie);
            categoryImageView = itemView.findViewById(R.id.movieImage);
            categoryNameBtn = itemView.findViewById(R.id.open);

        }
    }


}
