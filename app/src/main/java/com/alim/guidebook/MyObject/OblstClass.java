package com.alim.guidebook.MyObject;

public class OblstClass {

    private  String name;
    private  String ru_name;
    private  int image;
    private  String distance;
    private  String link;

    public OblstClass(String name, String ru_name, int image,String distance,String link) {
        this.name = name;
        this.ru_name= ru_name;
        this.image = image;
        this.distance = distance;
        this.link = link;
    }

    public String getName() {
        return name;
    }


    public String getRu_name() {
        return ru_name;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRu_name(String ru_name) {
        this.ru_name = ru_name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDistance() { return distance; }

    public void setDistance(String distance) { this.distance = distance; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }
}
