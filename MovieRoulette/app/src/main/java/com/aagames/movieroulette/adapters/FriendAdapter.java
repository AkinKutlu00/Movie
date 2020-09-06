package com.aagames.movieroulette.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.objects.FriendItem;
import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {

    private ArrayList<FriendItem> friendList;

    Context context;




    public FriendAdapter(Context context, ArrayList<FriendItem> friendList){
        this.friendList=friendList;
        this.context=context;


    }


    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_item_card_view, parent, false);
        FriendViewHolder mvh = new FriendViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final FriendViewHolder holder, final int position) {

        FriendItem currentItem = friendList.get(position);

        holder.emailTv.setText(currentItem.getUsername());
        holder.usernameTv.setText(currentItem.getEmail());





    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder{

        public TextView usernameTv;
        public TextView emailTv;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTv = itemView.findViewById(R.id.username);
            emailTv = itemView.findViewById(R.id.email);

        }
    }


}
