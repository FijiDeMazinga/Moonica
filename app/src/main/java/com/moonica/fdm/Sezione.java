package com.moonica.fdm;

import java.util.ArrayList;

public class Sezione {
    private String nome;
    private String corso;
    private ArrayList<String> contenuti = new ArrayList<String>();

    public Sezione(String nome, String corso, ArrayList<String> contenuti){
        this.contenuti = contenuti;
        this.corso = corso;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCorso() {
        return corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public ArrayList<String> getContenuti() {
        return contenuti;
    }

    public void setContenuti(ArrayList<String> contenuti) {
        this.contenuti = contenuti;
    }

    public void aggiungiContenuto() {

    }
}
