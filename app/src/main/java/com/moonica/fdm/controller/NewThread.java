package com.moonica.fdm.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import com.moonica.fdm.R;
import com.moonica.fdm.model.Commento;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryCommenti;
import com.moonica.fdm.model.FactoryCorsi;
import com.moonica.fdm.model.FactoryFileFinti;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.FactoryUtente;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.Professore;
import com.moonica.fdm.model.Studente;
import com.moonica.fdm.model.Utente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewThread extends AppCompatActivity {

    Utente utente;
    Corso c = new Corso();
    ForumThread ft = new ForumThread();


    LinearLayout linearLayout, allegaFile;
    TextView titoloReply;

    EditText titolo, testo;
    ImageButton uploadFile;
    Button invio;

    Bundle extras;

    ForumThread forumThread = new ForumThread();
    Commento commento = new Commento();


    static int numAllegati = 0;
    final static int MAXALLEGATI = 4;


    public static final String FILE = "com.moonica.fdm";
    static final String UTENTE  = "utente";
    public static final String CORSO = "com.moonica.fdm";

    public static final String NEWTHREAD = "com.moonica.fdm";
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_thread);
        /*
         * Viene impostato il tasto per tornare alla activity precedente
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        Serializable obj = i.getSerializableExtra("com.moonica.fdm");
        Serializable studObj = i.getSerializableExtra("utente");
        utente = (Utente) studObj;

        extras = i.getExtras();

        allegaFile = (LinearLayout) findViewById(R.id.allegaFile);
        linearLayout = (LinearLayout) findViewById(R.id.previewAllegato);
        testo = (EditText) findViewById(R.id.testoNewT);
        uploadFile = (ImageButton) findViewById(R.id.caricaAllegato);
        invio = (Button) findViewById(R.id.inviaNT);

        if (extras.getString("newThread") != null) {

            titolo = (EditText) findViewById(R.id.titoloNewT);

            setTitle("Nuovo thread");

            c = (Corso) obj;

            invio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (checkTitolo()) {
                        String titleString = String.valueOf(titolo.getText());
                        String textString = String.valueOf(testo.getText());

                        FactoryUtente factoryUtente = FactoryUtente.getInstance();
                        FactoryForumThread factoryForumThread = FactoryForumThread.getInstance();

                        forumThread.setId(factoryForumThread.ultimoId());
                        forumThread.setTitolo(titleString);
                        forumThread.setTesto(textString);
                        forumThread.setCorso(c.getSigla(), c.getNome());
                        forumThread.setNumRisposte(0);
                        forumThread.setData(Calendar.getInstance());
                        forumThread.setAutore(factoryUtente.cercaUtente(utente.getUsername()));


                        factoryForumThread.aggiungiNuovoThread(forumThread);

                        Intent newThread = new Intent(NewThread.this, Thread.class);
                        newThread.putExtra("nuovoThread", "nuovoThread");
                        newThread.putExtra(NEWTHREAD, forumThread);
                        newThread.putExtra("utente", utente);
                        startActivity(newThread);

                    }
                }
            });

        } else if (extras.getString("longReply") != null) {

            titoloReply = (TextView) findViewById(R.id.titoloNewT);

            setTitle("Nuovo commento");

            ft = (ForumThread) obj;
            titoloReply.setText("Re:" + ft.getTitolo());
            titoloReply.setHintTextColor(Color.GRAY);
            titoloReply.setFocusable(false);
            titoloReply.setClickable(true);
            titoloReply.setTextColor(Color.GRAY);

            invio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkTesto()) {
                        String textString = String.valueOf(testo.getText());

                        commento.setFt(ft.getId());
                        commento.setAutore(utente);
                        commento.setTesto(textString);
                        commento.setData(Calendar.getInstance());

                        FactoryCommenti factoryCommenti = FactoryCommenti.getInstance();
                        FactoryForumThread factoryForumThread = FactoryForumThread.getInstance();

                        factoryForumThread.aggiungiNumRisposte(ft.getId());
                        ft.setNumRisposte(factoryCommenti);
                        factoryCommenti.aggiungiCommentoLista(commento);

                        ArrayList<Commento> arrayList = new ArrayList<>();
                        arrayList.addAll(factoryCommenti.cercaListaCommenti(ft.getId()));

                        numAllegati = 0;

                        Intent intent = new Intent();
                        intent.putExtra("nuovo commento", ft);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
        //creazione navbar
        Intent intent = new Intent(NewThread.this, Home.class);
        setNavBar(intent);
        //funzione logout
        Intent intentL = new Intent(NewThread.this, Login.class);
        logOut(intentL);
    }

    public void UploadFile(View v) {

        Intent intent = new Intent(NewThread.this, CaricaFile.class);
        intent.putExtra(FILE, "nuovoThread");
        startActivityForResult(intent, 1);
    }

    public void EliminaSceltaAllegato(View v, LinearLayout contenitore, String filename, int id) {

        numAllegati--;
        allegaFile.setVisibility(View.VISIBLE);
        if (numAllegati == 0) {
            linearLayout.setVisibility(View.GONE);
        }

        if (extras.getString("newThread") != null) {
            forumThread.eliminaNomeAllegati(filename);
            forumThread.eliminaIconaAllegati(id);

            if (forumThread.getIconaAllegati().size() == 0)
                forumThread.setAllegatiPresenza(false);
        } else if (extras.getString("longReply") != null) {
            commento.eliminaNomeAllegati(filename);
            commento.eliminaIconaAllegati(id);
            if (commento.getIconaAllegati().size() == 0)
                commento.setAllegatiPresenza(false);
        }

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

                    if (extras.getString("newThread") != null) {
                        forumThread.setAllegatiPresenza(true);
                        forumThread.setNomeAllegati(fileName);
                        forumThread.setIconaAllegati(factoryFileFinti.cercaFile(fileName));
                    } else if (extras.getString("longReply") != null) {
                        commento.setAllegatiPresenza(true);
                        commento.setNomeAllegati(fileName);
                        commento.setIconaAllegati(factoryFileFinti.cercaFile(fileName));
                    }

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

    //funzione che stampa messaggi di errore se non vengono riempiti i campi
    public boolean checkTitolo() {
        int errors = 0;
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_error);

        if (titolo.getText() == null || titolo.getText().length() == 0) {
            titolo.setError("Inserire un titolo");
            TextInputLayout til = findViewById(R.id.vibraTitolo);
            til.startAnimation(animation);
            errors++;
            hideKeyboard(this);
        } else
            titolo.setError(null);
        if (errors == 0) {
            return true;
        }
        return false;
    }

    public boolean checkTesto() {
        int errors = 0;
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_error);

        if (testo.getText() == null || testo.getText().length() == 0) {
            testo.setError("Inserire una risposta");
            TextInputLayout til = findViewById(R.id.vibraTitolo);
            til.startAnimation(animation);
            errors++;
            hideKeyboard(this);
        } else
            testo.setError(null);
        if (errors == 0) {
            return true;
        }
        return false;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*
     * L'override chiude l'activity presente in cima allo stack
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            default:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {

                    numAllegati = 0;
                    finish();
                }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        numAllegati = 0;
        finish();
    }

    public void setNavBar(final Intent intent) {
        //menu
        ActionBarDrawerToggle actionBarDrawerToggle;
        NavigationView navigationView;
        //navMenu
        drawerLayout = (DrawerLayout) findViewById(R.id.activityNewThread);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        navigationView = (NavigationView) findViewById(R.id.nv);
        CircleImageView avatar = new CircleImageView(this);
        TextView nomeUtente = new TextView(this);
        View header = navigationView.getHeaderView(0);
        avatar = header.findViewById(R.id.avatar);
        nomeUtente = header.findViewById(R.id.nomeUtente);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homeNav:
                        intent.putExtra("com.moonica.fdm", utente);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });
        avatar.setImageResource(utente.getAvatar());
        nomeUtente.setText(utente.getNome() + " " + utente.getCognome());

        if (utente instanceof Studente) {

            aggiungiPreferiti(utente);
        }
        if (utente instanceof Professore){
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.preferiti).setVisible(false);


            TextView textView = findViewById(R.id.zeroCorsiPreferiti);
            textView.setVisibility(View.GONE);
        }
    }

    public void aggiungiPreferiti(final Utente u) {
        NavigationView navigationView;
        navigationView = (NavigationView) findViewById(R.id.nv);

        LinearLayout linearLayout;
        linearLayout = navigationView.findViewById(R.id.listaPreferiti);

        TextView zeroPreferiti = navigationView.findViewById(R.id.zeroCorsiPreferiti);

        linearLayout.removeAllViews();

        if (u instanceof Studente) {
            int i = 0;
            if (((Studente) u).getCorsiPreferiti().size() == 0) {
                zeroPreferiti.setVisibility(View.VISIBLE);
            } else if (((Studente) u).getCorsiPreferiti().size() > 0)
                zeroPreferiti.setVisibility(View.GONE);

            for (Corso c : ((Studente) u).getCorsiPreferiti()) {

                final TextView textView = new TextView(this);
                textView.setText(c.getNome());
                textView.setPadding(50, 0, 0, 20);
                textView.setId(i + 1);


                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(NewThread.this, Corsi.class);

                        FactoryCorsi fc = FactoryCorsi.getInstance();
                        String name = String.valueOf(textView.getText());
                        String sigla = fc.cercaCorso(name).getSigla();

                        Corso corso = fc.cercaCorsoSigla(sigla);
                        intent.putExtra(CORSO, corso);
                        intent.putExtra(UTENTE, u);
                        startActivity(intent);
                    }
                });

                linearLayout.addView(textView);
                i++;
            }
        }
    }

    public void logOut(final Intent intent) {
        RelativeLayout relativeLayout = findViewById(R.id.buttonLogOut);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }
}
