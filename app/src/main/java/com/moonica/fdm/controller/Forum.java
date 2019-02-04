package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.ForumThread;

import java.io.Serializable;
import java.util.ArrayList;


public class Forum extends AppCompatActivity {

    TextView titolo;
    Corso c;
    ArrayList<ForumThread> listaForum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        Intent i = getIntent();

        //Serializable obj = i.getSerializableExtra(Corso.cs);

        //c = (Corso) obj;

        titolo.findViewById(R.id.forumWelcome);
        //titolo.setText("Forum di " + c.getCorso().getNome());

        for (ForumThread ft : listaForum){
            Button bt = new Button(this);
            Space s = new Space(this);

            s.setMinimumHeight(50);

            if(ft.getTitolo().length() <= 20)
                bt.setText(ft.getTitolo());
            else
                bt.setText(ft.getTitolo().substring(0, 20) + "...");

        }
    }
}
