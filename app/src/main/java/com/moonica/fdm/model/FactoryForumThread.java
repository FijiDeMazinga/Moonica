package com.moonica.fdm.model;

import java.util.ArrayList;

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
        thread1.setTitolo("Domanda appello di Gennaio");
        thread1.setData(2017, 0, 13);
        thread1.setAutore("Spighetto");
        thread1.setNumRisposte(3);
        thread1.setCorso("DP", "Diritto Privato");

        ForumThread thread2 = new ForumThread();
        thread2.setTitolo("Richiesta spiegazione funzione della milza");
        thread2.setData(2017, 6, 25);
        thread2.setAutore("Ines");
        thread2.setNumRisposte(5);
        thread2.setCorso("AN", "Anatomia");

        ForumThread thread3 = new ForumThread();
        thread3.setTitolo("Euristiche di Nielsen");
        thread3.setData(2018, 11, 26);
        thread3.setAutore("Federica");
        thread3.setNumRisposte(1);
        thread3.setCorso("IUM", "Interazione Uomo-Macchina");

        ForumThread thread4 = new ForumThread();
        thread4.setTitolo("Approfondimento lezione 12/03/2018");
        thread4.setData(2018,3,15);
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
        return forumThreadCorso;

    }
}
