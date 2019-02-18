package com.moonica.fdm.model;

import com.moonica.fdm.R;

import java.util.ArrayList;

public class FactoryUtente {
    private static FactoryUtente instance;
    private ArrayList<Utente> listaUtenti = new ArrayList<>();
    private FactoryCorsoDiStudi factoryCorsoDiStudi = FactoryCorsoDiStudi.getInstance();

    private FactoryUtente() {
        FactoryCorsi factoryCorsi = FactoryCorsi.getInstance();

        Studente studente1 = new Studente();
        studente1.setNome("Ines");
        studente1.setCognome("Lopez");
        studente1.setSesso("Femmina");
        studente1.setUsername("Ines");
        studente1.setEmail("ilopez@unica.it");
        studente1.setPassword("111");
        studente1.setFacolta("Ingegneria e Architettura");
        studente1.setCorsoStudi("Ingegneria Biomedica");
        studente1.setAvatar(R.drawable.avatar_ines);
        studente1.aggiungiCorso(factoryCorsi.cercaCorso("Biomateriali"));
        studente1.aggiungiCorso(factoryCorsi.cercaCorso("Anatomia"));
        studente1.aggiungiCorso(factoryCorsi.cercaCorso("Chimica"));

        Studente studente2 = new Studente();
        studente2.setNome("Marcello");
        studente2.setCognome("Spiga");
        studente2.setSesso("Maschio");
        studente2.setUsername("Spighetto");
        studente2.setEmail("fspiga@unica.it");
        studente2.setPassword("hypergeo");
        studente2.setFacolta("Scienze Economiche, Giuridiche e Politiche");
        studente2.setCorsoStudi("Scienze Politiche");
        studente2.aggiungiCorso(factoryCorsi.cercaCorso("Economia Politica"));
        studente2.aggiungiCorso(factoryCorsi.cercaCorso("Diritto Privato"));

        Studente studente3 = new Studente();
        studente3.setNome("Francesco");
        studente3.setCognome("Traccis");
        studente3.setSesso("Non specificato");
        studente3.setUsername("Cosino");
        studente3.setEmail("ftraccis@gmail.com");
        studente3.setPassword("111");
        studente3.setFacolta("Medicina e Chirurgia");
        studente3.setCorsoStudi("Medicina e Chirurgia");
        studente3.setAvatar(R.drawable.avatar_cosino);
        studente3.aggiungiCorso(factoryCorsi.cercaCorso("Fisiologia"));
        studente3.aggiungiCorso(factoryCorsi.cercaCorso("Anatomia Patologica"));
        studente3.aggiungiCorso(factoryCorsi.cercaCorso("Oncologia"));

        Studente studente4 = new Studente();
        studente4.setNome("Federica");
        studente4.setCognome("Zanda");
        studente4.setSesso("Femmina");
        studente4.setUsername("Federica");
        studente4.setEmail("fzanda@outlook.com");
        studente4.setPassword("111");
        studente4.setFacolta("Scienze");
        studente4.setCorsoStudi("Informatica");
        studente4.aggiungiCorso(factoryCorsi.cercaCorso("Algoritmi e Strutture Dati 1"));
        studente4.aggiungiCorso(factoryCorsi.cercaCorso("Programmazione 2"));
        studente4.aggiungiCorso(factoryCorsi.cercaCorso("Sistemi Operativi 1"));
        studente4.aggiungiCorso(factoryCorsi.cercaCorso("Interazione Uomo-Macchina"));

        Professore professore1 = new Professore();
        professore1.setNome("Carlo");
        professore1.setCognome("Giganti");
        professore1.setSesso("Maschio");
        professore1.setUsername("Gyganti");
        professore1.setEmail("cgiganti@unica.it");
        professore1.setPassword("excalibur");
        professore1.setFacolta("Studi Umanistici");
        professore1.setCorsoStudi("Lettere"); //storia medievale
        professore1.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Storia Medievale"));

        Professore professore2 = new Professore();
        professore2.setNome("Cristina");
        professore2.setCognome("Tronci");
        professore2.setSesso("Femmina");
        professore2.setUsername("Troncina");
        professore2.setEmail("cronci@unica.it");
        professore2.setPassword("beaker");
        professore2.setFacolta("Biologia e Farmacia");
        professore2.setCorsoStudi("Chimica e Tecnologie Farmaceutiche");
        professore2.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Chimica Organica"));

        Professore professore3 = new Professore();
        professore3.setNome("Gian Carlo");
        professore3.setCognome("Mosco");
        professore3.setSesso("Maschio");
        professore3.setUsername("SriLankas");
        professore3.setEmail("nicolas@unica.it");
        professore3.setPassword("Brasil3");
        professore3.setFacolta("Ingegneria e Architettura");
        professore3.setCorsoStudi("Ingegneria Biomedica");
        professore3.setAvatar(R.drawable.avatar_prof3);
        professore3.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Anatomia"));

        listaUtenti.add(studente1);
        listaUtenti.add(studente2);
        listaUtenti.add(studente3);
        listaUtenti.add(studente4);
        listaUtenti.add(professore1);
        listaUtenti.add(professore2);
        listaUtenti.add(professore3);
    }

    public static FactoryUtente getInstance() {
        if (instance == null)
            instance = new FactoryUtente();

        return instance;
    }

    public ArrayList<Utente> getListaUtenti() {
        return listaUtenti;
    }

    public Utente cercaUtente(String username, String password) {
        for (Utente utente : listaUtenti)
            if (username.equals(utente.getUsername()) && password.equals(utente.getPassword()))
                return utente;

        return null;
    }

    public Utente cercaUtente(String username){
        for (Utente utente : listaUtenti)
            if (username.equals(utente.getUsername()))
                return utente;
         return null;
    }

    public ArrayList<Utente> aggiungiUtente (Utente u){
        this.listaUtenti.add(u);
        return listaUtenti;
    }
}
