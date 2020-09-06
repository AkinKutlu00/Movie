package com.aagames.movieroulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.aagames.movieroulette.objects.UserItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddFriendDialog extends AppCompatDialogFragment {

    private EditText listNameEt;
    String listName;
    int catNumber;
    String id;
    FirebaseAuth auth;

    ArrayList<UserItem> friendsList;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.create_cat_dialog,null);
        listNameEt = view.findViewById(R.id.editCatName);

        auth = FirebaseAuth.getInstance();
        final String id = auth.getUid();
        final DatabaseReference listRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "friends" );

        listNameEt.setHint("username");

        friendsList = new ArrayList<>();

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

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        builder.setView(view)
                .setTitle("New Friend Request")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        listName=listNameEt.getText().toString();

                        final UserItem friendItem =new UserItem(listName, null,null);

                        DatabaseReference databaseReferenceUsers = FirebaseDatabase.getInstance().getReference().child( "infoUsers" ).child(listName);


                        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                UserItem n = ( UserItem ) snapshot.getValue( UserItem.class );
                                //System.out.println("bum "+ n.getName());

                                if(n == null){
                                    Toast.makeText(builder.getContext(),"User does not exist",Toast.LENGTH_LONG).show();

                                }else{
                                    friendItem.setMail(n.getMail());
                                    friendItem.setId(n.getId());
                                    friendsList.add(friendItem);
                                    listRef.setValue(friendsList);
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });





                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
        ;




        return  builder.create();

    }
}
