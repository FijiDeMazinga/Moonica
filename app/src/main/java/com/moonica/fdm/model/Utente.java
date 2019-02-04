package com.moonica.fdm.model;


import java.io.Serializable;

public class Utente implements Serializable {

    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;
    private String facolta;
    private Gender sesso;
    CorsoDiStudi corsoStudi;

    public Utente() {
        this.setNome("");
        this.setCognome("");
        this.setUsername("");
        this.setEmail("");
        this.setPassword("");
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

    public CorsoDiStudi getCorsoStudi() {
        return corsoStudi;
    }

    public void setCorsoStudi(String cds) {
        FactoryCorsoDiStudi factoryCorsoDiStudi = FactoryCorsoDiStudi.getInstance();
        this.corsoStudi = factoryCorsoDiStudi.cercaCDS(cds);
    }

    public Gender getSesso() {
        return sesso;
    }

    public void setSesso(Gender sesso) {
        this.sesso = sesso;
    }
}
