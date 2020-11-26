package com.alim.guidebook.MyObject;

public class Tour {

    private String cost;
    private String link;
    private String time;
    private String title;

    public Tour(String title, String time, String link, String cost) {
        this.cost = cost;
        this.link = link;
        this.time = time;
        this.title = title;
    }

    public Tour()
    {

    }


    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public String getLink() {
        return link;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}
