package com.moonica.fdm;

import java.util.ArrayList;

public class CorsoDiStudi {
    private String nome;
    private String Facolta;
    protected ArrayList<Corso> listaCorsi = new ArrayList<Corso>();

    public CorsoDiStudi(){
        this.setNome("");
        this.setFacolta("");
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFacolta() {
        return Facolta;
    }

    public void setFacolta(String facolta) {
        Facolta = facolta;
    }
}
