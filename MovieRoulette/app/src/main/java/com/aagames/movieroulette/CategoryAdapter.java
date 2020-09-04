package com.aagames.movieroulette;

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

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MovieViewHolder> {

    Context context;
    ArrayList<String> categories;


    public CategoryAdapter(Context context, ArrayList<String> categories){
        this.context=context;
        this.categories= categories;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_view, parent, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {

        holder.categoryNameBtn.setText(categories.get(position));

        holder.categoryNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, MainActivity.class );

                intent.putExtra( "categoryname", categories.get(position));
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity( intent );


            }
        });
        holder.categoryNameBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent( context, NewCategory.class );

                intent.putExtra( "listname", categories.get(position));
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity( intent );

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        public Button categoryNameBtn;
        public TextView categoryNameTv;
        public ImageView categoryImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryNameTv = itemView.findViewById(R.id.nameMovie);
            categoryImageView = itemView.findViewById(R.id.movieImage);
            categoryNameBtn = itemView.findViewById(R.id.open);

        }
    }


}
