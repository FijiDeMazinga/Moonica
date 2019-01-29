package com.moonica.fdm;

import java.util.ArrayList;

public class Facolta {
    private String nome;
    private ArrayList<Corso> listaCorsi = new ArrayList<Corso>();

    public Facolta(String nome, ArrayList<Corso> listaCorsi){
        this.setNome(nome);
        this.listaCorsi = listaCorsi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
