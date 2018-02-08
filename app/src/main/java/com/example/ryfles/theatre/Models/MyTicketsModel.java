package com.example.ryfles.theatre.Models;

/**
 * Created by Ryfles on 2018-02-07.
 */

public class MyTicketsModel {
    String tytul, data, godzina, miejsce, status , idMiejsca, price ;

    public String getTytul() {
        return tytul;
    }

    public String getStatus() {
        return status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIdMiejsca() {
        return idMiejsca;
    }

    public void setIdMiejsca(String idMiejsca) {
        this.idMiejsca = idMiejsca;
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



    public MyTicketsModel(String tytul, String data, String godzina, String miejsce, String status, String idMiejsca, String price) {
        this.tytul = tytul;
        this.data = data;
        this.godzina = godzina;
        this.miejsce = miejsce;
        this.status = status;
        this.idMiejsca=idMiejsca;
        this.price=price;
    }

    public MyTicketsModel() {
    }
}
