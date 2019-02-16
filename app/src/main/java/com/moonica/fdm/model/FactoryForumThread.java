package com.moonica.fdm.model;

import com.moonica.fdm.controller.Forum;

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
        thread1.setTesto("Salve, mi chiedevo se è stato confermato il primo appello del prossimo" +
                "Gennaio. Ciao a tutti");
        thread1.setData(2017, 0, 13, 12, 24, 34);
        thread1.setAutore("Spighetto");
        thread1.setNumRisposte(3);
        thread1.setCorso("DP", "Diritto Privato");

        ForumThread thread2 = new ForumThread();
        thread2.setId(1);
        thread2.setTitolo("Richiesta spiegazione funzione della milza");
        thread2.setTesto("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean in ipsum augue. Morbi quis dui sed libero viverra porttitor. Vivamus tempus est quam, vel laoreet erat elementum id. Vivamus ante sem, feugiat molestie libero ut, blandit luctus eros. Proin laoreet justo ut orci ornare, id ornare magna egestas. Etiam gravida mi id mattis ornare. Quisque elit urna, consequat ac dapibus et, euismod eget ante. Praesent porttitor maximus volutpat. Nunc facilisis diam ac purus tincidunt, ut feugiat libero porta. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Ut interdum diam urna. Integer pretium bibendum accumsan. Morbi vulputate pharetra metus, a malesuada orci laoreet nec. Pellentesque eleifend mattis sollicitudin.\n" +
                "\n" +
                "Quisque at faucibus ante, porttitor blandit nulla. Sed et sapien eget ex commodo bibendum vitae sit amet ante. Phasellus interdum est egestas erat sagittis venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Pellentesque rhoncus orci augue, sit amet aliquam mi varius quis. Etiam vulputate sapien quis dapibus tincidunt. Donec fermentum ultricies sapien a commodo. Sed vehicula feugiat ligula, ut posuere nisl mollis a. In cursus, justo iaculis pretium consectetur, sapien dui sagittis turpis, vel tempus leo ligula eget neque. Ut sit amet rhoncus velit, et sagittis ligula. Maecenas varius sodales diam sit amet maximus. Vivamus pellentesque lectus quis felis consequat porta. Donec non risus in lectus aliquet eleifend a et turpis.");
        thread2.setData(2017, 6, 25, 17, 54, 21);
        thread2.setAutore("Ines");
        thread2.setNumRisposte(5);
        thread2.setCorso("AN", "Anatomia");

        ForumThread thread3 = new ForumThread();
        thread3.setId(2);
        thread3.setTitolo("Euristiche di Nielsen");
        thread3.setTesto("Questa è una domanda su una delle euristiche di Nielsen che non ho capito");
        thread3.setData(2018, 11, 26, 23, 12, 33);
        thread3.setAutore("Federica");
        thread3.setNumRisposte(1);
        thread3.setCorso("IUM", "Interazione Uomo-Macchina");

        ForumThread thread4 = new ForumThread();
        thread4.setId(3);
        thread4.setTitolo("Approfondimento lezione 12/03/2018");
        thread4.setTesto("Provaprovaprova");
        thread4.setData(2018,3,15, 2, 14, 59);
        thread4.setAutore("Ines");
        thread4.setNumRisposte(2);
        thread4.setCorso("AN", "Anatomia");

        listaForumThread.add(thread1);
        listaForumThread.add(thread2);
        listaForumThread.add(thread3);
        listaForumThread.add(thread4);
        listaForumThread.add(thread2);
        listaForumThread.add(thread4);
        listaForumThread.add(thread2);
        listaForumThread.add(thread4);
        listaForumThread.add(thread2);

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
    public void aggiungiNumRisposte (int id){
        ForumThread ft = cercaThread(id);
        ft.setNumRisposte((ft.getNumRisposte())+1);
    }
}
