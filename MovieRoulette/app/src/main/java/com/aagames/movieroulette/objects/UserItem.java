package com.aagames.movieroulette.objects;

public class UserItem {
    private String username;
    private String mail;
    private String id;

    public UserItem(String username, String mail, String id) {
        this.username = username;
        this.mail = mail;
        this.id = id;
    }

    public UserItem(String username) {
        this.username = username;
        this.id = null;
        this.mail = null;
    }

    public UserItem() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
