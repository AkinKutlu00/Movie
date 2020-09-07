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

import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private ArrayList<String> mNotificationList;

    Context context;
    DatabaseReference myRef;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    final String id = auth.getUid();

 final DatabaseReference listRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "notifications" );

    public NotificationAdapter(Context context, ArrayList<String> mNotificationList){

        this.context=context;
        this.mNotificationList= mNotificationList;


    }


    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_cardview, parent, false);
        NotificationViewHolder mvh = new NotificationViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationViewHolder holder, final int position) {

        holder.notiftxt.setText(mNotificationList.get(position));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationList.remove(position);
                listRef.setValue(mNotificationList);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{

        public TextView notiftxt;
        public Button delete;


        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notiftxt = itemView.findViewById(R.id.notitxt);
            delete = itemView.findViewById(R.id.deletenotif);


        }
    }


}
