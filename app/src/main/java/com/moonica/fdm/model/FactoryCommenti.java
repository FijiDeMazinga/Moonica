package com.moonica.fdm.model;

import org.w3c.dom.Comment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FactoryCommenti {

    private static FactoryCommenti singleton;

    public static FactoryCommenti getInstance() {
        if (singleton == null)
            singleton = new FactoryCommenti();
        return singleton;
    }

    private ArrayList<Commento> listaCommenti = new ArrayList<>();

    private FactoryCommenti() {

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
        c4.setData(2017, 7, 30, 22, 10, 22);
        c4.setTesto("Gracias a ti professor");
        c4.setFt(1);

        Commento c5 = new Commento();
        c5.setAutore("Cosino");
        c5.setData(2018, 1, 18, 17, 33, 18);
        c5.setTesto("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean in ipsum augue. Morbi quis dui sed libero viverra porttitor. Vivamus tempus est quam, vel laoreet erat elementum id. Vivamus ante sem, feugiat molestie libero ut, blandit luctus eros. Proin laoreet justo ut orci ornare, id ornare magna egestas. Etiam gravida mi id mattis ornare. Quisque elit urna, consequat ac dapibus et, euismod eget ante. Praesent porttitor maximus volutpat. Nunc facilisis diam ac purus tincidunt, ut feugiat libero porta. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Ut interdum diam urna. Integer pretium bibendum accumsan. Morbi vulputate pharetra metus, a malesuada orci laoreet nec. Pellentesque eleifend mattis sollicitudin.\n" +
                "\n" +
                "Quisque at faucibus ante, porttitor blandit nulla. Sed et sapien eget ex commodo bibendum vitae sit amet ante. Phasellus interdum est egestas erat sagittis venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Pellentesque rhoncus orci augue, sit amet aliquam mi varius quis. Etiam vulputate sapien quis dapibus tincidunt. Donec fermentum ultricies sapien a commodo. Sed vehicula feugiat ligula, ut posuere nisl mollis a. In cursus, justo iaculis pretium consectetur, sapien dui sagittis turpis, vel tempus leo ligula eget neque. Ut sit amet rhoncus velit, et sagittis ligula. Maecenas varius sodales diam sit amet maximus. Vivamus pellentesque lectus quis felis consequat porta. Donec non risus in lectus aliquet eleifend a et turpis.");
        c5.setFt(3);

        Commento c6 = new Commento();
        c6.setAutore("Cosino");
        c6.setData(2018, 8, 21, 22, 18, 27);
        c6.setTesto("Ciao");
        c6.setFt(3);

        listaCommenti.add(c1);
        listaCommenti.add(c2);
        listaCommenti.add(c3);
        listaCommenti.add(c4);
        listaCommenti.add(c5);
        listaCommenti.add(c6);
    }

    public ArrayList<Commento> cercaListaCommenti(int id) {

        ArrayList<Commento> commentiThread = new ArrayList<>();

        for (Commento c : listaCommenti) {
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

    public void aggiungiCommentoLista(Commento c) {
        this.listaCommenti.add(c);
    }


    public int numRisposteThread(int id) {

        int numRisposte = 0;

        for (Commento c : listaCommenti)
            if (c.getFt().equals(id))
                numRisposte++;
        return numRisposte;
    }


}
