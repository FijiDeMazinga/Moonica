package com.moonica.fdm.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.moonica.fdm.R;

public class NewThread extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_thread);

        /*
         * Viene impostato il tasto per tornare alla activity precedente
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
