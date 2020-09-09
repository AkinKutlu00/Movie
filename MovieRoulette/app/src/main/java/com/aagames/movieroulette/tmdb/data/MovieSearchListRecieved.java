package com.aagames.movieroulette.tmdb.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchListRecieved {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    private List<MovieResult> movieResults;

    public Integer getPage() {
        return page;
    }


    public Integer getTotalResults() {
        return totalResults;
    }


    public Integer getTotalPages() {
        return totalPages;
    }


    public List<MovieResult> getMovieResults() {
        return movieResults;
    }


}
