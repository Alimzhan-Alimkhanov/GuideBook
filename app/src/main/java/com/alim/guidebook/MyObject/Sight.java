package com.alim.guidebook.MyObject;

public class Sight {

    private String type;
    private String town;
    private String title_ru;
    private String title_kz;
    private String time;
    private String text_ru;
    private String text_kz;
    private String street;
    private String id_img;
    private String cost;
    private String contact;
    private String add_f3;
    private String add_f2;
    private String add_f1;


    public Sight(String type,String town, String title_ru, String title_kz, String time, String text_ru, String text_kz,
                 String street,String id_img, String cost, String contact,String add_f3,String add_f2,String add_f1) {
        this.type= type;
        this.town = town;
        this.title_ru = title_ru;
        this.title_kz = title_kz;
        this.time = time;
        this.text_ru = text_ru;
        this.text_kz = text_kz;
        this.street = street;
        this.id_img = id_img;
        this.cost = cost;
        this.contact = contact;
        this.add_f3 = add_f3;
        this.add_f1 = add_f2;
        this.add_f2 = add_f1;
    }

    public Sight()
    {

    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setTitle_kz(String title_kz) {
        this.title_kz = title_kz;
    }

    public void setTitle_ru(String title_ru) {
        this.title_ru = title_ru;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setText_kz(String text_kz) {
        this.text_kz = text_kz;
    }

    public void setText_ru(String text_ru) {
        this.text_ru = text_ru;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setId_img(String id_img) { this.id_img = id_img; }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAdd_f1(String add_f1) {
        this.add_f1 = add_f1;
    }

    public void setAdd_f2(String add_f2) {
        this.add_f2 = add_f2;
    }

    public void setAdd_f3(String add_f3) {
        this.add_f3 = add_f3;
    }

    public String getType() {
        return type;
    }

    public String getTown() {
        return town;
    }

    public String getTitle_kz() {
        return title_kz;
    }

    public String getTitle_ru() {
        return title_ru;
    }

    public String getTime() {
        return time;
    }

    public String getText_kz() {
        return text_kz;
    }

    public String getText_ru() {
        return text_ru;
    }

    public String getStreet() {
        return street;
    }

    public String getId_img() { return id_img; }

    public String getCost() {
        return cost;
    }

    public String getContact() {
        return contact;
    }

    public String getAdd_f1() {
        return add_f1;
    }

    public String getAdd_f2() {
        return add_f2;
    }

    public String getAdd_f3() {
        return add_f3;
    }
}
