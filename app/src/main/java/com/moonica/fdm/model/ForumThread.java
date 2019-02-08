package com.moonica.fdm.model;

import java.util.Calendar;
import java.util.TimeZone;

public class ForumThread {

    private String titolo;
    private String testo;
    private Calendar data = Calendar.getInstance(TimeZone.getDefault());
    private Utente autore;
    private int numRisposte;
    private Corso corso;

    public ForumThread(){
        this.setTitolo("");
        this.setTesto("");
        this.setData(1990,1,1);
        this.setAutore("");
        this.setNumRisposte(0);
        this.setCorso(null, null);
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(int y, int m, int d) {
        this.data.set(y,m,d);
    }


    public int getNumRisposte() {
        return numRisposte;
    }

    public void setNumRisposte(int numRisposte) {
        this.numRisposte = numRisposte;
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(String nickAutore) {
       FactoryUtente factoryUtente = FactoryUtente.getInstance();
       this.autore = factoryUtente.cercaUtente(nickAutore);
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(String codice, String nomeCorso) {
        FactoryCorsi fc = FactoryCorsi.getInstance();
        this.corso = fc.cercaCorso(codice,nomeCorso);
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
