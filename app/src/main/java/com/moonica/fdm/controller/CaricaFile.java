package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.FactoryFileFinti;
import com.moonica.fdm.model.FileFinto;
import com.moonica.fdm.model.FileRVAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class CaricaFile extends AppCompatActivity {

    FactoryFileFinti fff = FactoryFileFinti.getInstance();
    ArrayList<FileFinto> listaFile = new ArrayList<>();
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carica_file);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        GridLayout gl = new GridLayout(this);
        Serializable obj = intent.getSerializableExtra("com.moonica.fdm");

        String activityArrivo = (String) obj;

        switch (activityArrivo){
            case "registrazione":
                listaFile = fff.restituisciAvatar();
                break;
            case "nuovoThread":
                listaFile =fff.restituisciFile();
                break;
        }

        GridLayoutManager glm = new GridLayoutManager(this, 2);

        rv = findViewById(R.id.rvGriglia);
        rv.setLayoutManager(glm);
        rv.setFocusable(false);
        rv.setHasFixedSize(true);
        FileRVAdapter fileRVAdapter = new FileRVAdapter(listaFile);
        rv.setAdapter(fileRVAdapter);
    }

    /*
     * L'ovveride chiude l'activity presente in cima allo stack
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return true;
    }
}
