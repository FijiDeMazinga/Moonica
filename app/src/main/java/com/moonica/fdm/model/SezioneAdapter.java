package com.moonica.fdm.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.controller.Forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.moonica.fdm.controller.Corsi.FORUM;

public class SezioneAdapter extends RecyclerView.Adapter<SezioneAdapter.SezioneViewHolder> {
    int position;
    int mExpandedPosition = -1;

    ArrayList<Sezione> lista = new ArrayList<Sezione>();

    public static class SezioneViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView titoloSezione;
        CardView figlio;

        public SezioneViewHolder (@NonNull final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view_sezioni);
            titoloSezione = itemView.findViewById(R.id.titolo_sezione);
            //figlio = new ContenutoViewHolder()
        }
    }

    public static class ContenutoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView icona;
        TextView testo;

        public ContenutoViewHolder (@NonNull final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view_contenuti);
            icona = itemView.findViewById(R.id.icona_contenuto);
            testo = itemView.findViewById(R.id.testo_contenuto);
        }
    }

    public SezioneAdapter(ArrayList<Sezione> lista, Utente utente){
        this.lista = lista;
    }

    @Override
    public SezioneViewHolder onCreateViewHolder(ViewGroup group, int i){
        View v = LayoutInflater.from(group.getContext()).inflate(R.layout.activity_item_sezione, group, false);
        SezioneViewHolder svh = new SezioneViewHolder(v);
        /*int intMaxNoOfChild = 0;
        for (int index = 0; index < lista.size(); index++) {
            int intMaxSizeTemp = lista.get(index).getContenuti().size();
            if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
        }*/
        return svh;
    }

    //aggiungere onCreateViewHolder per ContenutoViewHolder

    @Override
    public void onBindViewHolder(@NonNull final SezioneViewHolder sezioneViewHolder, final int i){
        sezioneViewHolder.cv.setMinimumHeight(50);
        sezioneViewHolder.cv.setRadius(20.f);
        sezioneViewHolder.titoloSezione.setText(lista.get(i).getTitolo());
        sezioneViewHolder.titoloSezione.setPadding(0, 25, 0, 25);
        sezioneViewHolder.cv.setBackgroundColor(0xff225599);
        sezioneViewHolder.titoloSezione.setTextColor(0xffffffff);
        sezioneViewHolder.titoloSezione.setTextSize(18);

        /*
        final boolean isExpanded = position == mExpandedPosition;
        sezioneViewHolder.details.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        sezioneViewHolder.itemView.setActivated(isExpanded);
        sezioneViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                notifyItemChanged(position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

/*
public class SezioneAdapter extends ExpandableRecyclerViewAdapter<SezioneViewHolder, ContenutoViewHolder> {
    public SezioneAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public SezioneViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_sezione, parent, false);
        return new SezioneViewHolder(view);
    }

    @Override
    public ContenutoViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_contenuto, parent, false);
        return new ContenutoViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ContenutoViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Contenuto contenuto = ((Contenuto) group).getItems().get(childIndex);
        holder.setTesto(group);
    }

    @Override
    public void onBindGroupViewHolder(SezioneViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitolo(group);
    }
}
*/