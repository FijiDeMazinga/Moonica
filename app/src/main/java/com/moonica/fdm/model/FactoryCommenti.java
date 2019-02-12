package com.moonica.fdm.model;

import java.util.ArrayList;

public class FactoryCommenti {

    private static FactoryCommenti singleton;

    public static FactoryCommenti getInstance(){
        if(singleton == null)
            singleton = new FactoryCommenti();
        return singleton;
    }

    private ArrayList<Commento> listaCommenti = new ArrayList<>();

    private FactoryCommenti(){

        Commento c1 = new Commento();
        c1.setAutore("Cosino");
        c1.setData(2017, 2, 28);
        c1.setTesto("Questa è una risposta al tuo commento");
        c1.setFt(1);

        listaCommenti.add(c1);
    }

    public ArrayList<Commento> cercaListaCommenti(int id){

        ArrayList<Commento> commentiThread = new ArrayList<>();

        for (Commento c : listaCommenti){
            if (c.getFt().getId() == id)
                commentiThread.add(c);
        }

        return commentiThread;
    }
}
