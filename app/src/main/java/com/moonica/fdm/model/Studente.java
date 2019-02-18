package com.moonica.fdm.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Studente extends Utente implements Serializable {
    private int annoCorso;
    private ArrayList<Corso> corsi = new ArrayList<Corso>();

    public Studente() {
        super();
        this.setAnnoCorso(1);
    }

    public int getAnnoCorso() {
        return annoCorso;
    }

    public void setAnnoCorso(int annoCorso) {
        this.annoCorso = annoCorso;
    }

    public ArrayList<Corso> getCorsi() {
        return corsi;
    }

    public void setCorsi(ArrayList<Corso> corsi) {
        this.corsi = corsi;
    }

    public void setCorsoStudi(CorsoDiStudi corsoStudi) {
        this.corsoStudi = corsoStudi;
    }

    public void aggiungiCorso (Corso corsoDaAggiungere) {  //decidere poi come gestire la possibile aggiunta di un corso a cui si e' gia' iscritti
        corsi.add(corsoDaAggiungere);
    }

    public void rimuoviCorso (Corso corsoDaRimuovere) {
        for (Corso corso : corsi) {
            if (corso.equals(corsoDaRimuovere)) {
                corsi.remove(corso);
                break;
            }
        }
    }
}
