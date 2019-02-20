package com.moonica.fdm.model;

import java.util.ArrayList;

public class FactoryCorsoDiStudi {
    private static FactoryCorsoDiStudi singleton;

    public static FactoryCorsoDiStudi getInstance(){
        if(singleton == null)
            singleton = new FactoryCorsoDiStudi();
        return singleton;
    }

    private ArrayList<CorsoDiStudi> listaCorsiStudi = new ArrayList<CorsoDiStudi>();

    private FactoryCorsoDiStudi(){
        CorsoDiStudi lettere = new CorsoDiStudi();
        lettere.setNome("Lettere");
        lettere.setFacolta("Studi Umanistici");

        CorsoDiStudi ingBioMed = new CorsoDiStudi();
        ingBioMed.setNome("Ingegneria Biomedica");
        ingBioMed.setFacolta("Ingegneria e Architettura");

        CorsoDiStudi scienzePol = new CorsoDiStudi();
        scienzePol.setNome("Scienze Politiche");
        scienzePol.setFacolta("Scienze Economiche, Giuridiche e Politiche");

        CorsoDiStudi medicina = new CorsoDiStudi();
        medicina.setNome("Medicina e Chirurgia");
        medicina.setFacolta("Medicina e Chirurgia");

        CorsoDiStudi ctf = new CorsoDiStudi();
        ctf.setNome("Chimica e Tecnologie Farmaceutiche");
        ctf.setFacolta("Biologia e Farmacia");

        CorsoDiStudi informatica = new CorsoDiStudi();
        informatica.setNome("Informatica");
        informatica.setFacolta("Scienze");

        listaCorsiStudi.add(informatica);
        listaCorsiStudi.add(ctf);
        listaCorsiStudi.add(medicina);
        listaCorsiStudi.add(ingBioMed);
        listaCorsiStudi.add(lettere);
        listaCorsiStudi.add(scienzePol);
    }
    //cerca un corso di studi in base al nome
    public CorsoDiStudi cercaCDS(String nome){
        if(nome == null)
            return null;
        for(CorsoDiStudi cds : listaCorsiStudi)
            if(nome.equals(cds.getNome()))
                return cds;
        return null;
    }
}
