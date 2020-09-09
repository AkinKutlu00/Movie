package com.aagames.movieroulette.tmdb.api;

import com.aagames.movieroulette.tmdb.data.MovieSearchListRecieved;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


    public interface TmdbClient {

        @GET("search/movie")
        Call<MovieSearchListRecieved> getMovies(
                @Query("api_key") String api_key,
                @Query("query") String query
        );

    }

