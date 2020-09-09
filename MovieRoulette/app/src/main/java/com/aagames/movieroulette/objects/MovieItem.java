package com.aagames.movieroulette.objects;

public class MovieItem {
    private String name;
    private String imageCode;
    private boolean revealed;
    private int movieid;

    public MovieItem(String name, String imageCode, int movieid, Boolean revealed){
        this.movieid=movieid;
        this.name=name;
        this.imageCode=imageCode;
        this.revealed=revealed;
    }


    public MovieItem(String name, String imageCode, int movieid){
        this.movieid=movieid;
        this.name=name;
        this.imageCode=imageCode;
        this.revealed=false;
    }
    public MovieItem() {
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getName() {
        return name;
    }

    public String getImageCode(){
        return imageCode;
    }

    public boolean getRevealed(){
        return revealed;
    }

    public void setName(String newName){
        name=newName;
    }

    public void setimageCode(String newImageCode){
        imageCode=newImageCode;
    }

    public void setRevealed(boolean newRevealed){
        revealed=newRevealed;
    }

}
