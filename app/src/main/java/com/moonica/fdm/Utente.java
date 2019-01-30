package com.moonica.fdm;


import java.io.Serializable;
import java.util.ArrayList;

public class Utente implements Serializable {

    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;
    private String facolta;
    private String corsoStudi;
    public ArrayList<String> corsi;
    private boolean isProfessor;

    public Utente() {
        this.setNome("");
        this.setCognome("");
        this.setUsername("");
        this.setEmail("");
        this.setPassword("");
        this.setFacolta(null);
        this.setCorsoStudi(null);
        this.setCorsi(null);
        this.setProfessor(false);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacolta() {
        return facolta;
    }

    public void setFacolta(String facolta) {
        this.facolta = facolta;
    }

    public String getCorsoStudi() {
        return corsoStudi;
    }

    public void setCorsoStudi(String corsoStudi) {
        this.corsoStudi = corsoStudi;
    }

    public ArrayList<String> getCorsi() {
        return corsi;
    }

    public void setCorsi(ArrayList<String> corsi) {
        this.corsi = corsi;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setProfessor(boolean professor) {
        isProfessor = professor;
    }
}
