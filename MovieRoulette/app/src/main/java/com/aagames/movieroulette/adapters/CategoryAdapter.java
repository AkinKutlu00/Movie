package com.aagames.movieroulette.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.activities.Categories;
import com.aagames.movieroulette.activities.MainActivity;
import com.aagames.movieroulette.activities.NewCategory;
import com.aagames.movieroulette.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    ArrayList<String> categories;
    int height;
   int  width;

    public CategoryAdapter(Context context, ArrayList<String> categories, int height, int width){
        this.context=context;
        this.categories= categories;
        this.height = height;
        this.width = width;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_view, parent, false);
        CategoryViewHolder mvh = new CategoryViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, final int position) {

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width/4, height/6);
        layoutParams.setMargins(20,0,0,50);

        holder.cardView.setLayoutParams(layoutParams);

        holder.categoryNameBtn.setText(categories.get(position));

        holder.categoryNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, MainActivity.class );

                intent.putExtra( "categoryname", categories.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( intent );
                ((Activity)v.getContext()).finish();





            }
        });
        holder.categoryNameBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent( context, NewCategory.class );

                intent.putExtra( "listname", categories.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( intent );

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        public Button categoryNameBtn;
        public TextView categoryNameTv;
        public ImageView categoryImageView;
        public CardView cardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryNameTv = itemView.findViewById(R.id.nameMovie);
            categoryImageView = itemView.findViewById(R.id.movieImage);
            categoryNameBtn = itemView.findViewById(R.id.open);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }




}
