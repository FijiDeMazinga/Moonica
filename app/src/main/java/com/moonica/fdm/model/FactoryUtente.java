package com.moonica.fdm.model;

import com.moonica.fdm.R;

import java.util.ArrayList;

public class FactoryUtente {
    private static FactoryUtente instance;
    private ArrayList<Utente> listaUtenti = new ArrayList<>();
    private ArrayList<Studente> listaStudenti = new ArrayList<>();
    private ArrayList<Professore> listaProfessori  = new ArrayList<>();
    private FactoryCorsoDiStudi factoryCorsoDiStudi = FactoryCorsoDiStudi.getInstance();
    FactoryCorsi factoryCorsi = FactoryCorsi.getInstance();

    private FactoryUtente() {

        Studente studente1 = new Studente();
        studente1.setNome("Ines");
        studente1.setCognome("Lopez");
        studente1.setSesso("Femmina");
        studente1.setUsername("Ines");
        studente1.setEmail("ilopez@unica.it");
        studente1.setPassword("adelante");
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
        studente3.setPassword("spaghettialpesto");
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
        studente4.setPassword("commodore64");
        studente4.setFacolta("Scienze");
        studente4.setCorsoStudi("Informatica");
        studente4.aggiungiCorso(factoryCorsi.cercaCorso("Algoritmi e Strutture Dati 1"));
        studente4.aggiungiCorso(factoryCorsi.cercaCorso("Programmazione 2"));
        studente4.aggiungiCorso(factoryCorsi.cercaCorso("Sistemi Operativi 1"));
        studente4.aggiungiCorso(factoryCorsi.cercaCorso("Interazione Uomo-Macchina"));

        Studente studente5 = new Studente();
        studente5.setNome("Umberto");
        studente5.setCognome("Pinna");
        studente5.setSesso("Maschio");
        studente5.setUsername("Umbo");
        studente5.setEmail("umbertoP@gmail.com");
        studente5.setPassword("gengivesanguinanti");
        studente5.setFacolta("Scienze");
        studente5.setCorsoStudi("Informatica");
        studente5.aggiungiCorso(factoryCorsi.cercaCorso("Programmazione 2"));
        studente5.aggiungiCorso(factoryCorsi.cercaCorso("Sistemi Operativi 1"));

        Studente studente6 = new Studente();
        studente5.setNome("Martina");
        studente5.setCognome("Puddu");
        studente5.setSesso("Femmina");
        studente5.setUsername("Mpuddu");
        studente5.setEmail("mpuddu@gmail.com");
        studente5.setPassword("errata");
        studente5.setFacolta("Scienze");
        studente5.setCorsoStudi("Informatica");
        studente5.aggiungiCorso(factoryCorsi.cercaCorso("Programmazione 1"));
        studente5.aggiungiCorso(factoryCorsi.cercaCorso("Interazione Uomo-Macchina"));

        Studente studente7 = new Studente();
        studente5.setNome("Marco");
        studente5.setCognome("Fenu");
        studente5.setSesso("Maschio");
        studente5.setUsername("Marco");
        studente5.setEmail("marcofenu@gmail.com");
        studente5.setPassword("fibraottica");
        studente5.setFacolta("Scienze");
        studente5.setCorsoStudi("Informatica");
        studente5.aggiungiCorso(factoryCorsi.cercaCorso("Programmazione 1"));
        studente5.aggiungiCorso(factoryCorsi.cercaCorso("Interazione Uomo-Macchina"));
        studente5.aggiungiCorso(factoryCorsi.cercaCorso("Fondamenti di Programmazione Web"));

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
        factoryCorsi.cercaCorso("Storia Medievale").setProfessore(professore1);

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
        factoryCorsi.cercaCorso("Anatomia").setProfessore(professore3);

        Professore spano = new Professore();
        spano.setNome("Lucio Davide");
        spano.setCognome("Spano");
        spano.setSesso("Maschio");
        spano.setUsername("Davide");
        spano.setEmail("davide.spano@unica.it");
        spano.setPassword("Winterfell");
        spano.setFacolta("Scienze");
        spano.setCorsoStudi("Informatica");
        spano.setAvatar(R.drawable.avatar_spano);
        spano.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Interazione Uomo-Macchina"));
        factoryCorsi.cercaCorso("Interazione Uomo-Macchina").setProfessore(spano);
        spano.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Fondamenti di Programmazione Web"));
        factoryCorsi.cercaCorso("Fondamenti di Programmazione Web").setProfessore(spano);

        Professore botta = new Professore();
        botta.setNome("Iunio Fabio");
        botta.setCognome("Botta");
        botta.setSesso("Maschio");
        botta.setUsername("IFB");
        botta.setEmail("botta@unica.it");
        botta.setPassword("spread");
        botta.setFacolta("Scienze");
        botta.setCorsoStudi("Informatica");
        botta.setAvatar(R.drawable.avatar_placeholder);
        botta.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Elementi di Economia e Diritto per Informatici"));
        factoryCorsi.cercaCorso("Elementi di Economia e Diritto per Informatici").setProfessore(botta);

        Professore bartoletti = new Professore();
        bartoletti.setNome("Massimo");
        bartoletti.setCognome("Bartoletti");
        bartoletti.setSesso("Maschio");
        bartoletti.setUsername("Bart");
        bartoletti.setEmail("bart@unica.it");
        bartoletti.setPassword("statifiniti");
        bartoletti.setFacolta("Scienze");
        bartoletti.setCorsoStudi("Informatica");
        bartoletti.setAvatar(R.drawable.avatar_bart);
        bartoletti.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Automi e Linguaggi Formali"));
        factoryCorsi.cercaCorso("Automi e Linguaggi Formali").setProfessore(bartoletti);

        Professore zuddas = new Professore();
        zuddas.setNome("Fabio");
        zuddas.setCognome("Zuddas");
        zuddas.setSesso("Maschio");
        zuddas.setUsername("Fab");
        zuddas.setEmail("fabio.zuddas@unica.it");
        zuddas.setPassword("crittografia");
        zuddas.setFacolta("Scienze");
        zuddas.setCorsoStudi("Informatica");
        zuddas.setAvatar(R.drawable.avatar_placeholder);
        zuddas.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Matematica Discreta"));
        factoryCorsi.cercaCorso("Matematica Discreta").setProfessore(zuddas);

        Professore scateni = new Professore();
        scateni.setNome("Riccardo");
        scateni.setCognome("Scateni");
        scateni.setSesso("Maschio");
        scateni.setUsername("Scat");
        scateni.setEmail("ricscat@unica.it");
        scateni.setPassword("fiorentina");
        scateni.setFacolta("Scienze");
        scateni.setCorsoStudi("Informatica");
        scateni.setAvatar(R.drawable.avatar_scateni);
        scateni.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Programmazione 1"));
        factoryCorsi.cercaCorso("Programmazione 1").setProfessore(scateni);
        scateni.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Videogame Design"));
        factoryCorsi.cercaCorso("Videogame Design").setProfessore(scateni);

        Professore manca = new Professore();
        manca.setNome("Giulia");
        manca.setCognome("Manca");
        manca.setSesso("Femmina");
        manca.setUsername("Giulia");
        manca.setEmail("g.manca@unica.it");
        manca.setPassword("lhadroncollider");
        manca.setFacolta("Scienze");
        manca.setCorsoStudi("Informatica");
        manca.setAvatar(R.drawable.avatar_placeholder);
        manca.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Fisica e Metodo Scientifico"));
        factoryCorsi.cercaCorso("Fisica e Metodo Scientifico").setProfessore(manca);

        Professore atzori = new Professore();
        atzori.setNome("Maurizio");
        atzori.setCognome("Atzori");
        atzori.setSesso("Maschio");
        atzori.setUsername("Mau");
        atzori.setEmail("m.atzori@unica.it");
        atzori.setPassword("oggetti");
        atzori.setFacolta("Scienze");
        atzori.setCorsoStudi("Informatica");
        atzori.setAvatar(R.drawable.avatar_placeholder);
        atzori.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Programmazione 2"));
        factoryCorsi.cercaCorso("Programmazione 2").setProfessore(atzori);

        Professore carta = new Professore();
        carta.setNome("Salvatore");
        carta.setCognome("Carta");
        carta.setSesso("Maschio");
        carta.setUsername("Carta");
        carta.setEmail("salvatore@unica.it");
        carta.setPassword("oggetti");
        carta.setFacolta("Scienze");
        carta.setCorsoStudi("Informatica");
        carta.setAvatar(R.drawable.avatar_placeholder);
        carta.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Sistemi Operativi 1"));
        factoryCorsi.cercaCorso("Sistemi Operativi 1").setProfessore(carta);

        Professore franceschissimo = new Professore();
        franceschissimo.setNome("Massimo");
        franceschissimo.setCognome("Di Francesco");
        franceschissimo.setSesso("Maschio");
        franceschissimo.setUsername("DiFra");
        franceschissimo.setEmail("mdf@unica.it");
        franceschissimo.setPassword("chiquadro");
        franceschissimo.setFacolta("Scienze");
        franceschissimo.setCorsoStudi("Informatica");
        franceschissimo.setAvatar(R.drawable.avatar_placeholder);
        franceschissimo.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Dati e Modelli"));
        factoryCorsi.cercaCorso("Dati e Modelli").setProfessore(franceschissimo);

        Professore cecilia = new Professore();
        cecilia.setNome("Cecilia");
        cecilia.setCognome("Di Ruberto");
        cecilia.setSesso("Femmina");
        cecilia.setUsername("Cecilia");
        cecilia.setEmail("dirubert@unica.it");
        cecilia.setPassword("quicksort");
        cecilia.setFacolta("Scienze");
        cecilia.setCorsoStudi("Informatica");
        cecilia.setAvatar(R.drawable.avatar_placeholder);
        cecilia.aggiungiCorsoGestito(factoryCorsi.cercaCorso("Algoritmi e Strutture Dati 1"));
        factoryCorsi.cercaCorso("Algoritmi e Strutture Dati 1").setProfessore(cecilia);

        listaUtenti.add(studente1);
        listaStudenti.add(studente1);
        listaUtenti.add(studente2);
        listaStudenti.add(studente2);
        listaUtenti.add(studente3);
        listaStudenti.add(studente3);
        listaUtenti.add(studente4);
        listaStudenti.add(studente4);
        listaUtenti.add(studente5);
        listaStudenti.add(studente5);
        listaUtenti.add(studente6);
        listaStudenti.add(studente6);
        listaUtenti.add(studente7);
        listaStudenti.add(studente7);
        listaUtenti.add(professore1);
        listaProfessori.add(professore1);
        listaUtenti.add(professore2);
        listaProfessori.add(professore2);
        listaUtenti.add(professore3);
        listaProfessori.add(professore3);
        listaProfessori.add(spano);
        listaUtenti.add(spano);
        listaProfessori.add(botta);
        listaUtenti.add(botta);
        listaProfessori.add(bartoletti);
        listaUtenti.add(bartoletti);
        listaProfessori.add(zuddas);
        listaUtenti.add(zuddas);
        listaProfessori.add(scateni);
        listaUtenti.add(scateni);
        listaProfessori.add(manca);
        listaUtenti.add(manca);
        listaProfessori.add(atzori);
        listaUtenti.add(atzori);
        listaProfessori.add(carta);
        listaUtenti.add(carta);
        listaProfessori.add(cecilia);
        listaUtenti.add(cecilia);
        listaProfessori.add(franceschissimo);
        listaUtenti.add(franceschissimo);

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

    public Professore cercaProfessore (String cognome)  {
        for (Professore professore : listaProfessori)
            if (cognome.equals(professore.getCognome()))
                return professore;
        return null;
    }

    public ArrayList<Utente> aggiungiUtente (Utente u){
        this.listaUtenti.add(u);
        return listaUtenti;
    }
}
