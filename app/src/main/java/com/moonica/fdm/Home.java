package com.moonica.fdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    TextView welcome;
    Utente u;
    String username;
    LinearLayout l;
    ArrayList<Corso> lista = new ArrayList<Corso>();
    FactoryCorsi fc = FactoryCorsi.getInstance();

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

        l = findViewById(R.id.corsi);
        lista = fc.listaCorsiFacolta(u.getCorsoStudi().getNome());

        for(Corso c : lista){
            Button b = new Button(this);
            b.setText(c.getNome());
            b.setBackgroundColor(225599);
            l.addView(b);
        }
    }
}
