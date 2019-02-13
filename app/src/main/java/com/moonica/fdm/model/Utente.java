package com.moonica.fdm.model;


import android.media.Image;
import android.widget.ImageView;

import com.moonica.fdm.R;

import java.io.Serializable;

public class Utente implements Serializable {

    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;
    private String facolta;
    private Gender sesso;
    private int avatarId;
    CorsoDiStudi corsoStudi;

    public Utente() {
        this.setNome("");
        this.setCognome("");
        this.setUsername("");
        this.setEmail("");
        this.setPassword("");
        this.setFacolta("");
        this.setSesso("Non specificato");
        this.setAvatar(R.drawable.placeholder);
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

    public void setSesso(String sesso) {
        switch (sesso){
            case "Maschio":
                this.sesso = Gender.MASCHIO;
                break;
            case "Femmina":
                this.sesso = Gender.FEMMINA;
                break;
            case "Non specificato":
                this.sesso = Gender.NONSPECIFICATO;
                break;
        }
    }

    public int getAvatar() {
        return avatarId;
    }

    public void setAvatar(int avatarId) {
        this.avatarId = avatarId;
    }
}
