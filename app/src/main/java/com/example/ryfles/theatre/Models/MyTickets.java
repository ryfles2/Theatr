package com.example.ryfles.theatre.Models;

/**
 * Created by Ryfles on 2018-02-07.
 */

public class MyTickets {
    String tytul, data, godzina , miejsce ,idMiejce;

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        tytul = tytul;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdMiejce() {
        return idMiejce;
    }

    public void setIdMiejce(String idMiejce) {
        this.idMiejce = idMiejce;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public MyTickets(String tytul, String data, String godzina, String miejsce, String idMiejce) {
        this.tytul = tytul;
        this.data = data;
        this.idMiejce = idMiejce;
        this.godzina = godzina;
        this.miejsce = miejsce;
    }

    public MyTickets() {
    }
}
