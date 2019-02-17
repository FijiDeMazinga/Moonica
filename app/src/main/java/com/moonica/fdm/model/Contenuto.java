package com.moonica.fdm.model;

import android.os.Parcelable;

import com.moonica.fdm.R;

public class Contenuto /*extends ExpandableGroup*/ {
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
