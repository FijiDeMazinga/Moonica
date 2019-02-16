package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.ForumRVAdapter;


import java.io.Serializable;
import java.util.ArrayList;


public class Forum extends AppCompatActivity {
    Corso c;
    RecyclerView rv;
    ArrayList<ForumThread> listaForum = new ArrayList<>();
    FactoryForumThread fft = FactoryForumThread.getInstance();


    public static final String NEWTHREAD = "com.moonica.fdm";


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        /*
         * Viene impostato il tasto per tornare alla activity precedente
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Serializable obj = i.getSerializableExtra("com.moonica.fdm");
        c = (Corso) obj;

        setTitle(c.getNome() + " / Forum");

        /*
         * funzione per prendere i thread relativi ad uno specifico corso
         */
        listaForum = fft.cercaThreadCorso(c);

        rv = (RecyclerView) findViewById(R.id.rv_forum);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.newthread, menu);
        return true;
    }

    private void initializeAdapter() {
        ForumRVAdapter adapter = new ForumRVAdapter(listaForum);
        rv.setAdapter(adapter);



    }

    /*
     * L'ovveride chiude l'activity presente in cima allo stack
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            /*
             * Per qualche motivo alla presione del tasto viene comunque eseguita il default.
             * Controllare.
             */
            case R.id.action_newThread:
                Intent newThread = new Intent(Forum.this, NewThread.class);
                newThread.putExtra(NEWTHREAD, c);
                startActivity(newThread);
                break;
            default:
                finish();
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        initializeAdapter();
    }
}

