package com.example.ryfles.theatre.Models;

/**
 * Created by Ryfles2 on 05.02.2018.
 */

public class SiteModel {
    private String idKupujacego;
    private String status;

    public SiteModel() {
    }

    public SiteModel(String idKupujacego, String status) {
        this.idKupujacego = idKupujacego;
        this.status = status;
    }

    public String getIdKupujacego() {
        return idKupujacego;
    }

    public void setIdKupujacego(String idKupujacego) {
        this.idKupujacego = idKupujacego;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
