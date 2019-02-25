package com.moonica.fdm.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class ForumThread implements Serializable {

    private int id;
    private String titolo;
    private String testo;
    private Calendar data = Calendar.getInstance(TimeZone.getDefault());
    private Utente autore;
    private int numRisposte;
    private Corso corso;
    private boolean allegatiPresenza;
    private ArrayList<String> nomeAllegati = new ArrayList<>();
    private ArrayList<Integer> iconaAllegati = new ArrayList<>();

    public ForumThread(){
        this.setId(-1);
        this.setTitolo("");
        this.setTesto("");
        this.setData(1990,1,1, 00, 00, 00);

        this.setAutore("");
        this.setNumRisposte(0);
        this.setCorso(null, null);

        this.allegatiPresenza = false;
        this.setNomeAllegati(new ArrayList<String>());
        this.setIconaAllegati(new ArrayList<Integer>());
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Calendar getData() {


        return data;
    }

    public void setData(int y, int m, int d, int h, int min, int s){
        this.data.set(y, m, d, h, min, s);
    }

    public void setData(Calendar c){
        c.add(Calendar.HOUR_OF_DAY, 1);
        this.data = c;
    }


    public int getNumRisposte() {
        return numRisposte;
    }

    public void setNumRisposte(int numRisposte) {
        this.numRisposte = numRisposte;
    }

    public void setNumRisposte(FactoryCommenti factoryCommenti){
        this.numRisposte = factoryCommenti.numRisposteThread(this.getId())+1;
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(String nickAutore) {
       FactoryUtente factoryUtente = FactoryUtente.getInstance();
       this.autore = factoryUtente.cercaUtente(nickAutore);
    }

    public void setAutore(Utente u){
        this.autore = u;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(String codice, String nomeCorso) {
        FactoryCorsi fc = FactoryCorsi.getInstance();
        this.corso = fc.cercaCorso(codice,nomeCorso);
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAllegatiPresenza() {
        return allegatiPresenza;
    }

    public void setAllegatiPresenza(boolean allegatiPresenza) {
        this.allegatiPresenza = allegatiPresenza;
    }

    public ArrayList<String> getNomeAllegati() {
        return nomeAllegati;
    }

    public void setNomeAllegati(ArrayList<String> nomeAllegati) {
        this.nomeAllegati = nomeAllegati;
    }
    public void setNomeAllegati(String nomeAllegati) {
        this.nomeAllegati.add(nomeAllegati);
    }

    public void eliminaNomeAllegati(String allegato){
        for (String s : nomeAllegati){
            if (s.equals(allegato))
                nomeAllegati.remove(s);
        }
    }

    public ArrayList<Integer> getIconaAllegati() {
        return iconaAllegati;
    }

    public void setIconaAllegati(ArrayList<Integer> iconaAllegati) {
        this.iconaAllegati = iconaAllegati;
    }
    public void setIconaAllegati(Integer iconaAllegati) {
        this.iconaAllegati.add(iconaAllegati);
    }

    public void eliminaIconaAllegati(Integer icolaAllegati){
        for (Integer i : iconaAllegati)
            if (i.equals(icolaAllegati)) {
                iconaAllegati.remove(i);
            }
    }
}
