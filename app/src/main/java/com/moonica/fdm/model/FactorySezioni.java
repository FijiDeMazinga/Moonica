package com.moonica.fdm.model;

import com.moonica.fdm.R;

import java.util.ArrayList;

public class FactorySezioni {
    private static FactorySezioni instance;
    private ArrayList<Sezione> listaSezioni = new ArrayList<>();
    private ArrayList<Sezione> sezioniIUM = new ArrayList<>();
    private ArrayList<Sezione> sezioniFPW = new ArrayList<>();
    private ArrayList<Sezione> sezioniEDI = new ArrayList<>();
    private ArrayList<Sezione> sezioniALF = new ArrayList<>();
    private ArrayList<Sezione> sezioniMD = new ArrayList<>();
    private ArrayList<Sezione> sezioniVGD = new ArrayList<>();
    private ArrayList<Sezione> sezioniFMS = new ArrayList<>();
    private ArrayList<Sezione> sezioniPR1 = new ArrayList<>();
    private ArrayList<Sezione> sezioniPR2 = new ArrayList<>();
    private ArrayList<Sezione> sezioniDM = new ArrayList<>();
    private ArrayList<Sezione> sezioniSO1 = new ArrayList<>();
    private ArrayList<Sezione> sezioniASD = new ArrayList<>();
    private ArrayList<Sezione> sezioniSM = new ArrayList<>();
    //private FactoryCorsi factoryCorsi = FactoryCorsi.getInstance();

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

        Sezione placeholder1 = new Sezione();
        placeholder1.setTitolo("");
        placeholder1.setCorso("");
        placeholder1.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder1);
        sezioniFPW.add(placeholder1);

        Sezione placeholder2 = new Sezione();
        placeholder2.setTitolo("");
        placeholder2.setCorso("");
        placeholder2.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder2);
        sezioniFPW.add(placeholder2);

        Sezione placeholder3 = new Sezione();
        placeholder3.setTitolo("");
        placeholder3.setCorso("");
        placeholder3.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder3);
        sezioniFPW.add(placeholder3);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);
        /*
        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

        Sezione placeholder = new Sezione();
        placeholder.setTitolo("");
        placeholder.setCorso("");
        placeholder.getContenuti().add(new Contenuto(R.drawable.icon_pdf, "Slides.pdf"));
        listaSezioni.add(placeholder);

*/


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
        switch (corso) {
            case ("Interazione Uomo-Macchina"):
                return sezioniIUM;
            case ("Storia Medievale"):
                return sezioniSM;
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
