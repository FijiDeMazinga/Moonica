package com.moonica.fdm.model;

import java.util.Calendar;
import java.util.TimeZone;

public class Commento {

    private Utente autore;
    private String testo;
    private Calendar data = Calendar.getInstance(TimeZone.getDefault());
    private ForumThread ft;

    public Commento(){
        this.setAutore("");
        this.setData(1990, 1,1);
        this.setTesto("");
        this.setFt(null);
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(String nickAutore) {
        FactoryUtente factoryUtente = FactoryUtente.getInstance();
        this.autore = factoryUtente.cercaUtente(nickAutore);
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(int y, int m, int d) {
        this.data.set(y, m, d);
    }

    public ForumThread getFt() {
        return ft;
    }

    public void setFt(ForumThread ft) {
        this.ft = ft;
    }
}
