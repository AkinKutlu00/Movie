package com.aagames.movieroulette.tmdb.api;

import com.aagames.movieroulette.tmdb.data.castandcrew.MovieInfo;
import com.aagames.movieroulette.tmdb.data.search.MovieResult;
import com.aagames.movieroulette.tmdb.data.search.MovieSearchListRecieved;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


    public interface TmdbClient {

        @GET("search/movie")
        Call<MovieSearchListRecieved> getMovies(
                @Query("api_key") String api_key,
                @Query("query") String query,
                @Query("include_adult") boolean include_adult

        );

        @GET("movie/{movie_id}/credits")
        Call<MovieInfo> getMovieCredits(
                @Path("movie_id") int movie_id,
                @Query("api_key") String api_key

        );

        @GET("movie/{movie_id}")
        Call<MovieResult> getMovieInformation(
                @Path("movie_id") int movie_id,
                @Query("api_key") String api_key

        );

    }

