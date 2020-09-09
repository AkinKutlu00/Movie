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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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

        MovieList movieList1 = new MovieList("imdb");

        movieList1.addMovie(new MovieItem("The Shawshank Redemption","/5KCVkau1HEl7ZzfPsKAPM0sMiKc.jpg",278));
        movieList1.addMovie(new MovieItem("The Godfather","/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",238));
        movieList1.addMovie(new MovieItem("The Godfather: Part II","/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg",240));
        movieList1.addMovie(new MovieItem("The Dark Knight","/qJ2tW6WMUDux911r6m7haRef0WH.jpg",155));
        movieList1.addMovie(new MovieItem("12 Angry Men","/ppd84D2i9W8jXmsyInGyihiSyqz.jpg",389));
        movieList1.addMovie(new MovieItem("Schindler's List","/t7xhP8SQTVanzecvn1oQRVWCXTI.jpg",424));
        movieList1.addMovie(new MovieItem("The Lord of the Rings: The Return of the King","/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg",122));
        movieList1.addMovie(new MovieItem("Pulp Fiction","/e5vzxBLagSoFh3LCA2zSj55AVc7.jpg",680));
        movieList1.addMovie(new MovieItem("Il buono, il brutto, il cattivo","/eWivEg4ugIMAd7d4uWI37b17Cgj.jpg",429));
        movieList1.addMovie(new MovieItem("The Lord of the Rings: The Fellowship of the Ring","/6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg",120));
        movieList1.addMovie(new MovieItem("Fight Club","/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg",550));
        movieList1.addMovie(new MovieItem("Forrest Gump","/clolk7rB5lAjs41SD0Vt6IXYLMm.jpg",13));
        movieList1.addMovie(new MovieItem("Inception","/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",27205));
        movieList1.addMovie(new MovieItem("The Empire Strikes Back","/7BuH8itoSrLExs2YZSsM01Qk2no.jpg",1891));
        movieList1.addMovie(new MovieItem("The Lord of the Rings: The Two Towers","/5VTN0pR8gcqV3EPUHHfMGnJYN9L.jpg",121));
        movieList1.addMovie(new MovieItem("The Matrix","/dXNAPwY7VrqMAo51EKhhCJfaGb5.jpg",603));
        movieList1.addMovie(new MovieItem("GoodFellas","/oErEczcVUmJm0EPdvWsvK4g4Lv3.jpg",769));
        movieList1.addMovie(new MovieItem("One Flew Over the Cuckoo's Nest","/biejlC9yx8W66KHrD5tp9YiSqmV.jpg",510));
        movieList1.addMovie(new MovieItem("Kurosawa Akira: Tsukuru to iu koto wa subarashii: Shichinin no samurai","/asZGvMVmut2gEbEGlQ1yfUlOd3C.jpg",523634));
        movieList1.addMovie(new MovieItem("Se7en","/6yoghtyTpznpBik8EngEmJskVUO.jpg",807));
        movieList1.addMovie(new MovieItem("La vita è bella","/74hLDKjD5aGYOotO6esUVaeISa2.jpg",637));
        movieList1.addMovie(new MovieItem("Cidade de Deus","/k7eYdWvhYQyRQoU2TB2A2Xu2TfD.jpg",598));
        movieList1.addMovie(new MovieItem("The Silence of the Lambs","/rplLJ2hPcOQmkFhTqUte0MkEaO2.jpg",274));
        movieList1.addMovie(new MovieItem("It's a Wonderful Life","/bSqt9rhDZx1Q7UZ86dBPKdNomp2.jpg",1585));
        movieList1.addMovie(new MovieItem("Star Wars","/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg",11));
        movieList1.addMovie(new MovieItem("Saving Private Ryan","/1wY4psJ5NVEhCuOYROwLH2XExM2.jpg",857));
        movieList1.addMovie(new MovieItem("Spirited Away","/2TeJfUZMGolfDdW6DKhfIWqvq8y.jpg",129));
        movieList1.addMovie(new MovieItem("The Green Mile","/velWPhVMQeQKcxggNEU8YmIo52R.jpg",497));
        movieList1.addMovie(new MovieItem("Parasite","/6EM7Jm3veKSW9ELMajSSxslYWDa.jpg",48311));
        movieList1.addMovie(new MovieItem("Interstellar","/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",157336));
        movieList1.addMovie(new MovieItem("Léon: The Professional","/hxZKx5v4A2QiQ8sfLaph73baYpJ.jpg",101));
        movieList1.addMovie(new MovieItem("Hamilton","/h1B7tW0t399VDjAcWJh8m87469b.jpg",556574));
        movieList1.addMovie(new MovieItem("The Usual Suspects","/bUPmtQzrRhzqYySeiMpv7GurAfm.jpg",629));
        movieList1.addMovie(new MovieItem("Seppuku","/bLXl3iBAUoDAVK7z3m8kXd7AAye.jpg",76659));
        movieList1.addMovie(new MovieItem("The Lion King","/dzBtMocZuJbjLOXvrl4zGYigDzh.jpg",420818));
        movieList1.addMovie(new MovieItem("Back to the Future","/7lyBcpYB0Qt8gYhXYaEZUNlNQAv.jpg",105));
        movieList1.addMovie(new MovieItem("The Pianist","/3DzePKMbLMIM636S6syCy3cLPqj.jpg",423));
        movieList1.addMovie(new MovieItem("Terminator 2: Judgment Day","/wcJFUcbPfXJRXRNXNFfF0W6dpx.jpg",280));
        movieList1.addMovie(new MovieItem("American History X","/c2gsmSQ2Cqv8zosqKOCwRS0GFBS.jpg",73));
        movieList1.addMovie(new MovieItem("Modern Times","/7uoiKOEjxBBW0AgDGQWrlfGQ90w.jpg",3082));
        movieList1.addMovie(new MovieItem("Psycho","/nR4LD4ZJg2n6LZQpcOrLFdMq0cD.jpg",539));
        movieList1.addMovie(new MovieItem("Gladiator","/pRn3TJHbAqCAO6U8Dw5DayVUuX3.jpg",98));
        movieList1.addMovie(new MovieItem("City Lights","/bXNvzjULc9jrOVhGfjcc64uKZmZ.jpg",901));
        movieList1.addMovie(new MovieItem("The Departed","/jyAgiqVSx5fl0NNj7WoGGKweXrL.jpg",1422));
        movieList1.addMovie(new MovieItem("Intouchables","/w7WxNbb0mcWpwDMd3pJA7LQRfnt.jpg",77338));
        movieList1.addMovie(new MovieItem("Whiplash","/qq8xf2SQqHifUUyc0k6Hj1065f1.jpg",244786));
        movieList1.addMovie(new MovieItem("The Prestige","/pvSESD7ujxWZwtYDb9l57qE0ywq.jpg",1124));
        movieList1.addMovie(new MovieItem("Grave of the Fireflies","/dsqlm8YuodWsTZGrE2Nev2c99Gm.jpg",76826));
        movieList1.addMovie(new MovieItem("C'era una volta il West","/3RymloPYcEPx30T1vTrz2cXaVnh.jpg",335));
        movieList1.addMovie(new MovieItem("Casablanca","/pQjUifS7GXimKOtRwPf8nXWw1bd.jpg",289));
        movieList1.addMovie(new MovieItem("Nuovo Cinema Paradiso","/8SRUfRUi6x4O68n0VCbDNRa6iGL.jpg",11216));
        movieList1.addMovie(new MovieItem("Rear Window","/qitnZcLP7C9DLRuPpmvZ7GiEjJN.jpg",567));
        movieList1.addMovie(new MovieItem("Aliens","/xwdPTZyyBa4U3V2N0EmozTCeEAY.jpg",679));
        movieList1.addMovie(new MovieItem("Apocalypse Now","/gQB8Y5RCMkv2zwzFHbUJX3kAhvA.jpg",28));
        movieList1.addMovie(new MovieItem("Memento","/uprP8rtOgMYJQ2h3ldDFvGsaBgH.jpg",77));
        movieList1.addMovie(new MovieItem("Raiders of the Lost Ark","/awUGN7ZCNq2EUTdpVaHDX23anOZ.jpg",85));
        movieList1.addMovie(new MovieItem("The Great Dictator","/1QpO9wo7JWecZ4NiBuu625FiY1j.jpg",914));
        movieList1.addMovie(new MovieItem("Joker","/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",475557));
        movieList1.addMovie(new MovieItem("Django Unchained","/7oWY8VDWW7thTzWh3OKYRkWUlD5.jpg",68718));
        movieList1.addMovie(new MovieItem("Das Leben der Anderen","/5BCyeLJHPcRwhu0YaRqUzw00JJ4.jpg",582));
        movieList1.addMovie(new MovieItem("Paths of Glory","/2qlFZKJTe6SR4W5hubyBjY7uxS5.jpg",975));
        movieList1.addMovie(new MovieItem("The Shining","/b6ko0IKC8MdYBBPkkA1aBPLe2yz.jpg",694));
        movieList1.addMovie(new MovieItem("WALL·E","/Agc6lw8pb6BIGVeguvdjDT0p9Mb.jpg",10681));
        movieList1.addMovie(new MovieItem("Avengers: Infinity War","/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",299536));
        movieList1.addMovie(new MovieItem("Sunset Boulevard","/zt8aQ6ksqK6p1AopC5zVTDS9pKT.jpg",599));
        movieList1.addMovie(new MovieItem("Witness for the Prosecution","/99QUmKYziu6WGcHUmi06lY6w5Z3.jpg",37257));
        movieList1.addMovie(new MovieItem("Spider-Man: Into the Spider-Verse","/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",324857));
        movieList1.addMovie(new MovieItem("Princess Mononoke","/pdtzEreKvKAlqa2YEBaGwiA45V8.jpg",128));
        movieList1.addMovie(new MovieItem("Oldboy","/pWDtjs568ZfOTMbURQBYuT4Qxka.jpg",670));
        movieList1.addMovie(new MovieItem("Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb","/gHm96BRW4GoI339rF1vYoYTB6Qe.jpg",935));
        movieList1.addMovie(new MovieItem("The Dark Knight Rises","/vzvKcPQ4o7TjWeGIn0aGC9FeVNu.jpg",49026));
        movieList1.addMovie(new MovieItem("Once Upon a Time in America","/hBDHr3YihnuL46aeyEMhTchXkZP.jpg",311));
        movieList1.addMovie(new MovieItem("Aliens","/xwdPTZyyBa4U3V2N0EmozTCeEAY.jpg",679));
        movieList1.addMovie(new MovieItem("Avengers: Endgame","/or06FN3Dka5tukK1e9sl16pB3iy.jpg",299534));
        movieList1.addMovie(new MovieItem("Your Name","/q719jXXEzOoYaps6babgKnONONX.jpg",372058));
        movieList1.addMovie(new MovieItem("Coco","/eKi8dIrr8voobbaGzDpe8w0PVbC.jpg",354912));
        movieList1.addMovie(new MovieItem("American Beauty","/wby9315QzVKdW9BonAefg8jGTTb.jpg",14));
        movieList1.addMovie(new MovieItem("Braveheart","/or1gBugydmjToAEq7OZY0owwFk.jpg",197));
        movieList1.addMovie(new MovieItem("3 Idiots","/k1iWMypGjk1b59oCHLtyfd4hw99.jpg",20453));
        movieList1.addMovie(new MovieItem("Das Boot","/mgX9sExDcdwRx8YxPjL8ngfSTmn.jpg",387));
        movieList1.addMovie(new MovieItem("Toy Story","/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",301528));
        movieList1.addMovie(new MovieItem("Tengoku to jigoku","/tgNjemQPG96uIezpiUiXFcer5ga.jpg",12493));
        movieList1.addMovie(new MovieItem("Amadeus","/tsqcwBp1jYZdcceXXoVEby0dZkt.jpg",279));
        movieList1.addMovie(new MovieItem("Taare Zameen Par","/wEHkOBcvEiNZpFU1O3HbZLoKSaE.jpg",7508));
        movieList1.addMovie(new MovieItem("Return of the Jedi","/mDCBQNhR6R0PVFucJl0O4Hp5klZ.jpg",1892));
        movieList1.addMovie(new MovieItem("Inglourious Basterds","/7sfbEnaARXDDhKm0CZ7D7uc2sbo.jpg",16869));
        movieList1.addMovie(new MovieItem("Reservoir Dogs","/AjTtJNumZyUDz33VtMlF1K8JPsE.jpg",500));
        movieList1.addMovie(new MovieItem("Good Will Hunting","/bABCBKYBK7A5G1x0FzoeoNfuj2.jpg",489));
        movieList1.addMovie(new MovieItem("Capharnaüm","/mFnfTVADj8yOxwzprYOmTPselk8.jpg",517814));
        movieList1.addMovie(new MovieItem("2001: A Space Odyssey","/zmmYdPa8Lxx999Af9vnVP4XQ1V6.jpg",62));
        movieList1.addMovie(new MovieItem("Requiem for a Dream","/nOd6vjEmzCT0k4VYqsA2hwyi87C.jpg",641));
        movieList1.addMovie(new MovieItem("Vertigo","/15uOEfqBNTVtDUT7hGBVCka0rZz.jpg",426));
        movieList1.addMovie(new MovieItem("M - Eine Stadt sucht einen Mörder","/6hg2UClwHGnBojemFrLgiF1WK8A.jpg",832));
        movieList1.addMovie(new MovieItem("Dangal","/p2lVAcPuRPSO8Al6hDDGw0OgMi8.jpg",360814));
        movieList1.addMovie(new MovieItem("Eternal Sunshine of the Spotless Mind","/5MwkWH9tYHv3mV9OdYTMR5qreIz.jpg",38));
        movieList1.addMovie(new MovieItem("1917","/iZf0KyrE25z1sage4SYFLCCrMi9.jpg",530915));
        movieList1.addMovie(new MovieItem("Jagten","/wzrrF5ct6gYLyUEGCjwn8f1fOdv.jpg",103663));
        movieList1.addMovie(new MovieItem("Citizen Kane","/sav0jxhqiH0bPr2vZFU0Kjt2nZL.jpg",15));
        movieList1.addMovie(new MovieItem("Full Metal Jacket","/kMKyx1k8hWWscYFnPbnxxN4Eqo4.jpg",600));
        movieList1.addMovie(new MovieItem("Ladri di biciclette","/7ZiSTZN5FWsphVmlwFVFfn5EOl.jpg",5156));


        FirebaseDatabase.getInstance().getReference().child( "ListOfMovies" ).child("imdb").setValue(movieList1);

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
            startActivity( new Intent(getApplicationContext(), Categories.class) );
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
                                startActivity( new Intent( getApplicationContext(), Categories.class ) );
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
