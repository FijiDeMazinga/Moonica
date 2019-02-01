package com.moonica.fdm;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
    Professore p;
    Studente s;
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
        welcome.setText("Benvenuto " + username + "!");
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
}
