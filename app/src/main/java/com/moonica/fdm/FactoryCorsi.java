package com.moonica.fdm;

import java.util.ArrayList;

public class FactoryCorsi {
    private static FactoryCorsi singleton;

    public static FactoryCorsi getInstance(){
        if(singleton == null)
            singleton = new FactoryCorsi();
        return singleton;
    }
    private ArrayList<Corso> listaCorsi = new ArrayList<Corso>();

    private FactoryCorsi(){
        Corso storiaRomana = new Corso();

    }
}
