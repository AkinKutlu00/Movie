package com.aagames.movieroulette.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.objects.UserItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    final String id = auth.getUid();
    final DatabaseReference listRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "friends" );


    private ArrayList<UserItem> friendList;

    Context context;




    public FriendAdapter(Context context, ArrayList<UserItem> friendList){
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

        final UserItem currentItem = friendList.get(position);

        holder.emailTv.setText(currentItem.getUsername());
        holder.usernameTv.setText(currentItem.getMail());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendList.remove(position);
                listRef.setValue(friendList);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.getId();

            }
        });




    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder{

        public TextView usernameTv;
        public TextView emailTv;
        public ImageButton delete;
        public ImageButton share;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTv = itemView.findViewById(R.id.username);
            emailTv = itemView.findViewById(R.id.email);
            delete = itemView.findViewById(R.id.delete);
            share = itemView.findViewById(R.id.share);

        }
    }


}
