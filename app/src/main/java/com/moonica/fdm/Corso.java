package com.moonica.fdm;

import java.util.ArrayList;

public class Corso {
    private String nome;
    private String sigla;
    private String facolta;
    private ArrayList<Sezione> sezioni = new ArrayList<Sezione>();

    public Corso(String nome, String sigla){
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getFacolta() {
        return facolta;
    }

    public void setFacolta(String facolta) {
        this.facolta = facolta;
    }

    public ArrayList<Sezione> getSezioni() {
        return sezioni;
    }

    public void setSezioni(ArrayList<Sezione> sezioni) {
        this.sezioni = sezioni;
    }
}
