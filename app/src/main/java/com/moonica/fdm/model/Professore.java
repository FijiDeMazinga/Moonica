package com.moonica.fdm.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Professore extends Utente  implements Serializable {
    private ArrayList<CorsoDiStudi> cdsDiInsegnamento;
    private ArrayList<Corso> corsiGestiti = new ArrayList<Corso>();

    public Professore() {
        super();
    }

    public ArrayList<CorsoDiStudi> getCdsDiInsegnamento() {
        return cdsDiInsegnamento;
    }

    public void setCdsDiInsegnamento(ArrayList<CorsoDiStudi> cdsDiInsegnamento) {
        this.cdsDiInsegnamento = cdsDiInsegnamento;
    }

    public ArrayList<Corso> getCorsiGestiti() {
        return corsiGestiti;
    }

    public void setCorsiGestiti(ArrayList<Corso> corsiGestiti) {
        this.corsiGestiti = corsiGestiti;
    }

    public void aggiungiCdsDiInsegnamento(CorsoDiStudi cdsDaAggiungere) {
        this.cdsDiInsegnamento.add(cdsDaAggiungere);
    }

    public void rimuoviCdsDiInsegnamento(CorsoDiStudi cdsDaRimuovere) {
        for (CorsoDiStudi cds : cdsDiInsegnamento) {
            if (cds.getNome().equals(cdsDaRimuovere.getNome())) {
                cdsDiInsegnamento.remove(cds);
                break;
            }
        }
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
