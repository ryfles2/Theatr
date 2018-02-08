package com.example.ryfles.theatre.Models;

/**
 * Created by Ryfles on 2018-01-27.
 */

public class RepertoireModel {

    public RepertoireModel() {
    }

    public RepertoireModel(String tytul, String url, String price) {
        this.tytul = tytul;
        this.url = url;
        this.price=price;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getUrl() {
        return url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String tytul , url,price;
}
