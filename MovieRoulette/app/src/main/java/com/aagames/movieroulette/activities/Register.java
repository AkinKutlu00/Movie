package com.aagames.movieroulette.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aagames.movieroulette.R;
import com.aagames.movieroulette.objects.MovieItem;
import com.aagames.movieroulette.objects.MovieList;
import com.aagames.movieroulette.objects.UserItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    EditText username;
    EditText emailEt;
    EditText password;
    EditText confirm;
    ArrayList<String> userlist;

    Button register;

    FirebaseAuth auth;
    DatabaseReference databaseReferenceProfile;
    DatabaseReference databaseReferenceUsers;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userlist = new ArrayList<>();


        username = findViewById(R.id.userEt);
        emailEt = findViewById(R.id.mailEt);
        password = findViewById(R.id.passwordEt);
        confirm = findViewById(R.id.confirmEt);

        register = findViewById(R.id.register);

        final ArrayList<MovieList> movieLists = new ArrayList<>();

        final ArrayList<MovieItem> movieList1 = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();



        DatabaseReference myRef = database.getReference("ListOfMovies");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //movieLists.clear();

                for( DataSnapshot dataSnapshot1: snapshot.getChildren() )
                {
                    MovieList n = ( MovieList ) dataSnapshot1.getValue( MovieList.class );
                     //System.out.println("bum "+ n.getName());

                    movieLists.add( n );

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final DatabaseReference listRef = FirebaseDatabase.getInstance().getReference().child( "infoUsers" );


        listRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userlist.clear();
                // lists = new ArrayList<MovieList>();
                for( DataSnapshot shot: snapshot.getChildren() )
                {
                    UserItem friend1 =  ( UserItem ) shot.getValue( UserItem.class );

                    userlist.add( friend1.getUsername());

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getting texts from
                final String nameString = username.getText().toString();
                final String email = emailEt.getText().toString();
                String passwordString = password.getText().toString();
                String confirmPassword = confirm.getText().toString();


                // displaying progressbar

                register.setVisibility( View.INVISIBLE );
                auth = FirebaseAuth.getInstance();



                // if one of the editTexts is empty
                if ( TextUtils.isEmpty(email) || TextUtils.isEmpty(passwordString) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(nameString) )
                {
                    Toast.makeText(Register.this, "You should fill both",Toast.LENGTH_SHORT ).show();

                    register.setVisibility( View.VISIBLE );
                }

                else {
                    // if passwords user enter are not same
                    if ( !confirm.getText().toString().trim().equals( password.getText().toString().trim() ) ) {
                        Toast.makeText(Register.this, "Passwords does not match", Toast.LENGTH_SHORT ).show();

                        register.setVisibility( View.VISIBLE );
                    }

                    else if(check(userlist, nameString)){
                        Toast.makeText(Register.this, "username is already using", Toast.LENGTH_SHORT ).show();

                        register.setVisibility( View.VISIBLE );
                    }
                    else {
                        auth.createUserWithEmailAndPassword(email, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if ( task.isSuccessful() )
                                {
                                    id = auth.getUid();
                                    databaseReferenceProfile = FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child( "profile" );

                                    // making toast message of user created
                                    Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT ).show();

                                    // making changes on database
                                    databaseReferenceProfile.child( "name" ).setValue( nameString );
                                    databaseReferenceProfile.child( "email" ).setValue( email );
                                    databaseReferenceProfile.child( "id" ).setValue( id );

                                   // FirebaseDatabase.getInstance().getReference().child("movielists").child("imdb").setValue(movieList1);

                                    FirebaseDatabase.getInstance().getReference().child( "users" ).child(id).child("movielists").setValue(movieLists);

                                    databaseReferenceUsers = FirebaseDatabase.getInstance().getReference().child( "infoUsers" );
                                    //databaseReferenceUsers.setValue(""+nameString);


                                    databaseReferenceUsers.child( nameString ).setValue( new UserItem(nameString,email,id));

                                    // making disapppered progress bar
                                    register.setVisibility( View.VISIBLE );

                                    // finishing activity and starting main activity
                                    startActivity(new Intent( getApplicationContext(), Categories.class ));
                                    finish();
                                }
                                else
                                {

                                    register.setVisibility( View.VISIBLE );
                                    Toast.makeText(Register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                                }
                            }
                        });
                    }
                }
            }
        });



           }

    public boolean check(ArrayList<String> users, String newUser){

        if(users.size()==0){

            return false;
        }else{

            int pos = 0;
            while ( pos < users.size()-1 && !((users.get(pos)).equals(newUser) )){
                pos++;
            }

            return ( (users.get(pos)).equals(newUser));

        }


    }
}
