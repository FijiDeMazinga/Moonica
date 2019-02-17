package com.moonica.fdm.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.controller.Corsi;
import com.moonica.fdm.controller.Home;

import java.util.ArrayList;
import java.util.Collections;

import static com.moonica.fdm.controller.Home.CORSO;

public class HomeRVAAdapter extends RecyclerView.Adapter<HomeRVAAdapter.CorsoViewHolder> implements ItemMoveCallback.ItemTouchHelperContract{

    ArrayList<Corso> lista = new ArrayList<Corso>();

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
        ImageButton ib;

        public CorsoViewHolder(@NonNull final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view_corsi);
            nomeCorso = itemView.findViewById(R.id.nomeCorso);
            ib = itemView.findViewById(R.id.ibCorso);

        }

    }
    Studente studente = new Studente();
    public HomeRVAAdapter(ArrayList<Corso> lista, Studente s){
        this.studente = s;
        this.lista = lista;
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
        corsoViewHolder.cv.setMinimumHeight(50);
        corsoViewHolder.cv.setRadius(20.1f);
        if(lista.get(i).getNome().length() <= 20)
            corsoViewHolder.nomeCorso.setText("[" + lista.get(i).getSigla() + "] " + lista.get(i).getNome());
        else
            corsoViewHolder.nomeCorso.setText("[" + lista.get(i).getSigla() + "] " + lista.get(i).getNome().substring(0, 20) + "...");
        corsoViewHolder.nomeCorso.setPadding(0, 25,0,0);
        corsoViewHolder.ib.setImageResource(R.drawable.ic_more_vert_black_24dp);
        corsoViewHolder.ib.setBackgroundColor(0xff225599);
        corsoViewHolder.ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu =  new PopupMenu(corsoViewHolder.itemView.getContext(), corsoViewHolder.ib);
                Menu menu = popupMenu.getMenu();
                menu.add(0,0,0, "Elimina corso");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case 0:
                                studente.rimuoviCorso(lista.get(i));
                                Context context = corsoViewHolder.itemView.getContext();
                                Intent intent = ((Home)context).getIntent();
                                ((Home)context).finish();
                                ((Home)context).startActivity(intent);
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
        corsoViewHolder.nomeCorso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(corsoViewHolder.itemView.getContext(), Corsi.class);
                FactoryCorsi fc = FactoryCorsi.getInstance();
                String name = corsoViewHolder.nomeCorso.getText().toString();
                name = name.substring(name.lastIndexOf("] ") + 1);
                name = name.substring(1);
                Corso corso = fc.cercaCorso(name);
                intent.putExtra(CORSO, corso);
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
