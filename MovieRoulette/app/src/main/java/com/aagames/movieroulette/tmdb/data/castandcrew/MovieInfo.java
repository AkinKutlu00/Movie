package com.aagames.movieroulette.tmdb.data.castandcrew;


import java.util.List;

import com.aagames.movieroulette.tmdb.data.castandcrew.Cast;
import com.aagames.movieroulette.tmdb.data.castandcrew.Crew;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Crew> crew = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

}
