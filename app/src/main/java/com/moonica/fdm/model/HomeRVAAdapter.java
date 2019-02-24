package com.moonica.fdm.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.controller.Corsi;
import com.moonica.fdm.controller.Forum;
import com.moonica.fdm.controller.Home;

import java.util.ArrayList;
import java.util.Collections;

import static com.moonica.fdm.controller.Home.CORSO;

public class HomeRVAAdapter extends RecyclerView.Adapter<HomeRVAAdapter.CorsoViewHolder> implements ItemMoveCallback.ItemTouchHelperContract{

    ArrayList<Corso> lista = new ArrayList<Corso>();
    static String UTENTE = "utente";
    FactoryCorsi factoryCorsi = FactoryCorsi.getInstance();
    NavigationView navigationView;
    LinearLayout linearLayout;
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(lista, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(lista, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(CorsoViewHolder myViewHolder) {

    }

    @Override
    public void onRowClear(CorsoViewHolder myViewHolder) {

    }

    public static class CorsoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nomeCorso;
        ImageButton ib, preferito;

        public CorsoViewHolder(@NonNull final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view_corsi);
            nomeCorso = itemView.findViewById(R.id.nomeCorso);
            ib = itemView.findViewById(R.id.ibCorso);
            preferito = itemView.findViewById(R.id.corsoPreferito);
        }
    }
    Studente studente = new Studente();
    Professore professore = new Professore();
    Utente utente = new Utente();
    public HomeRVAAdapter(ArrayList<Corso> lista, Studente s, Utente u, Professore p){
        this.studente = s;
        this.lista = lista;
        this.utente = u;
        this.professore = p;
    }

    @NonNull
    @Override
    public CorsoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_corso, viewGroup, false);
        CorsoViewHolder fth = new CorsoViewHolder(v);
        return fth;
    }


    @Override
    public void onBindViewHolder(@NonNull final CorsoViewHolder corsoViewHolder, final int i) {
        final Context context = corsoViewHolder.itemView.getContext();
        corsoViewHolder.cv.setMinimumHeight(50);
        corsoViewHolder.cv.setRadius(20.1f);
        if(lista.get(i).getNome().length() <= 20)
            corsoViewHolder.nomeCorso.setText("[" + lista.get(i).getSigla() + "] " + lista.get(i).getNome());
        else
            corsoViewHolder.nomeCorso.setText("[" + lista.get(i).getSigla() + "] " + lista.get(i).getNome().substring(0, 20) + "...");
        corsoViewHolder.nomeCorso.setPadding(0, 25,0,0);

        corsoViewHolder.ib.setImageResource(R.drawable.ic_more_vert_black_24dp);

        if (utente instanceof Studente) {
            ArrayList<Corso> preferiti = studente.getCorsiPreferiti();


            corsoViewHolder.preferito.setImageResource(R.drawable.ic_favorite_black_24dp);

            if (factoryCorsi.cercaPreferito(lista.get(i), studente.getCorsiPreferiti()))
                corsoViewHolder.preferito.setColorFilter(Color.RED);

            corsoViewHolder.preferito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (factoryCorsi.cercaPreferito(lista.get(i), studente.getCorsiPreferiti())) {
                        studente.getCorsiPreferiti().remove(lista.get(i));
                        corsoViewHolder.preferito.setColorFilter(0xffeeeeee);
                        //(Home) context).rimuoviPreferito(i);
                        ((Home)context).aggiungiPreferiti(utente);
                    } else {
                        studente.getCorsiPreferiti().add(lista.get(i));
                        corsoViewHolder.preferito.setColorFilter(Color.RED);
                        //((Home) context).aggiungiPreferito(lista.get(i), i);
                        ((Home)context).aggiungiPreferiti(utente);
                    }
                }
            });
        }
        if(utente instanceof Professore)
            corsoViewHolder.preferito.setVisibility(View.GONE);

        corsoViewHolder.ib.setBackgroundColor(0xff225599);
        corsoViewHolder.ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu =  new PopupMenu(corsoViewHolder.itemView.getContext(), corsoViewHolder.ib);
                Menu menu = popupMenu.getMenu();
                if(utente instanceof Studente)
                    menu.add(0,0,0, "Disiscriviti dal corso");
                else if(utente instanceof Professore)
                    menu.add(0,0,0, "Elimina il corso");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case 0:
                                if(utente instanceof Studente) {
                                    studente.getCorsiPreferiti().remove(lista.get(i));
                                    studente.rimuoviCorso(lista.get(i));
                                }
                                else if(utente instanceof Professore)
                                    professore.rimuoviCorsoGestito(lista.get(i));

                                Context context = corsoViewHolder.itemView.getContext();
                                Intent intent = ((Home)context).getIntent();
                                ((Home)context).finish();
                                ((Home)context).startActivity(intent);
                                ((Home)context).overridePendingTransition(0,0);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        corsoViewHolder.cv.setCardBackgroundColor(0xff225599);
        corsoViewHolder.nomeCorso.setTextColor(0xffffffff);
        corsoViewHolder.nomeCorso.setTextSize(18);

        corsoViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(corsoViewHolder.itemView.getContext(), Corsi.class);

                FactoryCorsi fc = FactoryCorsi.getInstance();
                FactorySezioni fs = FactorySezioni.getInstance();
                String name = corsoViewHolder.nomeCorso.getText().toString();

                name = name.substring(name.indexOf("[") + 1);
                name = name.substring(0, name.indexOf("]"));
                Corso corso = fc.cercaCorsoSigla(name);
                //corso.setSezioni(fs.getSezioniCorso(corso.getNome()));
                intent.putExtra(CORSO, corso);
                intent.putExtra(UTENTE, utente);
                corsoViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}