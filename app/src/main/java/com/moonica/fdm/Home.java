package com.moonica.fdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class Home extends AppCompatActivity {
    TextView welcome;
    Utente u;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("I tuoi corsi");
        Intent i = getIntent();

        Serializable obj = i.getSerializableExtra(Login.USER);
        welcome = findViewById(R.id.welcome);

        u = (Utente) obj;
        username = u.getUsername();
        welcome.setText("Benvenuto " + username + "!");
    }
}
