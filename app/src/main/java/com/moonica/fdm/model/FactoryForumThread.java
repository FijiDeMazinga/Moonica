package com.moonica.fdm.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class FactoryForumThread {
    private static FactoryForumThread singleton;

    public static FactoryForumThread getInstance(){
        if(singleton == null)
            singleton = new FactoryForumThread();
        return singleton;
    }

    private ArrayList<ForumThread> listaForumThread = new ArrayList<>();

    private FactoryForumThread (){
        ForumThread thread1 = new ForumThread();
        thread1.setId(0);
        thread1.setTitolo("Domanda appello di Gennaio");
        thread1.setData(2017, 0, 13, 12, 24, 34);
        thread1.setAutore("Spighetto");
        thread1.setNumRisposte(3);
        thread1.setCorso("DP", "Diritto Privato");

        ForumThread thread2 = new ForumThread();
        thread2.setId(1);
        thread2.setTitolo("Richiesta spiegazione funzione della milza");
        thread2.setData(2017, 6, 25, 17, 54, 21);
        thread2.setAutore("Ines");
        thread2.setNumRisposte(5);
        thread2.setCorso("AN", "Anatomia");

        ForumThread thread3 = new ForumThread();
        thread3.setId(2);
        thread3.setTitolo("Euristiche di Nielsen");
        thread3.setData(2018, 11, 26, 23, 12, 33);
        thread3.setAutore("Federica");
        thread3.setNumRisposte(1);
        thread3.setCorso("IUM", "Interazione Uomo-Macchina");

        ForumThread thread4 = new ForumThread();
        thread4.setId(3);
        thread4.setTitolo("Approfondimento lezione 12/03/2018");
        thread4.setData(2018,3,15, 2, 14, 59);
        thread4.setAutore("Ines");
        thread4.setNumRisposte(2);
        thread4.setCorso("AN", "Anatomia");

        listaForumThread.add(thread1);
        listaForumThread.add(thread2);
        listaForumThread.add(thread3);
        listaForumThread.add(thread4);
    }

    public ArrayList<ForumThread> cercaThreadCorso (Corso crs){

        ArrayList<ForumThread> forumThreadCorso = new ArrayList<>();
        if (crs == null)
            return null;
        for (ForumThread ft : listaForumThread)
            if (ft.getCorso().equals(crs))
                forumThreadCorso.add(ft);
        Collections.sort(forumThreadCorso, new Comparator<ForumThread>() {
            @Override
            public int compare(ForumThread o1, ForumThread o2) {
                return o2.getData().getTime().compareTo(o1.getData().getTime());
            }
        });

        return forumThreadCorso;
    }

    public ForumThread cercaThread (int id){

        if ( id == (-1))
            return null;
        for (ForumThread ft : listaForumThread)
            if (ft.getId() == id)
                return ft;
            return null;

    }

}
