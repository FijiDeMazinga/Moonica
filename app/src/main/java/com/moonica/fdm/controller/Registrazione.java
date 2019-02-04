package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Studente;

import java.util.ArrayList;
import java.util.List;

public class Registrazione extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nome, cognome, username, password, mail;
    Spinner gender;

    public static final String USER = "com.moonica.fdm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spinner = findViewById(R.id.gender);

        spinner.setOnItemSelectedListener(this);
        List<String> genders = new ArrayList<String>();
        genders.add("Sesso");
        genders.add("Maschio");
        genders.add("Femmina");
        genders.add("Altro");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, genders);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(dataAdapter);

        final Studente nuovoS = new Studente();
        nome = findViewById(R.id.nome);
        nuovoS.setNome(nome.getText().toString());
        cognome = findViewById(R.id.cognome);
        nuovoS.setCognome(cognome.getText().toString());
        username = findViewById(R.id.username);
        nuovoS.setUsername(username.getText().toString());
        password = findViewById(R.id.password);
        nuovoS.setPassword(password.getText().toString());
        mail = findViewById(R.id.mail);
        nuovoS.setEmail(mail.getText().toString());
        gender = findViewById(R.id.gender);
        nuovoS.setSesso(gender.getSelectedItem().toString());

        Intent registrazione = getIntent();
        Button b = findViewById(R.id.continua);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continua = new Intent(Registrazione.this, SceltaFacolta.class);
                continua.putExtra(USER, nuovoS);
                startActivity(continua);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
