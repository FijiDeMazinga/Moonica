package com.moonica.fdm;

import java.util.ArrayList;

public class FactoryCorsi {
    private static FactoryCorsi singleton;

    public static FactoryCorsi getInstance(){
        if(singleton == null)
            singleton = new FactoryCorsi();
        return singleton;
    }

    private ArrayList<Corso> tuttiCorsi = new ArrayList<Corso>();
    private ArrayList<Corso> corsiLettere = new ArrayList<Corso>();
    private ArrayList<Corso> corsiIng = new ArrayList<Corso>();
    private ArrayList<Corso> scienzePol = new ArrayList<Corso>();
    private ArrayList<Corso> med = new ArrayList<Corso>();
    private ArrayList<Corso> ctf = new ArrayList<Corso>();
    private ArrayList<Corso> corsiInf = new ArrayList<Corso>();

    private FactoryCorsi(){
        Corso storiaRomana = new Corso();
        storiaRomana.setFacolta("Lettere");
        storiaRomana.setNome("Storia Romana");
        storiaRomana.setSigla("SR");
        storiaRomana.setSezioni(null);

        Corso letGreca = new Corso();
        letGreca.setFacolta("Lettere");
        letGreca.setNome("Letteratura Greca");
        letGreca.setSigla("LG");
        letGreca.setSezioni(null);

        Corso storiaMed = new Corso();
        storiaMed.setFacolta("Lettere");
        storiaMed.setNome("Storia Medievale");
        storiaMed.setSigla("SM");
        storiaMed.setSezioni(null);

        corsiLettere.add(letGreca);
        corsiLettere.add(storiaMed);
        corsiLettere.add(storiaRomana);

        tuttiCorsi.add(letGreca);
        tuttiCorsi.add(storiaMed);
        tuttiCorsi.add(storiaRomana);

        Corso chimica = new Corso();
        chimica.setFacolta("Ingegneria Biomedica");
        chimica.setNome("Chimica");
        chimica.setSigla("CH");
        chimica.setSezioni(null);

        Corso anatomia = new Corso();
        anatomia.setFacolta("Ingegneria Biomedica");
        anatomia.setNome("Anatomia");
        anatomia.setSigla("AN");
        anatomia.setSezioni(null);

        Corso analisi1 = new Corso();
        analisi1.setFacolta("Ingegneria Biomedica");
        analisi1.setNome("Analisi 1");
        analisi1.setSigla("A1");
        analisi1.setSezioni(null);

        Corso biomateriali = new Corso();
        biomateriali.setFacolta("Ingegneria Biomedica");
        biomateriali.setNome("Biomateriali");
        biomateriali.setSigla("BM");
        biomateriali.setSezioni(null);

        corsiIng.add(analisi1);
        corsiIng.add(anatomia);
        corsiIng.add(chimica);
        corsiIng.add(biomateriali);

        tuttiCorsi.add(analisi1);
        tuttiCorsi.add(anatomia);
        tuttiCorsi.add(chimica);
        tuttiCorsi.add(biomateriali);

        Corso economiaPol = new Corso();
        economiaPol.setFacolta("Scienze Politiche");
        economiaPol.setNome("Economia Politica");
        economiaPol.setSigla("EP");
        economiaPol.setSezioni(null);

        Corso dirPrivato = new Corso();
        dirPrivato.setFacolta("Scienze Politiche");
        dirPrivato.setNome("Diritto Privato");
        dirPrivato.setSigla("DP");
        dirPrivato.setSezioni(null);

        scienzePol.add(economiaPol);
        scienzePol.add(dirPrivato);

        tuttiCorsi.add(economiaPol);
        tuttiCorsi.add(dirPrivato);

        Corso fisio = new Corso();
        fisio.setFacolta("Medicina e chirurgia");
        fisio.setNome("Fisiologia");
        fisio.setSigla("FIS");
        fisio.setSezioni(null);

        Corso anatPat = new Corso();
        anatPat.setFacolta("Medicina e chirurgia");
        anatPat.setNome("Anatomia Patologica");
        anatPat.setSigla("AP");
        anatPat.setSezioni(null);

        Corso oncologia = new Corso();
        oncologia.setFacolta("Medicina e chirurgia");
        oncologia.setNome("Oncologia");
        oncologia.setSigla("ONC");
        oncologia.setSezioni(null);

        med.add(fisio);
        med.add(anatPat);
        med.add(oncologia);

        tuttiCorsi.add(fisio);
        tuttiCorsi.add(anatPat);
        tuttiCorsi.add(oncologia);

        Corso chimicaOrg = new Corso();
        chimicaOrg.setFacolta("Chimica e Tecnologie Farmaceutiche");
        chimicaOrg.setNome("Chimica Organica");
        chimicaOrg.setSigla("CO");
        chimicaOrg.setSezioni(null);

        ctf.add(chimicaOrg);

        tuttiCorsi.add(chimicaOrg);

        Corso pr1 = new Corso();
        pr1.setFacolta("Informatica");
        pr1.setNome("Programmazione 1");
        pr1.setSigla("PR1");
        pr1.setSezioni(null);

        Corso asd1 = new Corso();
        asd1.setFacolta("Informatica");
        asd1.setNome("Algoritmi e strutture dati 1");
        asd1.setSigla("ASD1");
        asd1.setSezioni(null);

        Corso so1 = new Corso();
        so1.setFacolta("Informatica");
        so1.setNome("Sistemi Operativi 1");
        so1.setSigla("SO1");
        so1.setSezioni(null);

        Corso pr2 = new Corso();
        pr2.setFacolta("Informatica");
        pr2.setNome("Programmazione 2");
        pr2.setSigla("PR2");
        pr2.setSezioni(null);

        Corso ium = new Corso();
        ium.setFacolta("Informatica");
        ium.setNome("Interazione Uomo-Macchina");
        ium.setSigla("IUM");
        ium.setSezioni(null);

        corsiInf.add(ium);
        corsiInf.add(pr1);
        corsiInf.add(pr2);
        corsiInf.add(so1);
        corsiInf.add(asd1);

        tuttiCorsi.add(ium);
        tuttiCorsi.add(pr1);
        tuttiCorsi.add(pr2);
        tuttiCorsi.add(so1);
        tuttiCorsi.add(asd1);
    }

    ArrayList<Corso> listaCorsiFacolta(String nomeFacolta){
        ArrayList<Corso> listaCorsi = new ArrayList<Corso>();

        switch (nomeFacolta){
            case "Informatica":
                return corsiInf;
            case "Chimica e Tecnologie Farmaceutiche":
                return ctf;
            case "Medicina e chirurgia":
                return med;
            case "Scienze Politiche":
                return scienzePol;
            case "Ingegneria Biomedica":
                return corsiIng;
            case "Lettere":
                return corsiLettere;
        }
        return null;
    }

    Corso cercaCorso(String nome){
        if(nome == null)
            return null;
        for(Corso c : tuttiCorsi)
            if(nome.equals(c.getNome()))
                return c;
        return null;
    }
}
