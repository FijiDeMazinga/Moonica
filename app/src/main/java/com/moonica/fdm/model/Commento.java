package com.moonica.fdm.model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class Commento {

    private Utente autore;
    private String testo;
    private Calendar data = Calendar.getInstance(TimeZone.getDefault());
    private ForumThread ft;
    private boolean allegatiPresenza;
    private ArrayList<String> nomeAllegati = new ArrayList<>();
    private ArrayList<Integer> iconaAllegati = new ArrayList<>();

    public Commento(){
        this.setAutore("");
        this.setData(1990, 1,1, 0, 0, 0);
        this.setTesto("");
        this.setFt(-1);
        this.setAllegatiPresenza(false);
        this.setNomeAllegati(new ArrayList<String>());
        this.setIconaAllegati(new ArrayList<Integer>());
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
        c.add(Calendar.HOUR_OF_DAY, 1);

        this.data = c;
    }

    public ForumThread getFt() {
        return ft;
    }

    public void setFt(int id) {
        FactoryForumThread factoryForumThreadt = FactoryForumThread.getInstance();
        this.ft = factoryForumThreadt.cercaThread(id);
    }



    public ArrayList<String> getNomeAllegati() {
        return nomeAllegati;
    }

    public void setNomeAllegati(ArrayList<String> nomeAllegati) {
        this.nomeAllegati = nomeAllegati;
    }
    public void setNomeAllegati(String nomeAllegati) {
        this.nomeAllegati.add(nomeAllegati);
    }

    public void eliminaNomeAllegati(String allegato){
        for (String s : nomeAllegati){
            if (s.equals(allegato)) {
                nomeAllegati.remove(s);
                break;
            }
        }
    }

    public ArrayList<Integer> getIconaAllegati() {
        return iconaAllegati;
    }

    public void setIconaAllegati(ArrayList<Integer> iconaAllegati) {
        this.iconaAllegati = iconaAllegati;
    }
    public void setIconaAllegati(Integer iconaAllegati) {
        this.iconaAllegati.add(iconaAllegati);
    }

    public void eliminaIconaAllegati(Integer icolaAllegati){
        for (Integer i : iconaAllegati)
            if (i.equals(icolaAllegati)) {
                iconaAllegati.remove(i);
                break;
            }
    }

    public boolean isAllegatiPresenza() {
        return allegatiPresenza;
    }

    public void setAllegatiPresenza(boolean allegatiPresenza) {
        this.allegatiPresenza = allegatiPresenza;
    }
}
