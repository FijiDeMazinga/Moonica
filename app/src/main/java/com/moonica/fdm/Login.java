package com.moonica.fdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login;
    TextView registrati, loginError;
    Utente u = new Utente();

    public static final String USER = "com.moonica.fdm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registrati = (TextView) findViewById(R.id.registrati);
        login = findViewById(R.id.login);
        loginError = findViewById(R.id.loginError);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkUser() && check()) {
                    Intent home = new Intent(Login.this, Home.class);
                    home.putExtra(USER, u);
                    startActivity(home);
                }
                else
                    loginError.setVisibility(View.VISIBLE);
            }
        });

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrazione = new Intent(Login.this, Registrazione.class);
                startActivity(registrazione);
            }
        });
    }
    public boolean check(){
        int errors = 0;

        if(username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire username");
            errors++;
        }
        else
            username.setError(null);
        if(password.getText() == null || password.getText().length() == 0){
            password.setError("Inserire password");
            errors++;
        }
        else
            password.setError(null);
        if(errors == 0) {
            return true;
        }
        return false;
    }

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
}
