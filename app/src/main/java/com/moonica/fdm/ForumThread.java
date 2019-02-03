package com.moonica.fdm;

import java.util.Calendar;

public class ForumThread {

    private String titolo;
    private Calendar data;
    private Utente autore;
    private int numRisposte;
    private Corso corso;

    public ForumThread(){
        this.setTitolo("");
        this.setData(1900,1,1);
        this.setAutore("");
        this.setNumRisposte(0);
        this.setCorso(null);
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
        this.data.set(y, m, d);
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
        //funziona per la ricerca dell'autore
        this.autore = autore;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(String nomeCorso) {
        FactoryCorsi fc = FactoryCorsi.getInstance();
        this.corso = fc.cercaCorso(nomeCorso);
    }
}
