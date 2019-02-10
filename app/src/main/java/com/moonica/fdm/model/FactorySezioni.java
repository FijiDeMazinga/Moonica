package com.moonica.fdm.model;

import java.util.ArrayList;

public class FactorySezioni {
    private static FactorySezioni instance;
    private ArrayList<Sezione> listaSezioni = new ArrayList<>();

    private FactorySezioni() {
        Sezione infoIUM = new Sezione();
        infoIUM.setNome("Interazione Uomo-Macchina");
        infoIUM.setCorso("Interazione Uomo-Macchina");
        infoIUM.getContenuti().add("Descrizione del corso");
        infoIUM.getContenuti().add("Calendario delle lezioni");
        listaSezioni.add(infoIUM);

        Sezione introIUM = new Sezione();
        introIUM.setNome("Introduzione");
        introIUM.setCorso("Interazione Uomo-Macchina");
        introIUM.getContenuti().add("Presentazione del docente, regole del corso ecc.");
        introIUM.getContenuti().add("Slides.pdf");
        listaSezioni.add(introIUM);

        Sezione compIUM  = new Sezione();
        compIUM.setNome("Il computer");
        compIUM.setCorso("Interazione Uomo-Macchina");
        compIUM.getContenuti().add("Breve storia dell'HCI e descrizione del computer dal punto di vista dell'interazione");
        compIUM.getContenuti().add("Slides.pdf");
        listaSezioni.add(compIUM);
    }

    public static FactorySezioni getInstance() {
        if (instance == null)
            instance = new FactorySezioni();

        return instance;
    }

    public ArrayList<Sezione> getListaSezioni() {
        return listaSezioni;
    }

    public ArrayList<Sezione> getSezioniCorso(String corso) {
        ArrayList<Sezione> sezioniCorso = new ArrayList<>();

        for (Sezione sezione : listaSezioni) {
            if (sezione.getCorso().equals(corso))
                sezioniCorso.add(sezione);
        }

        return sezioniCorso;
    }

    public Sezione cercaSezione(String nome) {
        for (Sezione sezione : listaSezioni)
            if (sezione.getNome().equals(nome))
                return sezione;

        return null;
    }
}
