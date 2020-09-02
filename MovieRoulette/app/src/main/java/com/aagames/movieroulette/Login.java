package com.aagames.movieroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText editMail;
    EditText editPassword;
    Button signIn;
    Button register;
    FirebaseAuth auth;
    Button forget;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editMail = (EditText)  findViewById(R.id.editMail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        signIn = (Button) findViewById(R.id.signbtn);
        register = (Button) findViewById(R.id.registerbtn);
        auth = FirebaseAuth.getInstance();
        forget = (Button) findViewById(R.id.forgetbtn);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        if ( auth.getCurrentUser() != null )
        {
            startActivity( new Intent(getApplicationContext(), MainActivity.class) );
            finish();
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // making progress bar visible

                editMail.setVisibility( View.INVISIBLE );
                editPassword.setVisibility( View.INVISIBLE );

                // getting mail and password from editTexts
                String mail = editMail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                // if users does not enter any thing in password or mail edittext
                if ( TextUtils.isEmpty(mail)|| TextUtils.isEmpty(password) )
                {
                    Toast.makeText(Login.this, "You should fill both",Toast.LENGTH_SHORT ).show();
                    editMail.setVisibility( View.VISIBLE );
                    editPassword.setVisibility( View.VISIBLE );
                }

                // if users fill password and mail edittexts
                else {
                    auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            // if log in succesful
                            if ( task.isSuccessful() ) {
                                Toast.makeText( Login.this, "Log in Succesful.", Toast.LENGTH_SHORT ).show();
                                startActivity( new Intent( getApplicationContext(), MainActivity.class ) );
                                finish();
                            }

                            // if log in is not succesful
                            else {

                                editMail.setVisibility( View.VISIBLE );
                                editPassword.setVisibility( View.VISIBLE );
                                Toast.makeText( Login.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                            }
                        }
                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), Register.class) );
            }
        });

    }
}
