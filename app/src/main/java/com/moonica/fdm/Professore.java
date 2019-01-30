package com.moonica.fdm;

import java.util.ArrayList;

public class Professore extends Utente {
    private ArrayList<Corso> corsiGestiti;

    public Professore() {
        super();
        this.setCorsiGestiti(null);
    }

    public ArrayList<Corso> getCorsiGestiti() {
        return corsiGestiti;
    }

    public void setCorsiGestiti(ArrayList<Corso> corsiGestiti) {
        this.corsiGestiti = corsiGestiti;
    }

    public void aggiungiCorsoGestito(Corso corsoDaAggiungere) {
        corsiGestiti.add(corsoDaAggiungere);
    }

    public void rimuoviCorsoGestito(Corso corsoDaRimuovere) {
        for (Corso corso : corsiGestiti) {
            if (corso.getNome().equals(corsoDaRimuovere.getNome())) {
                corsiGestiti.remove(corso);
                break;
            }
        }
    }
}
