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
import java.util.Set;

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
        Intent registrazione = getIntent();
        Button b = findViewById(R.id.continua);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trovaView();
                if(check()) {
                    Intent continua = new Intent(Registrazione.this, SceltaFacolta.class);
                    Studente s = creaUtente();
                    continua.putExtra(USER, s);
                    startActivity(continua);
                }
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
    public Studente creaUtente(){
        Studente nuovoS = new Studente();
        nuovoS.setNome(nome.getText().toString());
        nuovoS.setCognome(cognome.getText().toString());
        nuovoS.setUsername(username.getText().toString());
        nuovoS.setPassword(password.getText().toString());
        nuovoS.setEmail(mail.getText().toString());
        nuovoS.setSesso(gender.getSelectedItem().toString());
        return nuovoS;
    }
    public void trovaView(){
        nome = findViewById(R.id.nome);
        cognome = findViewById(R.id.cognome);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        mail = findViewById(R.id.mail);
        gender = findViewById(R.id.gender);

    }
    public boolean check(){
        int errors = 0;
        if(username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire username");
            errors++;
        }
        else  password.setError(null);
        if(password.getText() == null || password.getText().length() == 0){
            password.setError("Inserire password");
            errors++;
        }
        else  password.setError(null);
        if(nome.getText() == null || nome.getText().length() == 0){
            nome.setError("Inserire nome");
            errors++;
        }
        else  nome.setError(null);
        if(cognome.getText() == null || cognome.getText().length() == 0){
            cognome.setError("Inserire cognome");
            errors++;
        }
        else  cognome.setError(null);
        if(mail.getText() == null || mail.getText().length() == 0){
            mail.setError("Inserire mail");
            errors++;
        }
        else  mail.setError(null);
        if(gender.getSelectedItem().toString().equals("Sesso")) {
            SetError("Inserire sesso");
            errors++;
        }

        if(errors == 0)
            return true;
        return false;
    }
    public void SetError(String errorMessage){
        View view = gender.getSelectedView();

        TextView t = (TextView) view;
        TextView error = (TextView) findViewById(R.id.invisibleError);
        if(errorMessage != null){
            t.setError(errorMessage);
            t.requestFocus();
        }
        else{
            t.setError(null);
            error.setError(null);
        }
    }
}
