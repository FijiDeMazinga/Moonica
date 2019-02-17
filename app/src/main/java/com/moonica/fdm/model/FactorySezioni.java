package com.moonica.fdm.model;

import com.moonica.fdm.R;

import java.util.ArrayList;

public class FactorySezioni {
    private static FactorySezioni instance;
    private ArrayList<Sezione> listaSezioni = new ArrayList<>();

    private FactorySezioni() {
        Sezione infoIUM = new Sezione();
        infoIUM.setTitolo("Interazione Uomo-Macchina");
        infoIUM.setSottoTitolo("Informazioni generali sul corso");
        infoIUM.setCorso("Interazione Uomo-Macchina");
        infoIUM.getContenuti().add(new Contenuto(R.drawable.icon_text,"Descrizione del corso"));
        infoIUM.getContenuti().add(new Contenuto(R.drawable.icon_pdf,"Calendario delle lezioni"));
        listaSezioni.add(infoIUM);

        Sezione introIUM = new Sezione();
        introIUM.setTitolo("Introduzione");
        introIUM.setSottoTitolo("Presentazione del docente, regole del corso ecc.");
        introIUM.setCorso("Interazione Uomo-Macchina");
        introIUM.getContenuti().add(new Contenuto(R.drawable.icon_pdf,"Slides.pdf"));
        listaSezioni.add(introIUM);

        Sezione compIUM  = new Sezione();
        compIUM.setTitolo("Il computer");
        compIUM.setSottoTitolo("Breve storia dell'HCI e descrizione del computer dal punto di vista dell'interazione");
        compIUM.setCorso("Interazione Uomo-Macchina");
        compIUM.getContenuti().add(new Contenuto(R.drawable.icon_pdf,"Slides.pdf"));
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
            if (sezione.getTitolo().equals(nome))
                return sezione;

        return null;
    }
}
