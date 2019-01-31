package com.moonica.fdm;

import java.util.ArrayList;

public class FactoryUtente {
    private static FactoryUtente instance;
    private ArrayList<Utente> listaUtenti = new ArrayList<>();
    private FactoryCorsoDiStudi factoryCorsoDiStudi = FactoryCorsoDiStudi.getInstance();
    private FactoryCorsi factoryCorsi = FactoryCorsi.getInstance();

    private FactoryUtente() {
        Studente studente1 = new Studente();
        studente1.setNome("Ines");
        studente1.setCognome("Lopez");
        studente1.setUsername("Ines");
        studente1.setEmail("ilopez@unica.it");
        studente1.setPassword("@delante");
        studente1.setFacolta("Ingegneria e Architettura");
        studente1.setCorsoStudi("Ingegneria Biomedica"); //

        Studente studente2 = new Studente();
        studente2.setNome("Marcello");
        studente2.setCognome("Spiga");
        studente2.setUsername("Spighetto");
        studente2.setEmail("fspiga@unica.it");
        studente2.setPassword("hypergeo");
        studente2.setFacolta("Scienze Economiche, Giuridiche e Politiche");
        studente2.setCorsoStudi("Scienze Politiche");  //

        Studente studente3 = new Studente();
        studente3.setNome("Francesco");
        studente3.setCognome("Traccis");
        studente3.setUsername("Cosino");
        studente3.setEmail("ftraccis@gmail.com");
        studente3.setPassword("spaghettialpesto");
        studente3.setFacolta("Medicina e Chirurgia");
        studente3.setCorsoStudi("Medicina e Chirurgia"); //

        Studente studente4 = new Studente();
        studente4.setNome("Federica");
        studente4.setCognome("Zanda");
        studente4.setUsername("Federica");
        studente4.setEmail("fzanda@outlook.com");
        studente4.setPassword("commodore64");
        studente4.setFacolta("Scienze");
        studente4.setCorsoStudi("Informatica");

        Professore professore1 = new Professore();
        professore1.setNome("Carlo");
        professore1.setCognome("Giganti");
        professore1.setUsername("Gyganti");
        professore1.setEmail("cgiganti@unica.it");
        professore1.setPassword("excalibur");
        professore1.setFacolta("Studi Umanistici");
        professore1.setCorsoStudi("Lettere"); //storia medievale

        Professore professore2 = new Professore();
        professore2.setNome("Cristina");
        professore2.setCognome("Tronci");
        professore2.setUsername("Troncina");
        professore2.setEmail("cronci@unica.it");
        professore2.setPassword("beaker");
        professore2.setFacolta("Biologia e Farmacia");
        professore2.setCorsoStudi("Chimica e Tecnologie Farmaceutiche");

        listaUtenti.add(studente1);
        listaUtenti.add(studente2);
        listaUtenti.add(studente3);
        listaUtenti.add(studente4);
        listaUtenti.add(professore1);
        listaUtenti.add(professore2);
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
}
