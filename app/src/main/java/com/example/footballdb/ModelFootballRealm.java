package com.example.footballdb;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelFootballRealm extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String judul;
    private String desc;
    private Integer formedYear;
    private String poster;
    private String country;
    private String stadium;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFormedYear() {
        return formedYear;
    }

    public void setFormedYear(int formedYear) {
        this.formedYear = formedYear;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getCountry(){
        return country;
    }
    public  void setCountry (String country){
        this.country = country;
    }
    public String getStadium(){
        return stadium;
    }
    public void setStadium(String stadium){
        this.stadium = stadium;
    }
}
