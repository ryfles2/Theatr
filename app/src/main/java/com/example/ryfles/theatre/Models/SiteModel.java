package com.example.ryfles.theatre.Models;

/**
 * Created by Ryfles2 on 05.02.2018.
 */

public class SiteModel {
    private String idKupujacego;
    private String status;
    private String pushKeyValue;

    public SiteModel() {
    }

    public String getPushKeyValue() {
        return pushKeyValue;
    }

    public void setPushKeyValue(String pushKeyValue) {
        this.pushKeyValue = pushKeyValue;
    }

    public SiteModel(String idKupujacego, String status , String pushKeyValue) {
        this.idKupujacego = idKupujacego;
        this.status = status;
        this.pushKeyValue=pushKeyValue;

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
