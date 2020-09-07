package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.FriendAdapter;
import com.aagames.movieroulette.adapters.NotificationAdapter;
import com.aagames.movieroulette.objects.MovieList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    final String id = auth.getUid();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("notifications");
        final ArrayList<String> notifList = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notifList.clear();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    String msg =  ( String ) shot.getValue( String.class );

                    //lists.add( list );
                    notifList.add( msg );
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



       // notifList.add("Quasap Seni Takip etti");
        //notifList.add("Quasap Seni Takipten çıkardı");
        //notifList.add("Sormelf Seni Arkadaş olarak ekledi");


        recyclerView = findViewById(R.id.notifrv);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NotificationAdapter(getApplicationContext(),notifList);
        recyclerView.setAdapter(mAdapter);
    }
}