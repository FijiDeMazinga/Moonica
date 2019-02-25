package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.FactoryUtente;
import com.moonica.fdm.model.Utente;

import static com.moonica.fdm.controller.NewThread.hideKeyboard;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login;
    TextView registrati, loginError;
    Utente u = new Utente();
    TextInputLayout user, pass;

    public static final String USER = "com.moonica.fdm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();//nasconde l'actionbar

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registrati = (TextView) findViewById(R.id.registrati);
        login = findViewById(R.id.login);
        loginError = findViewById(R.id.loginError);
        user = findViewById(R.id.textUser);
        pass = findViewById(R.id.textPass);

        //listener per il pulsante di login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()) {
                    if (checkUser()) {//controllo che l'utente esista e che nome utente e password siano inseriti
                        Intent home = new Intent(Login.this, Home.class);
                        home.putExtra(USER, u);//passo l'utente alla home
                        startActivity(home);
                        finish();
                    } else
                        //se l'utente non esiste o i dati sono sbagliati viene reso visibile il messaggio di errore
                        loginError.setVisibility(View.VISIBLE);
                }
            }
        });

        //listener per il pulsante di registrazione
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrazione = new Intent(Login.this, Registrazione.class);
                startActivity(registrazione);
            }
        });
    }
    //funzione che stampa messaggi di errore se non vengono riempiti i campi
    public boolean check(){
        int errors = 0;
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_error);

        if(username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire username");
            user.startAnimation(animation);
            errors++;
        }
        else
            username.setError(null);
        if(password.getText() == null || password.getText().length() == 0){
            password.setError("Inserire password");
            pass.startAnimation(animation);
            errors++;
        }
        else
            password.setError(null);
        if(errors == 0) {
            return true;
        }


        hideKeyboard(this);
        return false;
    }

    //funzione che controlla se esista un utente con username e password passati
    public boolean checkUser(){
        FactoryUtente uf = FactoryUtente.getInstance();
        String user, p;
        user = username.getText().toString();
        p = password.getText().toString();
        if (uf.cercaUtente(user, p) != null) {
            u = uf.cercaUtente(user, p);
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
