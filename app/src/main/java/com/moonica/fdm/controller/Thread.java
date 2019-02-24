package com.moonica.fdm.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Commento;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryCommenti;
import com.moonica.fdm.model.FactoryCorsi;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.FactoryUtente;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.Studente;
import com.moonica.fdm.model.ThreadRVAdapter;
import com.moonica.fdm.model.Utente;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class Thread extends AppCompatActivity {

    NestedScrollView ns;

    LinearLayout allegati;

    CardView cv;
    ForumThread ft;
    static Utente utente;
    TextView titolo, testo, data, autore, noReply;
    ImageButton reply;
    FloatingActionButton fab;

    RecyclerView rv;

    ArrayList<Commento> commentsList = new ArrayList<>();
    FactoryCommenti fc = FactoryCommenti.getInstance();


    static final String UTENTE  = "utente";
    public static final String CORSO = "com.moonica.fdm";
    public static final String NEWTHREAD = "com.moonica.fdm";
    private DrawerLayout drawerLayout;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        /*
         * Viene impostato il tasto per tornare alla activity precedente
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*
         * Vengono presi i dati inviati dall'activity precedente
         */
        final Intent i = getIntent();
        Serializable obj = i.getSerializableExtra("com.moonica.fdm");
        Serializable studObj = i.getSerializableExtra("utente");

        ft = (ForumThread) obj;
        utente = (Utente) studObj;

        /*
         * Viene impostato il nome della pagina
         */
        setTitle(ft.getCorso().getNome() + " / Thread");

        commentsList = fc.cercaListaCommenti(ft.getId());

        /*
         * I dati del post principale del thread vengono caricati
         */
        ns = findViewById(R.id.scrollView_thread);
        allegati = findViewById(R.id.allegatiMainPost);
        cv = findViewById(R.id.cardView_main_post);
        titolo = findViewById(R.id.titoloThread_main_post);
        testo = findViewById(R.id.testoThread_main_post);
        data = findViewById(R.id.data_thread);
        autore = findViewById(R.id.autore_thread);
        noReply = findViewById(R.id.noReply);
        reply = findViewById(R.id.rispostaThreadCard);


        titolo.setText(ft.getTitolo());
        testo.setText(ft.getTesto());
        data.setText(ft.getData().getTime().toGMTString());
        autore.setText(ft.getAutore().getNome() + " " + ft.getAutore().getCognome());
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent longReply = new Intent(Thread.this, NewThread.class);
                longReply.putExtra("longReply", "risposta");
                longReply.putExtra(NEWTHREAD, ft);
                longReply.putExtra("utente", utente);
                startActivityForResult(longReply, 1);
            }
        });

        if (ft.getAllegatiPresenza()) {
            allegati.setVisibility(View.VISIBLE);

            for (int j = 0; j < ft.getIconaAllegati().size(); j++) {


                LinearLayout sectionLayout = new LinearLayout(this);
                ImageView imageView = new ImageView(this);
                final TextView textView = new TextView(this);

                LinearLayout.LayoutParams llParamas = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                llParamas.setMargins(0, 25,0,0);
                sectionLayout.setLayoutParams(llParamas);
                sectionLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(50, 50);
                paramsImage.gravity = Gravity.CENTER_VERTICAL;
                imageView.setImageResource(ft.getIconaAllegati().get(j));
                imageView.setLayoutParams(paramsImage);


                final LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                /*textParams.weight = 1.0f;*/
                textParams.gravity = Gravity.CENTER_VERTICAL;

                textView.setText(ft.getNomeAllegati().get(j));
                textView.setLayoutParams(textParams);
                textView.setTextSize(12f);
                textView.setPadding(10,0,10,0);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String messaggio = "Hai scaricato " + String.valueOf(textView.getText());
                        Toast t = Toast.makeText(v.getContext(), messaggio, Toast.LENGTH_SHORT);
                        t.show();
                    }
                });


                sectionLayout.addView(new Space(this));
                sectionLayout.addView(imageView);
                sectionLayout.addView(textView);
                sectionLayout.addView(new Space(this));

                allegati.addView(sectionLayout);
            }
        }

        rv = (RecyclerView) findViewById(R.id.rv_thread);

        if (ft.getNumRisposte() == 0) {
            noReply.setVisibility(View.VISIBLE);
        } else {
            noReply.setVisibility(View.GONE);
        }

        rv.setFocusable(false);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(false);

        initializeAdapter();


        fab = findViewById(R.id.fab_thread);
        /*
         * Il FAB viene reso cliccabile
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReply(Thread.this);
            }
        });


        /*
         * Se la fine della NestedScrollView coincide alla fine dello schermo, il FAB viene reso trasparente
         * altrmenti viene reso senza nessuna trasparenza
         */

        if (ns != null) {
            ns.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {

                    Rect rectRv = new Rect(rv.getLeft(), rv.getTop(), rv.getRight(), rv.getBottom());
                    Rect rectFab = new Rect(fab.getLeft(), fab.getTop(), fab.getRight(), fab.getBottom());

                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int height = size.y;

                    int cvHeight = cv.getHeight();

                    if (ns.getChildAt(0).getBottom()
                            <= (ns.getHeight() + ns.getScrollY()) && cvHeight > height) {
                        fab.setAlpha(0.25f);
                    } else if (ns.getChildAt(0).getBottom()
                            <= (ns.getHeight() + ns.getScrollY()) && rectFab.intersect(rectRv)) {
                        fab.setAlpha(0.25f);
                    } else if (ns.getChildAt(0).getBottom()
                            > (ns.getHeight() + ns.getScrollY()) || !rectFab.intersect(rectRv)) {
                        fab.setAlpha(0.99f);
                    } else {
                        fab.setAlpha(0.99f);
                    }
                }
            });


        }
        //creazione navbar
        Intent intent = new Intent(Thread.this, Home.class);
        setNavBar(intent);
        //funzione logout
        Intent intentL = new Intent(Thread.this, Login.class);
        logOut(intentL);

    }

    private void initializeAdapter() {
        ThreadRVAdapter adapter = new ThreadRVAdapter(commentsList);
        rv.setAdapter(adapter);

    }

    private void addReply(Context c) {

        final EditText reply = new EditText(c);
        final Button buttonPick = new Button(c);


        final AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Risposta")
                .setMessage("Aggiungi una risposta")
                //.setView(reply)
                .setPositiveButton("Rispondi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(reply.getText());

                        Commento newReply = new Commento();

                        if (ft.getNumRisposte() == 0) {
                            noReply.setVisibility(View.GONE);
                        }

                        FactoryUtente factoryUtente = FactoryUtente.getInstance();

                        newReply.setFt(ft.getId());
                        newReply.setTesto(task);
                        newReply.setData(Calendar.getInstance());
                        newReply.setAutore(factoryUtente.cercaUtente(utente.getUsername()));

                        FactoryCommenti factoryCommenti = FactoryCommenti.getInstance();
                        FactoryForumThread factoryForumThread = FactoryForumThread.getInstance();

                        factoryForumThread.aggiungiNumRisposte(ft.getId());
                        ft.setNumRisposte(factoryCommenti);
                        factoryCommenti.aggiungiCommentoLista(newReply);
                        commentsList.add(newReply);

                        ns.fullScroll(View.FOCUS_DOWN);

                        /*
                         * Quando aggiungo un commento al thread, devo aggiornare anche
                         * il thread stesso per numero di risposte.
                         */


                    }
                })
                .setNegativeButton("Annulla", null)
                .create();

        /*
         * Viene mostrata la tastiera per rispondere al thread
         */


        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        reply.setLayoutParams(lp);
        dialog.setView(reply);
        final AlertDialog dialog1 = dialog;
        dialog.show();

        ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE)
                .setEnabled(false);

        reply.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if edittext is empty
                if (TextUtils.isEmpty(s)) {
                    // Disable ok button
                    ((AlertDialog) dialog1).getButton(
                            AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GRAY);

                } else {
                    // Something into edit text. Enable the button.
                    ((AlertDialog) dialog1).getButton(
                            AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    ((AlertDialog) dialog1).getButton(
                            AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));

                }

            }
        });

        dialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Serializable strEditText = data.getSerializableExtra("nuovo commento");

                ForumThread ft = new ForumThread();

                ft = (ForumThread) strEditText;

                noReply.setVisibility(View.GONE);

                FactoryCommenti commenti = FactoryCommenti.getInstance();

                Commento nuovoCommento = commenti.cercaListaCommenti(ft.getId()).get(commenti.cercaListaCommenti(ft.getId()).size() - 1);
                commentsList.add(nuovoCommento);
                ft.setNumRisposte(commenti);

                initializeAdapter();

                ns.fullScroll(View.FOCUS_DOWN);
            }
        }
    }


    /*
     * L'ovveride chiude l'activity presente in cima allo stack
     */
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            finish();
            Intent intent = new Intent(Thread.this, Forum.class);
            intent.putExtra("com.moonica.fdm", ft.getCorso());
            intent.putExtra("utente", utente);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        return true;
    }

    public void setNavBar(final Intent intent) {
        //menu
        ActionBarDrawerToggle actionBarDrawerToggle;
        NavigationView navigationView;
        //navMenu
        drawerLayout = (DrawerLayout) findViewById(R.id.activityThread);
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

    @Override
    public void onBackPressed() {
        finish();

        Intent intent = new Intent(Thread.this, Forum.class);

        intent.putExtra("com.moonica.fdm", ft.getCorso());

        intent.putExtra("utente", utente);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                        Intent intent = new Intent(Thread.this, Corsi.class);

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
}


