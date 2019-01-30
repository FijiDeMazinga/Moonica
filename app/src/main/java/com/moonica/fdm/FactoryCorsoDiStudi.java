package com.moonica.fdm;

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
        lettere.getNome("Lettere");
        lettere.getFacolta("Studi Umanistici");
        lettere.listaCorsi.add(null);

        CorsoDiStudi ingBioMed = new CorsoDiStudi();
        ingBioMed.getNome("Ingegneria Biomedica");
        ingBioMed.getFacolta("Ingegneria e Architettura");
        ingBioMed.listaCorsi.add(null);

        CorsoDiStudi
    }
}
