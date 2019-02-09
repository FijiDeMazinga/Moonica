package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactorySezioni;
import com.moonica.fdm.model.Sezione;

import java.io.Serializable;
import java.util.ArrayList;

public class Corsi extends AppCompatActivity {
    TextView professore;
    Corso c;
    LinearLayout l;
    ArrayList<Sezione> listaSezioni = new ArrayList<>();
    FactorySezioni factorySezioni = FactorySezioni.getInstance();

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corsi);
        Intent i = getIntent();
        Serializable obj = null;

        obj = i.getSerializableExtra("com.moonica.fdm");

        c = (Corso) obj;
        setTitle(c.getNome());

        professore = findViewById(R.id.professore);
        professore.setText(c.getProfessore().getNome().concat(c.getProfessore().getCognome()));

        l = findViewById(R.id.sezioni);

        for (Sezione sezione : listaSezioni) {
            Button button = new Button(this);
            Space space = new Space(this);

            space.setMinimumHeight(50);
            button.setText(sezione.getNome());
            button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            l.addView(button);
            l.addView(space);
        }
    }
}
