package com.aagames.movieroulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.aagames.movieroulette.activities.NewCategory;
import com.aagames.movieroulette.objects.MovieList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCatDialog extends AppCompatDialogFragment {

    private EditText listNameEt;
    String listName;
    int catNumber;
    String id;
    FirebaseAuth auth;


    public AddCatDialog(int catNumber) {
        super();
        this.catNumber = catNumber;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.create_cat_dialog,null);
        listNameEt = view.findViewById(R.id.editCatName);


        auth = FirebaseAuth.getInstance();
        final String id = auth.getUid();
        final DatabaseReference listRef = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "movielists" );



        builder.setView(view)
                .setTitle("New Movie List")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        listName=listNameEt.getText().toString();
                        listRef.child( ""+catNumber ).setValue(new MovieList(listName));


                        final Intent intent = new Intent( view.getContext(), NewCategory.class);
                        intent.putExtra( "listname", listName);
                        intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                        startActivity(intent);
                        //System.out.println(catNumber+" alley");

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
