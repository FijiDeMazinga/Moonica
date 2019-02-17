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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryCorsi;
import com.moonica.fdm.model.HomeRVAAdapter;
import com.moonica.fdm.model.ItemMoveCallback;
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
    //LinearLayout l;
    RecyclerView rv;
    ArrayList<Corso> lista = new ArrayList<Corso>();
    FactoryCorsi fc = FactoryCorsi.getInstance();
    Dialog scelta;

    public static final String CORSO = "com.moonica.fdm";

    @SuppressLint({"ResourceType", "WrongViewCast"})
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("I tuoi corsi");//titolo che comparirà nell'actionbar
        Intent i = getIntent();
        Serializable obj = null;
        scelta = new Dialog(this);//inizializzazione Dialog per il pop-up

        //prelievo attributo tramite l'intent
        obj= i.getSerializableExtra("com.moonica.fdm");
        u = (Utente) obj;

        //cambio il messaggio di benvenuto in base al sesso dell'utente
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
        //in base al tipo di utente cambio il metodo per ottenere la lista dei corsi
        if(obj instanceof Studente){
            s = (Studente) obj;
            username = s.getUsername();
            lista = s.getCorsi();
        }
        else{
            p = (Professore) obj;
            username = p.getUsername();
            lista = p.getCorsiGestiti();
            ImageButton plus = (ImageButton) findViewById(R.id.plus);
            plus.setVisibility(View.INVISIBLE);//se l'utente è un professore rimuovo il pulsante per aggiungere i corsi
        }
        //settaggio del messaggio di benvenuto
        welcome = findViewById(R.id.welcome);
        welcome.setText(benvenuto.concat(username).concat("!"));

        LinearLayoutManager llm = new LinearLayoutManager(this);


        rv = findViewById(R.id.rvCorso);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        HomeRVAAdapter hra = new HomeRVAAdapter(lista, s);

        ItemTouchHelper.Callback callback = new ItemMoveCallback(hra);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rv);

        rv.setAdapter(hra);
    }
    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    //funzione che parte alla pressione del pulsante di aggiunta di un corso
    public void ShowPopup(View v){
        LayoutInflater f = getLayoutInflater();
        //questa sarà la vista che verrà mostrata nel caso di pressione del pulsante
        View view =  f.inflate(R.layout.activity_aggiungi_corso, null);//richiama l'activity_aggiungi_corso

        //dichiarazione gridlayout(da sistemare graficamente)
        final GridLayout gridLayout = (GridLayout) view.findViewById(R.id.griglia);

        //se il corso di studi dell'utente esiste
        if(s.getCorsoStudi() != null) {
            //prendo i corsi della facoltà a cui è iscritto l'utente
            ArrayList<Corso> listaNuovi = fc.listaCorsiFacolta(s.getCorsoStudi().getNome());
            //rimuovo quelli a cui è già iscritto
            listaNuovi.removeAll(s.getCorsi());
            FactoryCorsi fc = FactoryCorsi.getInstance();

            //per ogni corso aggiungo un bottone
            for (final Corso c : listaNuovi) {
                final Button b = new Button(this);
                //settaggio parametri bottone
                b.setText(c.getSigla());
                b.setBackgroundColor(0xffeeeeee);
                b.setTextColor(0xff225599);
                //dichiarazione di cosa succede cliccando il bottone
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FactoryCorsi fc = FactoryCorsi.getInstance();
                        s.aggiungiCorso(fc.cercaCorso(c.getNome()));//viene aggiunto il corso corrispondente
                        scelta.dismiss();//viene chiuso il pop-up
                        //viene refreshata l'activity home
                        finish();
                        startActivity(getIntent());
                    }
                });
                gridLayout.addView(b);//aggiunta dei bottoni al layout
            }
            scelta.setContentView(view);//setting della view da visualizzare col pop-up
            scelta.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            scelta.show();//lancio del pop-up
        }
        //se il corso di studi non esiste visualizzo un messaggio di errore tramite Toast
        else {
            Toast t = Toast.makeText(this, "Non ci sono corsi disponibili per la tua facoltà", Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
