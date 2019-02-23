package com.moonica.fdm.model;

import com.moonica.fdm.R;

import java.util.ArrayList;

public class FactorySezioni {
    private static FactorySezioni instance;
    private ArrayList<Sezione> listaSezioni = new ArrayList<>();
    private ArrayList<Sezione> sezioniIUM = new ArrayList<>();
    private ArrayList<Sezione> sezioniSM = new ArrayList<>();

    private FactorySezioni() {
        Sezione infoIUM = new Sezione();
        infoIUM.setTitolo("Interazione Uomo-Macchina");
        //infoIUM.setSottoTitolo("Informazioni generali sul corso");
        infoIUM.setCorso("Interazione Uomo-Macchina");
        infoIUM.getContenuti().add(new Contenuto(R.drawable.icon_text,"Descrizione del corso"));
        infoIUM.getContenuti().add(new Contenuto(R.drawable.icon_pdf,"Calendario delle lezioni"));
        listaSezioni.add(infoIUM);
        sezioniIUM.add(infoIUM);

        Sezione introIUM = new Sezione();
        introIUM.setTitolo("Introduzione");
        //introIUM.setSottoTitolo("Presentazione del docente, regole del corso ecc.");
        introIUM.setCorso("Interazione Uomo-Macchina");
        introIUM.getContenuti().add(new Contenuto(R.drawable.icon_pdf,"Slides.pdf"));
        listaSezioni.add(introIUM);
        sezioniIUM.add(introIUM);

        Sezione compIUM  = new Sezione();
        compIUM.setTitolo("Il computer");
        //compIUM.setSottoTitolo("Breve storia dell'HCI e descrizione del computer dal punto di vista dell'interazione");
        compIUM.setCorso("Interazione Uomo-Macchina");
        compIUM.getContenuti().add(new Contenuto(R.drawable.icon_pdf,"Slides.pdf"));
        listaSezioni.add(compIUM);
        sezioniIUM.add(compIUM);

        Sezione introSM = new Sezione();
        introSM.setTitolo("Intro");
        introSM.setCorso("Storia Medievale");
        introSM.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(introSM);
        sezioniSM.add(introSM);

        Sezione coseSM = new Sezione();
        coseSM.setTitolo("Cose");
        coseSM.setCorso("Storia Medievale");
        coseSM.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(coseSM);
        sezioniSM.add(coseSM);
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
        ArrayList<Sezione> listaSez = new ArrayList<>();
        switch (corso) {
            case ("Interazione Uomo-Macchina"):
                listaSez.addAll(sezioniIUM);
                return listaSez;
            case ("Storia Medievale"):
                listaSez.addAll(sezioniSM);
                return listaSez;
        }

        return null;
    }

    public Sezione cercaSezione(String nome) {
        for (Sezione sezione : listaSezioni)
            if (sezione.getTitolo().equals(nome))
                return sezione;

        return null;
    }
}
