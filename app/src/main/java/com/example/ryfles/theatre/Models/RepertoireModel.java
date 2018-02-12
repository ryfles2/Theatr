package com.example.ryfles.theatre.Models;

/**
 * Created by Ryfles on 2018-01-27.
 */

public class RepertoireModel {

    public RepertoireModel() {
    }

    public RepertoireModel(String tytul, String url, String price, String opis, String price2) {
        this.tytul = tytul;
        this.url = url;
        this.price=price;
        this.opis=opis;
        this.price2=price2;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getPrice() {
        return price;

    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String tytul , url,price, opis, price2;
}
