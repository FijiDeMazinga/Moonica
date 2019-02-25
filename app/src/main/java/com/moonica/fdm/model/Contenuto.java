package com.moonica.fdm.model;


import java.io.Serializable;

public class Contenuto implements Serializable {
    private int idIcona;
    private String testo;

    public  Contenuto(int icona, String testo) {
        //super(icona, testo);
        this.setIcona(icona);
        this.setTesto(testo);
    }

    public int getIdIcona() {
        return idIcona;
    }

    public void setIcona(int idIcona) {
        this.idIcona = idIcona;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
