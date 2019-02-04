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
        thread1.setData(2017, 1, 13);
        thread1.setAutore("Spighetto");
        thread1.setNumRisposte(3);
        thread1.setCorso("DP", "Diritto Privato");

        ForumThread thread2 = new ForumThread();
        thread1.setTitolo("Richiesta spiegazione funzione della milza");
        thread1.setData(2017, 6, 25);
        thread1.setAutore("Ines");
        thread1.setNumRisposte(5);
        thread1.setCorso("AN", "Anatomia");
    }

    public ArrayList<ForumThread> cercaThreadCorso (Corso crs){

        ArrayList<ForumThread> forumThreadCorso = new ArrayList<>();
        if (crs == null)
            return null;
        for (ForumThread ft : listaForumThread)
            if (ft.equals(crs))
                forumThreadCorso.add(ft);
        return forumThreadCorso;

    }
}
