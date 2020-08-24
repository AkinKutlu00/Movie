package com.aagames.movieroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText editMail;
    EditText editPassword;
    Button signIn;
    Button register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editMail = (EditText)  findViewById(R.id.editMail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        signIn = (Button) findViewById(R.id.signbtn);
        register = (Button) findViewById(R.id.registerbtn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), MainActivity.class) );
            }
        });

    }
}
