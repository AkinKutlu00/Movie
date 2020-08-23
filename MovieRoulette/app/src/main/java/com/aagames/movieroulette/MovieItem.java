package com.aagames.movieroulette;

public class MovieItem {
    private String name;
    private String imageCode;
    private boolean revealed;

    public MovieItem(String name, String imageCode){

        this.name=name;
        this.imageCode=imageCode;
        revealed=false;
    }

    public MovieItem() {
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
