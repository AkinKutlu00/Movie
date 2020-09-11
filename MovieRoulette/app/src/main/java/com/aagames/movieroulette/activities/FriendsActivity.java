package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aagames.movieroulette.dialogs.AddFriendDialog;
import com.aagames.movieroulette.R;
import com.aagames.movieroulette.adapters.FriendAdapter;
import com.aagames.movieroulette.objects.UserItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    String id;
    FirebaseAuth auth;

    Button addFriend;

    ArrayList<UserItem> friendsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        auth = FirebaseAuth.getInstance();
        final String id = auth.getUid();
        final DatabaseReference listRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "friends" );


        friendsList = new ArrayList<>();
        addFriend = findViewById(R.id.addFriend);

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        listRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                friendsList.clear();
                // lists = new ArrayList<MovieList>();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    UserItem friend1 =  ( UserItem ) shot.getValue( UserItem.class );

                    //lists.add( list );
                    friendsList.add( friend1);



                }


                recyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new FriendAdapter(getApplicationContext(),friendsList);
                recyclerView.setAdapter(mAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView = findViewById(R.id.friendsRV);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FriendAdapter(getApplicationContext(),friendsList);
        recyclerView.setAdapter(mAdapter);
    }

    public void openDialog() {
        AddFriendDialog catDialog = new AddFriendDialog();
        catDialog.show(getSupportFragmentManager(),"example");
    }
}