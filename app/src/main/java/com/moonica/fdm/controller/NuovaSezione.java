package com.moonica.fdm.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import com.moonica.fdm.R;
import com.moonica.fdm.model.Contenuto;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryFileFinti;
import com.moonica.fdm.model.FactorySezioni;
import com.moonica.fdm.model.Sezione;
import com.moonica.fdm.model.Utente;
import java.io.Serializable;
import java.util.ArrayList;
import static com.moonica.fdm.controller.NewThread.hideKeyboard;

public class NuovaSezione extends AppCompatActivity {
    Utente utente;
    Corso c;
    Sezione nuovaSezione = new Sezione();

    LinearLayout linearLayout, allegaFile;
    EditText titoloSezione;
    ImageButton caricaFile;
    Button invio;

    static int numAllegati = 0;
    final static int MAXALLEGATI = 4;

    public static final String FILE = "com.moonica.fdm";
    public static final String NUOVASEZIONE = "com.moonica.fdm";

    private FactorySezioni factorySezioni = FactorySezioni.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_sezione);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        Serializable obj = i.getSerializableExtra("nuovaSezione");
        final Serializable utenteObj = i.getSerializableExtra("utente");

        c = (Corso) obj;
        utente = (Utente) utenteObj;

        allegaFile =  findViewById(R.id.vistaCaricaFile);
        linearLayout = findViewById(R.id.anteprimaAllegato);
        titoloSezione = findViewById(R.id.titoloNS);
        caricaFile = findViewById(R.id.caricaFile);
        invio = findViewById(R.id.accettaSezione);

        invio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkTitolo()) {
                    nuovaSezione.setTitolo(String.valueOf(titoloSezione.getText()));
                    nuovaSezione.setCorso(c.getNome());
                    //c.getSezioni().add(nuovaSezione);
                    factorySezioni.getSezioniCorso(c.getNome()).add(nuovaSezione);

                    Intent i = new Intent();
                    i.putExtra("nuova sezione", nuovaSezione);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }

    public void UploadFile(View v) {

        Intent intent = new Intent(NuovaSezione.this, CaricaFile.class);
        intent.putExtra(FILE, "nuovoThread");
        startActivityForResult(intent, 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            default:
                finish();
        }
        return true;
    }


    //funzione che stampa messaggi di errore se non vengono riempiti i campi
    public boolean checkTitolo() {
        int errors = 0;
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_error);

        if (titoloSezione.getText() == null || titoloSezione.getText().length() == 0) {
            titoloSezione.setError("Inserire un titolo");
            TextInputLayout til = findViewById(R.id.vibraTitolo);
            til.startAnimation(animation);
            errors++;
            hideKeyboard(this);
        } else
            titoloSezione.setError(null);
        if (errors == 0) {
            return true;
        }
        return false;
    }

    public void EliminaSceltaAllegato(View v, LinearLayout contenitore, String filename, int id) {
        numAllegati--;
        allegaFile.setVisibility(View.VISIBLE);
        if (numAllegati == 0) {
            linearLayout.setVisibility(View.GONE);
        }

        ArrayList<Contenuto> contenutiDaRimuovere = new ArrayList<>();
        for (Contenuto contenuto : nuovaSezione.getContenuti()) {
            if (contenuto.getIdIcona()  ==  id && contenuto.getTesto().equals(filename))
                contenutiDaRimuovere.add(contenuto);
        }
        nuovaSezione.getContenuti().removeAll(contenutiDaRimuovere);
        contenitore.removeAllViews();
        linearLayout.removeView(contenitore);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (numAllegati < MAXALLEGATI) {
                    final ProgressDialog progress = new ProgressDialog(this);
                    progress.setMessage("Caricamento file");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(true);
                    progress.show();
                    new CountDownTimer(1500, 100) {
                        @Override
                        public void onTick(long millisUntilFinished) {}
                        @Override
                        public void onFinish() {
                            progress.dismiss();
                        }
                    }.start();
                    Serializable fn = data.getSerializableExtra("resultFilename");

                    numAllegati++;

                    final int id = numAllegati;

                    final String fileName = (String) fn;

                    final FactoryFileFinti factoryFileFinti = FactoryFileFinti.getInstance();

                    final LinearLayout sectionLayout = new LinearLayout(this);
                    ImageView imageView = new ImageView(this);
                    TextView textView = new TextView(this);
                    ImageButton imageButton = new ImageButton(this);


                    LinearLayout.LayoutParams llParamas = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    sectionLayout.setLayoutParams(llParamas);
                    sectionLayout.setOrientation(LinearLayout.HORIZONTAL);


                    LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(100, 100);
                    paramsImage.gravity = Gravity.CENTER_VERTICAL;
                    imageView.setImageResource(factoryFileFinti.cercaFile(fileName));
                    imageView.setLayoutParams(paramsImage);


                    LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    textParams.gravity = Gravity.CENTER_VERTICAL;

                    textView.setText(fileName);
                    textView.setTextColor(Color.parseColor("#225599"));
                    textView.setLayoutParams(textParams);
                    textView.setPadding(10, 0, 10, 0);


                    LinearLayout.LayoutParams paramsb = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.buttonDeleteFile), getResources().getDimensionPixelSize(R.dimen.buttonDeleteFile));
                    paramsb.weight = 1.0f;
                    paramsb.gravity = Gravity.CENTER_VERTICAL;

                    imageButton.setImageResource(R.drawable.ic_delete_red_24dp);
                    imageButton.setLayoutParams(paramsb);
                    imageButton.setBackgroundColor(Color.parseColor("#eaeaea"));
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EliminaSceltaAllegato(v, sectionLayout, fileName, factoryFileFinti.cercaFile(fileName));
                        }
                    });

                    nuovaSezione.getContenuti().add(new Contenuto(factoryFileFinti.cercaFile(fileName), fileName));

                    sectionLayout.addView(imageView);
                    sectionLayout.addView(textView);
                    sectionLayout.addView(imageButton);
                    sectionLayout.addView(new Space(this));

                    linearLayout.addView(sectionLayout);
                    linearLayout.setVisibility(View.VISIBLE);

                }

                if (numAllegati == MAXALLEGATI)
                    allegaFile.setVisibility(View.GONE);
            }
        }
    }
}
