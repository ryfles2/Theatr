package com.example.ryfles.theatre.Models;

/**
 * Created by Ryfles on 2018-02-07.
 */

public class MyTicketsModel {
    String tytul, data, godzina, miejsce, status  ;

    public String getTytul() {
        return tytul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }



    public MyTicketsModel(String tytul, String data, String godzina, String miejsce, String status) {
        this.tytul = tytul;
        this.data = data;
        this.godzina = godzina;
        this.miejsce = miejsce;
        this.status = status;
    }

    public MyTicketsModel() {
    }
}
