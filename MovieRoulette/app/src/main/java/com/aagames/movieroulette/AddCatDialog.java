package com.aagames.movieroulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.aagames.movieroulette.activities.NewCategory;
import com.aagames.movieroulette.objects.MovieList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddCatDialog extends AppCompatDialogFragment {

    private EditText listNameEt;
    String listName;
    int catNumber;
    String id;
    FirebaseAuth auth;
    ArrayList<String> movieListNames;


    public AddCatDialog(int catNumber, ArrayList<String> movieListNames) {
        super();
        this.catNumber = catNumber;
        this.movieListNames = movieListNames;
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
                        int counter;
                        counter = 0;

                        if(movieListNames.size()>0){

                            while (counter<movieListNames.size()-1 && !(movieListNames.get(counter).equals(listName) )){
                                counter++;
                            }

                            if(movieListNames.get(counter).equals(listName)){
                                Toast.makeText(view.getContext(),"Error: "+ listName +" already exist ",Toast.LENGTH_LONG ).show();


                            }else{
                                listRef.child( ""+catNumber ).setValue(new MovieList(listName));

                                final Intent intent = new Intent( view.getContext(), NewCategory.class);
                                intent.putExtra( "listname", listName);
                                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                                startActivity(intent);
                                //getActivity().finish();

                            }

                        }else{
                            listRef.child( ""+catNumber ).setValue(new MovieList(listName));

                            final Intent intent = new Intent( view.getContext(), NewCategory.class);
                            intent.putExtra( "listname", listName);
                            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                            startActivity(intent);
                            //getActivity().finish();

                        }




                        // this.finish();
                        //System.out.println(catNumber+" alley");

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return  builder.create();

    }
}
