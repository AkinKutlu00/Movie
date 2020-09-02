package com.aagames.movieroulette;

import java.util.ArrayList;

public class MovieList {

    // properties

    String name;
    ArrayList<MovieItem> movies;

    // constructor

    public MovieList ( String name, ArrayList<MovieItem> movies)
    {
        this.name= name;
        this.movies = movies;
    }

    public MovieList ( String name)
    {
        this.name= name;
    }

    public MovieList ()
    {
    }

    // methods


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MovieItem> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieItem> movies) {
        this.movies = movies;
    }

    public void addMovie( MovieItem newOne )
    {
        movies.add( newOne );
    }

    public void removeMovie( MovieItem oldOne )
    {
        movies.remove( oldOne );
    }
}
