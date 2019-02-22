package com.moonica.fdm.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonica.fdm.R;

import java.util.ArrayList;

public class EliminaSezioneAdapter extends RecyclerView.Adapter<EliminaSezioneAdapter.EliminaSezioneViewHolder> {
    ArrayList<Sezione> lista = new ArrayList<Sezione>();

    public static class EliminaSezioneViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView titoloSezione;
        ImageView elimina;
        RelativeLayout attivatore;
        LinearLayout vistaContenuti;

        public EliminaSezioneViewHolder(@NonNull final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view_sezioni);
            titoloSezione = itemView.findViewById(R.id.titolo_sezione);
            elimina = itemView.findViewById(R.id.freccia);
            attivatore = itemView.findViewById(R.id.attivatore);
            vistaContenuti = itemView.findViewById(R.id.vistaContenuti);
        }
    }

    public EliminaSezioneAdapter(ArrayList<Sezione> lista, Utente utente){
        this.lista = lista;
    }

    @Override
    public EliminaSezioneViewHolder onCreateViewHolder(ViewGroup group, int i){
        View v = LayoutInflater.from(group.getContext()).inflate(R.layout.activity_item_sezione, group, false);
        EliminaSezioneViewHolder svh = new EliminaSezioneViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull final EliminaSezioneViewHolder sezioneViewHolder, final int i){
        sezioneViewHolder.cv.setMinimumHeight(50);
        sezioneViewHolder.cv.setRadius(20.1f);
        sezioneViewHolder.titoloSezione.setText(lista.get(i).getTitolo());
        sezioneViewHolder.titoloSezione.setPadding(0, 25, 0, 25);
        sezioneViewHolder.cv.setCardBackgroundColor(0xff225599);
        sezioneViewHolder.titoloSezione.setTextColor(0xffffffff);
        sezioneViewHolder.titoloSezione.setTextSize(18);
        sezioneViewHolder.elimina.setImageResource(R.drawable.ic_delete_red_24dp);
        sezioneViewHolder.elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
