package com.aagames.movieroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<MovieItem> movieList1 = new ArrayList<>();

        movieList1.add(new MovieItem("The Shawshank Redemption","The Shawshank Redemption image"));
        movieList1.add(new MovieItem("The Godfather","The Godfather image"));
        movieList1.add(new MovieItem("The Godfather: Part II","The Godfather: Part II image"));
        movieList1.add(new MovieItem("The Dark Knight","The Dark Knight image"));
        movieList1.add(new MovieItem("12 Angry Men","12 Angry Men image"));
        movieList1.add(new MovieItem("Schindler's List","Schindler's List image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Return of the King","The Lord of the Rings: The Return of the King image"));
        movieList1.add(new MovieItem("Pulp Fiction","Pulp Fiction image"));
        movieList1.add(new MovieItem("Il buono, il brutto, il cattivo","Il buono, il brutto, il cattivo image"));
        movieList1.add(new MovieItem("Fight Club","Fight Club image"));
        movieList1.add(new MovieItem("Joker","Joker image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Fellowship of the Ring","The Lord of the Rings: The Fellowship of the Ring image"));
        movieList1.add(new MovieItem("Forrest Gump","Forrest Gump image"));
        movieList1.add(new MovieItem("Inception","Inception image"));
        movieList1.add(new MovieItem("Star Wars: Episode V - The Empire Strikes Back","Star Wars: Episode V - The Empire Strikes Back image"));
        movieList1.add(new MovieItem("The Lord of the Rings: The Two Towers","The Lord of the Rings: The Two Towers image"));
        movieList1.add(new MovieItem("The Matrix","The Matrix image"));
        movieList1.add(new MovieItem("One Flew Over the Cuckoo's Nest","One Flew Over the Cuckoo's Nest image"));
        movieList1.add(new MovieItem("Goodfellas","Goodfellas image"));
        movieList1.add(new MovieItem("Shichinin no samurai","Shichinin no samurai image"));
        movieList1.add(new MovieItem("Se7en","Se7en image"));
        movieList1.add(new MovieItem("Cidade de Deus","Cidade de Deus image"));
        movieList1.add(new MovieItem("La vita è bella","La vita è bella image"));
        movieList1.add(new MovieItem("The Silence of the Lambs","The Silence of the Lambs image"));
        movieList1.add(new MovieItem("Star Wars","Star Wars image"));
        movieList1.add(new MovieItem("It's a Wonderful Life","It's a Wonderful Life image"));
        movieList1.add(new MovieItem("Saving Private Ryan","Saving Private Ryan image"));
        movieList1.add(new MovieItem("Sen to Chihiro no kamikakushi","Sen to Chihiro no kamikakushi image"));
        movieList1.add(new MovieItem("The Green Mile","The Green Mile image"));
        movieList1.add(new MovieItem("Léon","Léon image"));
        movieList1.add(new MovieItem("Seppuku","Seppuku image"));
        movieList1.add(new MovieItem("Interstellar","Interstellar image"));
        movieList1.add(new MovieItem("The Usual Suspects","The Usual Suspects image"));
        movieList1.add(new MovieItem("The Lion King","The Lion King image"));
        movieList1.add(new MovieItem("American History X","American History X image"));
        movieList1.add(new MovieItem("Back to the Future","Back to the Future image"));
        movieList1.add(new MovieItem("The Pianist","The Pianist image"));
        movieList1.add(new MovieItem("Modern Times","Modern Times image"));
        movieList1.add(new MovieItem("Terminator 2: Judgment Day","Terminator 2: Judgment Day image"));
        movieList1.add(new MovieItem("Intouchables","Intouchables image"));
        movieList1.add(new MovieItem("Psycho","Psycho image"));
        movieList1.add(new MovieItem("Gladiator","Gladiator image"));
        movieList1.add(new MovieItem("City Lights","City Lights image"));
        movieList1.add(new MovieItem("The Departed","The Departed image"));
        movieList1.add(new MovieItem("Whiplash","Whiplash image"));
        movieList1.add(new MovieItem("C'era una volta il West","C'era una volta il West image"));
        movieList1.add(new MovieItem("The Prestige","The Prestige image"));
        movieList1.add(new MovieItem("Avengers: Endgame","Avengers: Endgame image"));
        movieList1.add(new MovieItem("Casablanca","Casablanca image"));
        movieList1.add(new MovieItem("Hotaru no haka","Hotaru no haka image"));
        movieList1.add(new MovieItem("Rear Window","Rear Window image"));
        movieList1.add(new MovieItem("Nuovo Cinema Paradiso","Nuovo Cinema Paradiso image"));
        movieList1.add(new MovieItem("Alien","Alien image"));
        movieList1.add(new MovieItem("Raiders of the Lost Ark","Raiders of the Lost Ark image"));
        movieList1.add(new MovieItem("Memento","Memento image"));
        movieList1.add(new MovieItem("Apocalypse Now","Apocalypse Now image"));
        movieList1.add(new MovieItem("The Great Dictator","The Great Dictator image"));
        movieList1.add(new MovieItem("Das Leben der Anderen","Das Leben der Anderen image"));
        movieList1.add(new MovieItem("Avengers: Infinity War","Avengers: Infinity War image"));
        movieList1.add(new MovieItem("Django Unchained","Django Unchained image"));
        movieList1.add(new MovieItem("Spider-Man: Into the Spider-Verse","Spider-Man: Into the Spider-Verse image"));
        movieList1.add(new MovieItem("The Shining","The Shining image"));
        movieList1.add(new MovieItem("Paths of Glory","Paths of Glory image"));
        movieList1.add(new MovieItem("WALL·E","WALL·E image"));
        movieList1.add(new MovieItem("Sunset Blvd.","Sunset Blvd. image"));
        movieList1.add(new MovieItem("Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb","Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb image"));
        movieList1.add(new MovieItem("Mononoke-hime","Mononoke-hime image"));
        movieList1.add(new MovieItem("Oldeuboi","Oldeuboi image"));
        movieList1.add(new MovieItem("Witness for the Prosecution","Witness for the Prosecution image"));
        movieList1.add(new MovieItem("The Dark Knight Rises","The Dark Knight Rises image"));
        movieList1.add(new MovieItem("Once Upon a Time in America","Once Upon a Time in America image"));
        movieList1.add(new MovieItem("Gisaengchung","Gisaengchung image"));
        movieList1.add(new MovieItem("Aliens","Aliens image"));
        movieList1.add(new MovieItem("American Beauty","American Beauty image"));
        movieList1.add(new MovieItem("Coco","Coco image"));
        movieList1.add(new MovieItem("Kimi no na wa.","Kimi no na wa. image"));
        movieList1.add(new MovieItem("Braveheart","Braveheart image"));
        movieList1.add(new MovieItem("Das Boot","Das Boot image"));
        movieList1.add(new MovieItem("3 Idiots","3 Idiots image"));
        movieList1.add(new MovieItem("Taare Zameen Par","Taare Zameen Par image"));
        movieList1.add(new MovieItem("Star Wars: Episode VI - Return of the Jedi","Star Wars: Episode VI - Return of the Jedi image"));
        movieList1.add(new MovieItem("Toy Story","Toy Story image"));
        movieList1.add(new MovieItem("Reservoir Dogs","Reservoir Dogs image"));
        movieList1.add(new MovieItem("Amadeus","Amadeus image"));
        movieList1.add(new MovieItem("Dangal","Dangal image"));
        movieList1.add(new MovieItem("Good Will Hunting","Good Will Hunting image"));
        movieList1.add(new MovieItem("Inglourious Basterds","Inglourious Basterds image"));
        movieList1.add(new MovieItem("M - Eine Stadt sucht einen Mörder","M - Eine Stadt sucht einen Mörder image"));
        movieList1.add(new MovieItem("Requiem for a Dream","Requiem for a Dream image"));
        movieList1.add(new MovieItem("2001: A Space Odyssey","2001: A Space Odyssey image"));
        movieList1.add(new MovieItem("Vertigo","Vertigo image"));
        movieList1.add(new MovieItem("Eternal Sunshine of the Spotless Mind","Eternal Sunshine of the Spotless Mind image"));
        movieList1.add(new MovieItem("Citizen Kane","Citizen Kane image"));
        movieList1.add(new MovieItem("Full Metal Jacket","Full Metal Jacket image"));
        movieList1.add(new MovieItem("Jagten","Jagten image"));
        movieList1.add(new MovieItem("North by Northwest","North by Northwest image"));
        movieList1.add(new MovieItem("A Clockwork Orange","A Clockwork Orange image"));
        movieList1.add(new MovieItem("Snatch","Snatch image"));
        movieList1.add(new MovieItem("Le fabuleux destin d'Amélie Poulain","Le fabuleux destin d'Amélie Poulain image"));
        movieList1.add(new MovieItem("The Kid","The Kid image"));
        movieList1.get(0).setRevealed(true);

        final int maxNumber= movieList1.size();

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,10);
        mAdapter = new MovieAdapter(getApplicationContext(),movieList1);

        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);

        random = findViewById(R.id.randomButton);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(maxNumber);
                Toast.makeText(getApplicationContext()," "+ randomNumber,Toast.LENGTH_SHORT).show();

                movieList1.get(randomNumber).setRevealed(true);
                Toast.makeText(getApplicationContext()," "+ randomNumber,Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();



            }
        });


    }


}
