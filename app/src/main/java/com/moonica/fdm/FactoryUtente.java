package com.moonica.fdm;

import java.util.ArrayList;

public class FactoryUtente {
    private static FactoryUtente instance;
    private ArrayList<Utente> listaUtenti = new ArrayList<>();
    private FactoryCorsoDiStudi fcs = FactoryCorsoDiStudi.getInstance();

    private FactoryUtente() {
        Utente utente1 = new Utente();
        utente1.setNome("Ines");
        utente1.setCognome("Lopez");
        utente1.setUsername("Ines");
        utente1.setEmail("ilopez@unica.it");
        utente1.setPassword("@delante");
        utente1.setFacolta("Ingegneria e Architettura");
        utente1.setCorsoStudi();  //Ingegneria Biomedica
        utente1.aggiungiCorso(null);  //biomateriali
        utente1.aggiungiCorso(null);  //
        utente1.aggiungiCorso(null);  //
        utente1.setProfessor(false);

        Utente utente2 = new Utente();
        utente2.setNome("Marcello");
        utente2.setCognome("Spiga");
        utente2.setUsername("Spighetto");
        utente2.setEmail("fspiga@unica.it");
        utente2.setPassword("hypergeo");
        utente2.setFacolta("Scienze Economiche Giuridiche e Politiche");
        utente2.setCorsoStudi("Scienze Politiche");
        utente2.aggiungiCorso(null);  //
        utente2.aggiungiCorso(null);  //
        utente2.aggiungiCorso(null);  //
        utente2.setProfessor(false);

        Utente utente3 = new Utente();
        utente3.setNome("Carlo");
        utente3.setCognome("Giganti");
        utente3.setUsername("Gyganti");
        utente3.setEmail("cgiganti@unica.it");
        utente3.setPassword("excalibur");
        utente3.setFacolta("Studi Umanistici");
        utente3.setCorsoStudi("Lettere");
        utente3.aggiungiCorso(null);  //storia medievale
        utente3.setProfessor(true);

        Utente utente4 = new Utente();
        utente4.setNome("Francesco");
        utente4.setCognome("Traccis");
        utente4.setUsername("Cosino");
        utente4.setEmail("ftraccis@gmail.com");
        utente4.setPassword("spaghettialpesto");
        utente4.setFacolta("Medicina e Chirurgia");
        utente4.setCorsoStudi("Medicina e Chirurgia");
        utente4.aggiungiCorso(null);  //
        utente4.aggiungiCorso(null);  //
        utente4.aggiungiCorso(null);  //
        utente4.setProfessor(false);

        Utente utente5 = new Utente();
        utente5.setNome("");
        utente5.setCognome("");
        utente5.setUsername("");
        utente5.setEmail("");
        utente5.setPassword("");
        utente5.setFacolta("");
        utente5.setCorsoStudi("");
        utente5.aggiungiCorso(null);
        utente5.setProfessor(false);

        Utente utente6 = new Utente();
        utente6.setNome("");
        utente6.setCognome("");
        utente6.setUsername("");
        utente6.setEmail("");
        utente6.setPassword("");
        utente6.setFacolta("");
        utente6.setCorsoStudi("");
        utente6.aggiungiCorso(null);
        utente6.setProfessor(false);

        listaUtenti.add(utente1);
        listaUtenti.add(utente2);
        listaUtenti.add(utente3);
    }

    public static FactoryUtente getInstance() {
        if (instance == null)
            instance = new FactoryUtente();

        return instance;
    }

    public ArrayList<Utente> getListaUtenti() {
        return listaUtenti;
    }

    public Utente getUtente(String username, String password) {
        for (Utente utente : listaUtenti)
            if (username.equals(utente.getUsername()) && password.equals(utente.getPassword()))
                return utente;

        return null;
    }
}
