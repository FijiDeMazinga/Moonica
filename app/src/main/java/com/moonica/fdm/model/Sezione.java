package com.moonica.fdm.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sezione {
    private String titolo;
    //private String sottoTitolo;
    private String corso;
    private ArrayList<Contenuto> listaContenuti = new ArrayList<Contenuto>();

    public Sezione(){
        this.titolo = ("");
        this.listaContenuti = getContenuti();
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String nome) {
        this.titolo = nome;
    }

    /*public String getSottoTitolo() {
        return sottoTitolo;
    }*/

   /* public void setSottoTitolo(String sottoTitolo) {
        this.sottoTitolo = sottoTitolo;
    }*/

    public String getCorso() {
        return corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public ArrayList<Contenuto> getContenuti() {
        return listaContenuti;
    }

    public void setContenuti(ArrayList<Contenuto> contenuti) {
        this.listaContenuti = contenuti;
    }

    public void aggiungiContenuto() {

    }
}
