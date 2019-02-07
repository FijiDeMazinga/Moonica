package com.moonica.fdm.controller;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryCorsi;
import com.moonica.fdm.model.Professore;
import com.moonica.fdm.model.Studente;
import com.moonica.fdm.model.Utente;

import java.io.Serializable;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    TextView welcome;
    Utente u;
    Professore p;
    Studente s;
    String username, benvenuto;
    LinearLayout l;
    ArrayList<Corso> lista = new ArrayList<Corso>();
    FactoryCorsi fc = FactoryCorsi.getInstance();
    Dialog scelta;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("I tuoi corsi");
        Intent i = getIntent();
        Serializable obj = null;
        scelta = new Dialog(this);

        obj= i.getSerializableExtra("com.moonica.fdm");

        u = (Utente) obj;
        switch (u.getSesso()){
            case FEMMINA:
                benvenuto = "Benvenuta ";
                break;
            case MASCHIO:
                benvenuto = "Benvenuto ";
                break;
            case NONSPECIFICATO:
                benvenuto = "Benvenut* ";
                break;
        }

        if(obj instanceof Studente){
            s = (Studente) obj;
            username = s.getUsername();
            lista = s.getCorsi();
        }
        else{
            p = (Professore) obj;
            username = p.getUsername();
            lista = p.getCorsiGestiti();
        }
        welcome = findViewById(R.id.welcome);
        welcome.setText(benvenuto.concat(username).concat("!"));
        l = findViewById(R.id.corsi);

        for(Corso c : lista){
            Button tv = new Button(this);
            Space s = new Space(this);

            s.setMinimumHeight(50);
            if(c.getNome().length() <= 20)
                tv.setText("[" + c.getSigla() + "] " + c.getNome());
            else
                tv.setText("[" + c.getSigla() + "] " + c.getNome().substring(0, 20) + "...");
            tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            tv.setPadding(30,0,0,0);
            tv.setTextColor(0xffffffff);
            tv.setBackgroundColor(0xff225599);
            tv.setTextSize(16);

            l.addView(tv);
            l.addView(s);
        }
    }
    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void ShowPopup(View v){
        LayoutInflater f = getLayoutInflater();
        View view =  f.inflate(R.layout.activity_aggiungi_corso, null);
        final GridLayout gridLayout = (GridLayout) view.findViewById(R.id.griglia);
        if(s.getCorsoStudi() != null) {
            ArrayList<Corso> listaNuovi = fc.listaCorsiFacolta(s.getCorsoStudi().getNome());
            listaNuovi.removeAll(s.getCorsi());
            FactoryCorsi fc = FactoryCorsi.getInstance();

            for (final Corso c : listaNuovi) {
                final Button b = new Button(this);
                b.setText(c.getSigla());
                b.setBackgroundColor(0xffeeeeee);
                b.setTextColor(0xff225599);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FactoryCorsi fc = FactoryCorsi.getInstance();
                        s.aggiungiCorso(fc.cercaCorso(c.getNome()));
                        scelta.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                });
                gridLayout.addView(b);
            }
            scelta.setContentView(view);
            scelta.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            scelta.show();
        }
        else {
            Toast t = Toast.makeText(this, "Non ci sono corsi disponibili per la tua facoltà", Toast.LENGTH_SHORT);

            t.show();
        }
    }
}
