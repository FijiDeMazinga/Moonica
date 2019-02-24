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

    private FactorySezioni() {
        Sezione infoIUM = new Sezione();
        infoIUM.setTitolo("Interazione Uomo-Macchina");
        infoIUM.setCorso("Interazione Uomo-Macchina");
        infoIUM.getContenuti().add(new Contenuto(R.drawable.txt_preview,"Descrizione del corso"));
        infoIUM.getContenuti().add(new Contenuto(R.drawable.pdf_preview,"Calendario delle lezioni"));
        listaSezioni.add(infoIUM);
        sezioniIUM.add(infoIUM);

        Sezione introIUM = new Sezione();
        introIUM.setTitolo("Introduzione");
        introIUM.setCorso("Interazione Uomo-Macchina");
        introIUM.getContenuti().add(new Contenuto(R.drawable.pdf_preview,"Slides.pdf"));
        listaSezioni.add(introIUM);
        sezioniIUM.add(introIUM);

        Sezione compIUM  = new Sezione();
        compIUM.setTitolo("Il computer");
        compIUM.setCorso("Interazione Uomo-Macchina");
        compIUM.getContenuti().add(new Contenuto(R.drawable.pdf_preview,"Slides.pdf"));
        listaSezioni.add(compIUM);
        sezioniIUM.add(compIUM);

        Sezione htmlCss = new Sezione();
        htmlCss.setTitolo("HTML e CSS");
        htmlCss.setCorso("Fondamenti di Programmazione Web");
        htmlCss.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(htmlCss);
        sezioniFPW.add(htmlCss);

        Sezione pServerside = new Sezione();
        pServerside.setTitolo("Programmazione serverside");
        pServerside.setCorso("Fondamenti di Programmazione Web");
        pServerside.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(pServerside);
        sezioniFPW.add(pServerside);

        Sezione javascript = new Sezione();
        javascript.setTitolo("Javascript");
        javascript.setCorso("Fondamenti di Programmazione Web");
        javascript.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(javascript);
        sezioniFPW.add(javascript);

        Sezione economia = new Sezione();
        economia.setTitolo("Economia");
        economia.setCorso("Elementi di Economia e Diritto per Informatici");
        economia.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(economia);
        sezioniEDI.add(economia);

        Sezione diritto = new Sezione();
        diritto.setTitolo("Diritto");
        diritto.setCorso("Elementi di Economia e Diritto per Informatici");
        diritto.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(diritto);
        sezioniEDI.add(diritto);

        Sezione parzialiEdi = new Sezione();
        parzialiEdi.setTitolo("Prove parziali");
        parzialiEdi.setCorso("Elementi di Economia e Diritto per Informatici");
        parzialiEdi.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Parziali economia.pdf"));
        parzialiEdi.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Parziali diritto.pdf"));
        listaSezioni.add(parzialiEdi);
        sezioniEDI.add(parzialiEdi);

        Sezione p1ALF = new Sezione();
        p1ALF.setTitolo("Parte 1");
        p1ALF.setCorso("Automi e Linguaggi Formali");
        p1ALF.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p1ALF);
        sezioniALF.add(p1ALF);

        Sezione p2ALF = new Sezione();
        p2ALF.setTitolo("Parte 2");
        p2ALF.setCorso("Automi e Linguaggi Formali");
        p2ALF.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p2ALF);
        sezioniALF.add(p2ALF);

        Sezione p3ALF = new Sezione();
        p3ALF.setTitolo("Parte 3");
        p3ALF.setCorso("Automi e Linguaggi Formali");
        p3ALF.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p3ALF);
        sezioniALF.add(p3ALF);

        Sezione p1MD = new Sezione();
        p1MD.setTitolo("Parte 1");
        p1MD.setCorso("Matematica Discreta");
        p1MD.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p1MD);
        sezioniMD.add(p1MD);

        Sezione p2MD = new Sezione();
        p2MD.setTitolo("Parte 2");
        p2MD.setCorso("Matematica Discreta");
        p2MD.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p2MD);
        sezioniMD.add(p2MD);

        Sezione p3MD = new Sezione();
        p3MD.setTitolo("Parte 3");
        p3MD.setCorso("Matematica Discreta");
        p3MD.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p3MD);
        sezioniMD.add(p3MD);

        Sezione textures = new Sezione();
        textures.setTitolo("Textures");
        textures.setCorso("Videogame Design");
        textures.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(textures);
        sezioniVGD.add(textures);

        Sezione meshes = new Sezione();
        meshes.setTitolo("Meshes");
        meshes.setCorso("Videogame Design");
        meshes.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(meshes);
        sezioniVGD.add(meshes);

        Sezione animazioni = new Sezione();
        animazioni.setTitolo("Animazioni");
        animazioni.setCorso("Videogame Design");
        animazioni.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(animazioni);
        sezioniVGD.add(animazioni);

        Sezione dinECin = new Sezione();
        dinECin.setTitolo("Dinamica e Cinematica");
        dinECin.setCorso("Fisica e Metodo Scientifico");
        dinECin.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(dinECin);
        sezioniFMS.add(dinECin);

        Sezione eleMag = new Sezione();
        eleMag.setTitolo("Elettromagnetismo");
        eleMag.setCorso("Fisica e Metodo Scientifico");
        eleMag.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(eleMag);
        sezioniFMS.add(eleMag);

        Sezione parzialiFMS = new Sezione();
        parzialiFMS.setTitolo("Prove parziali");
        parzialiFMS.setCorso("Fisica e Metodo Scientifico");
        parzialiFMS.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Primo parziale.pdf"));
        parzialiFMS.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Secondo parziale.pdf"));
        listaSezioni.add(parzialiFMS);
        sezioniFMS.add(parzialiFMS);

        Sezione introPR1 = new Sezione();
        introPR1.setTitolo("Introduzione");
        introPR1.setCorso("Programmazione 1");
        introPR1.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(introPR1);
        sezioniPR1.add(introPR1);

        Sezione funzioni = new Sezione();
        funzioni.setTitolo("Funzioni");
        funzioni.setCorso("Programmazione 1");
        funzioni.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(funzioni);
        sezioniPR1.add(funzioni);

        Sezione puntEListe = new Sezione();
        puntEListe.setTitolo("Puntatori e Liste");
        puntEListe.setCorso("Programmazione 1");
        puntEListe.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(puntEListe);
        sezioniPR1.add(puntEListe);

        Sezione introPR2 = new Sezione();
        introPR2.setTitolo("Introduzione alla programmazione ad oggetti");
        introPR2.setCorso("Programmazione 2");
        introPR2.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(introPR2);
        sezioniPR2.add(introPR2);

        Sezione eccezioni = new Sezione();
        eccezioni.setTitolo("Eccezioni");
        eccezioni.setCorso("Programmazione 2");
        eccezioni.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(eccezioni);
        sezioniPR2.add(eccezioni);

        Sezione lab = new Sezione();
        lab.setTitolo("Laboratorio");
        lab.setCorso("Programmazione 2");
        lab.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(lab);
        sezioniPR2.add(lab);

        Sezione scheduling = new Sezione();
        scheduling.setTitolo("Scheduling");
        scheduling.setCorso("Sistemi Operativi 1");
        scheduling.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(scheduling);
        sezioniSO1.add(scheduling);

        Sezione semafori = new Sezione();
        semafori.setTitolo("Semafori");
        semafori.setCorso("Sistemi Operativi 1");
        semafori.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(semafori);
        sezioniSO1.add(semafori);

        Sezione memCent = new Sezione();
        memCent.setTitolo("Memoria centrale");
        memCent.setCorso("Sistemi Operativi 1");
        memCent.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(memCent);
        sezioniSO1.add(memCent);

        Sezione alberi = new Sezione();
        alberi.setTitolo("Alberi");
        alberi.setCorso("Algoritmi e Strutture Dati 1");
        alberi.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(alberi);
        sezioniASD.add(alberi);

        Sezione algOrd = new Sezione();
        algOrd.setTitolo("Algoritmi di ordinamento");
        algOrd.setCorso("Algoritmi e Strutture Dati 1");
        algOrd.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(algOrd);
        sezioniASD.add(algOrd);

        Sezione hashTabs = new Sezione();
        hashTabs.setTitolo("Tavole Hash");
        hashTabs.setCorso("Algoritmi e Strutture Dati 1");
        hashTabs.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(hashTabs);
        sezioniASD.add(hashTabs);

        Sezione p1DM = new Sezione();
        p1DM.setTitolo("Parte 1");
        p1DM.setCorso("Dati e Modelli");
        p1DM.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p1DM);
        sezioniDM.add(p1DM);

        Sezione p2DM = new Sezione();
        p2DM.setTitolo("Parte 2");
        p2DM.setCorso("Dati e Modelli");
        p2DM.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p2DM);
        sezioniDM.add(p2DM);

        Sezione p3DM = new Sezione();
        p3DM.setTitolo("Parte 3");
        p3DM.setCorso("Dati e Modelli");
        p3DM.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(p3DM);
        sezioniDM.add(p3DM);

        Sezione introSM = new Sezione();
        introSM.setTitolo("Intro");
        introSM.setCorso("Storia Medievale");
        introSM.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
        listaSezioni.add(introSM);
        sezioniSM.add(introSM);

        Sezione coseSM = new Sezione();
        coseSM.setTitolo("Cose");
        coseSM.setCorso("Storia Medievale");
        coseSM.getContenuti().add(new Contenuto(R.drawable.pdf_preview, "Slides.pdf"));
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
            case ("Programmazione 1"):
                return sezioniPR1;
            case  ("Programmazione 2"):
                return sezioniPR2;
            case ("Algoritmi e Strutture Dati 1"):
                return sezioniASD;
            case ("Sistemi Operativi 1"):
                return sezioniSO1;
            case ("Dati e Modelli"):
                return  sezioniDM;
            case ("Videogame Design"):
                return sezioniVGD;
            case ("Fisica e Metodo Scientifico"):
                return sezioniFMS;
            case ("Matematica Discreta"):
                return sezioniMD;
            case ("Automi e Linguaggi Formali"):
                return sezioniALF;
            case ("Elementi di Economia e Diritto per Informatici"):
                return sezioniEDI;
            case ("Fondamenti di Programmazione Web"):
                return sezioniFPW;
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
