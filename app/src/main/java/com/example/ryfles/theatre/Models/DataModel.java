package com.example.ryfles.theatre.Models;

/**
 * Created by Ryfles on 2018-01-27.
 */

public class DataModel {
    private String  data,godzina, idMiejsce, idTytul;

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

    public String getIdMiejsce() {
        return idMiejsce;
    }

    public void setIdMiejsce(String idMiejsce) {
        this.idMiejsce = idMiejsce;
    }

    public String getIdTytul() {
        return idTytul;
    }

    public void setIdTytul(String idTytul) {
        this.idTytul = idTytul;
    }

    public DataModel(String data, String godzina, String idMiejsce, String idTytul) {
        this.data = data;
        this.godzina = godzina;
        this.idMiejsce = idMiejsce;
        this.idTytul = idTytul;
    }

    public DataModel() {
    }
}
