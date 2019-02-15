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
        this.setData(1990, 1,1, 0, 0, 0);
        this.setTesto("");
        this.setFt(-1);
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(String nickAutore) {
        FactoryUtente factoryUtente = FactoryUtente.getInstance();
        this.autore = factoryUtente.cercaUtente(nickAutore);
    }

    public void setAutore(Utente u){
        this.autore = u;
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

    public void setData(int y, int m, int d, int h, int min, int s) {
        this.data.set(y, m, d, h, min, s);
    }

    public void setData(Calendar c){
        this.data = c;
    }

    public ForumThread getFt() {
        return ft;
    }

    public void setFt(int id) {
        FactoryForumThread factoryForumThreadt = FactoryForumThread.getInstance();
        this.ft = factoryForumThreadt.cercaThread(id);
    }
}
