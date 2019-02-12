package com.moonica.fdm.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        c1.setData(2017, 7, 28, 12, 24, 54);
        c1.setTesto("Questa è una risposta al tuo commento");
        c1.setFt(1);

        Commento c2 = new Commento();
        c2.setAutore("Ines");
        c2.setData(2017, 7, 28, 14, 43, 2);
        c2.setTesto("Grazie mille per la tua gentilezza, questo commento è stato tradotto con google translate" +
                "perché non sono ancora brava nella vostra lingua");
        c2.setFt(1);

        Commento c3 = new Commento();
        c3.setAutore("SriLankas");
        c3.setData(2017, 7, 30, 21, 12, 32);
        c3.setTesto("Oh oh oh, felice di avervi aiutati ragazzi!");
        c3.setFt(1);

        Commento c4 = new Commento();
        c4.setAutore("Ines");
        c4.setData(2017, 7,30, 22, 10, 22);
        c4.setTesto("Gracias a ti professor");
        c4.setFt(1);

        listaCommenti.add(c1);
        listaCommenti.add(c2);
        listaCommenti.add(c3);
        listaCommenti.add(c4);
    }

    public ArrayList<Commento> cercaListaCommenti(int id){

        ArrayList<Commento> commentiThread = new ArrayList<>();

        for (Commento c : listaCommenti){
            if (c.getFt().getId() == id)
                commentiThread.add(c);
        }

        Collections.sort(commentiThread, new Comparator<Commento>() {
            @Override
            public int compare(Commento o1, Commento o2) {
                return o2.getData().getTime().compareTo(o1.getData().getTime());
            }
        });

        Collections.reverse(commentiThread);

        return commentiThread;
    }
}
