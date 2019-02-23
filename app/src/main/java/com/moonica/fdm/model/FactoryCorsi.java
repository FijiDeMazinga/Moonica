package com.moonica.fdm.model;

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
        fisio.setFacolta("Medicina e Chirurgia");
        fisio.setNome("Fisiologia");
        fisio.setSigla("FIS");
        fisio.setSezioni(null);

        Corso anatPat = new Corso();
        anatPat.setFacolta("Medicina e Chirurgia");
        anatPat.setNome("Anatomia Patologica");
        anatPat.setSigla("AP");
        anatPat.setSezioni(null);

        Corso oncologia = new Corso();
        oncologia.setFacolta("Medicina e Chirurgia");
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
        asd1.setNome("Algoritmi e Strutture Dati 1");
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

        Corso dm = new Corso();
        dm.setFacolta("Informatica");
        dm.setNome("Dati e Modelli");
        dm.setSigla("DM");

        Corso vgd = new Corso();
        vgd.setFacolta("Informatica");
        vgd.setNome("Videogame Design");
        vgd.setSigla("VGD");

        Corso fms = new Corso();
        fms.setFacolta("Informatica");
        fms.setNome("Fisica e Metodo Scientifico");
        fms.setSigla("FMS");

        Corso md = new Corso();
        md.setFacolta("Informatica");
        md.setNome("Matematica Discreta");
        md.setSigla("MD");

        Corso alf = new Corso();
        alf.setFacolta("Informatica");
        alf.setNome("Automi e Linguaggi Formali");
        alf.setSigla("ALF");

        Corso edi = new Corso();
        edi.setFacolta("Informatica");
        edi.setNome("Elementi di Economia e Diritto per Informatici");
        edi.setSigla("EDI");

        Corso fpw = new Corso();
        fpw.setFacolta("Informatica");
        fpw.setNome("Fondamenti di Programmazione Web");
        fpw.setSigla("FPW");

        corsiInf.add(fpw);
        corsiInf.add(edi);
        corsiInf.add(alf);
        corsiInf.add(md);
        corsiInf.add(vgd);
        corsiInf.add(fms);
        corsiInf.add(ium);
        corsiInf.add(pr1);
        corsiInf.add(pr2);
        corsiInf.add(so1);
        corsiInf.add(asd1);
        corsiInf.add(dm);

        tuttiCorsi.add(edi);
        tuttiCorsi.add(fpw);
        tuttiCorsi.add(alf);
        tuttiCorsi.add(md);
        tuttiCorsi.add(fms);
        tuttiCorsi.add(vgd);
        tuttiCorsi.add(dm);
        tuttiCorsi.add(ium);
        tuttiCorsi.add(pr1);
        tuttiCorsi.add(pr2);
        tuttiCorsi.add(so1);
        tuttiCorsi.add(asd1);
    }

    //restituisce tutti i corsi di una specifica facoltà
    public ArrayList<Corso> listaCorsiFacolta(String nomeFacolta){
        ArrayList<Corso> listaCorsi = new ArrayList<Corso>();

        switch (nomeFacolta){
            case "Informatica":
                listaCorsi.addAll(corsiInf);
                break;
            case "Chimica e Tecnologie Farmaceutiche":
                listaCorsi.addAll(ctf);
                break;
            case "Medicina e Chirurgia":
                listaCorsi.addAll(med);
                break;
            case "Scienze Politiche":
                listaCorsi.addAll(scienzePol);
                break;
            case "Ingegneria Biomedica":
                listaCorsi.addAll(corsiIng);
                break;
            case "Lettere":
                listaCorsi.addAll(corsiLettere);
                break;
        }
        return listaCorsi;
    }
    /*
     * Ricerca di un corso mediante nome del corso
     */
    public Corso cercaCorso(String nome){
        if(nome == null)
            return null;
        for(Corso c : tuttiCorsi)
            if(nome.equals(c.getNome()))
                return c;
        return null;
    }
    public Corso cercaCorsoSigla(String sigla){
        if(sigla == null)
            return null;
        for(Corso c : tuttiCorsi)
            if(sigla.equals(c.getSigla()))
                return c;
        return null;
    }

    /*
     * Ricerca di un corso mediante codice unico e nome del corso
     */
    public Corso cercaCorso(String codice, String nome){
        if (nome == null || codice == null)
            return null;
        for (Corso c : tuttiCorsi)
            if(nome.equals(c.getNome()) && codice.equals(c.getSigla()))
                return c;
            return null;
    }
    public boolean cercaPreferito(Corso c, ArrayList<Corso> preferiti){
        for(Corso corso : preferiti){
            if(c.equals(corso))
                return true;
        }
        return false;
    }
}
