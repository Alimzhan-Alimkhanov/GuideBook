package com.alim.guidebook.MyObject;

public class Comment {

    private String rating;
    private String login;
    private String id_sight;
    private String email;
    private String date;
    private String comment;


    public Comment(String rating, String login, String id_sight, String email, String date, String comment) {
        this.rating = rating;
        this.login = login;
        this.id_sight = id_sight;
        this.email = email;
        this.date = date;
        this.comment = comment;
    }

    public Comment()
    {

    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId_sight() { return id_sight; }

    public void setId_sight(String id_sight) { this.id_sight = id_sight; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
