package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        }



        GridLayoutManager glm = new GridLayoutManager(this, 2);

        rv = findViewById(R.id.rvGriglia);
        rv.setLayoutManager(glm);
        rv.setHasFixedSize(true);
        FileRVAdapter fileRVAdapter = new FileRVAdapter(listaFile);
        rv.setAdapter(fileRVAdapter);

        /*gl.findViewById(R.id.grigliaFile);
        gl.setColumnCount(2);
        gl.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        gl.setRowCount(listaFile.size()/2);
        for(FileFinto fl : listaFile){
            LinearLayout ll = new LinearLayout(this);
            ImageView iv = new ImageView(this);
            TextView tv = new TextView(this);

            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            iv.setImageResource(fl.getImmagineId());
            iv.setLayoutParams(new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.file_width),
                    (int) getResources().getDimension(R.dimen.file_height)));

            tv.setText(fl.getNome());
            tv.setMaxWidth(50);

            ll.addView(iv);
            ll.addView(tv);
            gl.addView(ll);

        }
        setContentView(gl);*/
    }
}
