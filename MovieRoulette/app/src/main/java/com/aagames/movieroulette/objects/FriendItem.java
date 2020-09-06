package com.aagames.movieroulette.objects;

public class FriendItem {

    private String username;
    private String email;

    public FriendItem(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public FriendItem() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
