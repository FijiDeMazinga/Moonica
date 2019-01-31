package com.moonica.fdm;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Space;
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

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
            Button tv = new Button(this);
            Space s = new Space(this);

            s.setMinimumHeight(50);
            tv.setText("[" + c.getSigla() + "] " + c.getNome());
            tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            tv.setPadding(30,0,0,0);
            tv.setTextColor(0xffffffff);
            tv.setBackgroundColor(0xff225599);
            tv.setTextSize(16);

            l.addView(tv);
            l.addView(s);
        }
    }
}
