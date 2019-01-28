package com.moonica.fdm;

import java.util.ArrayList;

public class FactoryUtente {
    private static FactoryUtente instance;
    private ArrayList<Utente> listaUtenti = new ArrayList<>();

    private FactoryUtente() {
        Utente utente1 = new Utente();
        utente1.setNome("Ines");
        utente1.setCognome("Lopez");
        utente1.setUsername("Ines");
        utente1.setEmail("ilopez@unica.it");
        utente1.setPassword("@delante");
        utente1.setFacolta("Ingegneria e Architettura");
        utente1.setCorso("Ingegneria Biomedica");
        utente1.setProfessor(false);

        Utente utente2 = new Utente();
        utente2.setNome("Francesco");
        utente2.setCognome("Spiga");
        utente2.setUsername("Spighetto");
        utente2.setEmail("fspiga@unica.it");
        utente2.setPassword("hypergeo");
        utente2.setFacolta("Studi Umanistici");
        utente2.setCorso("Scienze e Tecniche Psicologiche");
        utente2.setProfessor(false);

        Utente utente3 = new Utente();
        utente3.setNome("Carlo");
        utente3.setCognome("Giganti");
        utente3.setUsername("Gyganti");
        utente3.setEmail("cgiganti@unica.it");
        utente3.setPassword("excalibur");
        utente3.setFacolta("Studi Umanistici");
        utente3.setCorso("Lettere");
        utente3.setProfessor(true);

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
}
