package com.moonica.fdm.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moonica.fdm.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ForumThreadHolder> {

    @NonNull
    @Override
    public ForumThreadHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_forumthread, viewGroup, false);
        ForumThreadHolder fth = new ForumThreadHolder(v);
        return fth;
    }

    @Override
    public void onBindViewHolder(@NonNull ForumThreadHolder forumThreadHolder, int i) {
        if (ftList.get(i).getTitolo().length() < 43)
            forumThreadHolder.titolo.setText(ftList.get(i).getTitolo());
        else
            forumThreadHolder.titolo.setText(ftList.get(i).getTitolo().substring(0,42) + "...");
        forumThreadHolder.numR.setText(ftList.get(i).getNumRisposte() + " risposte");
        forumThreadHolder.dataAutore.setText(getElapsedDaysText(ftList.get(i).getData(), Calendar.getInstance())
        + " da " + ftList.get(i).getAutore().getNome() + " " + ftList.get(i).getAutore().getCognome());

        ft = ftList.get(i);
    }

    @Override
    public int getItemCount() {
        return ftList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ForumThreadHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView titolo;
        TextView numR;
        TextView dataAutore;
        public static final String THREAD = "com.moonica.fdm";


        public ForumThreadHolder(@NonNull final View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.card_view);
            titolo = (TextView) itemView.findViewById(R.id.titoloThread);
            numR = (TextView) itemView.findViewById(R.id.numRisposte);
            dataAutore = (TextView) itemView.findViewById(R.id.dataAutore);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //itemView.getContext().startActivity(new Intent(itemView.getContext(), com.moonica.fdm.controller.Thread.class));
                    Intent thread = new Intent(itemView.getContext(), com.moonica.fdm.controller.Thread.class);
                    thread.putExtra(THREAD, ft);
                    itemView.getContext().startActivity(thread);
                }
            });
        }
    }

    ArrayList<ForumThread> ftList;
    static ForumThread ft;

    public RVAdapter(ArrayList<ForumThread> ftList){
        this.ftList = ftList;
    }

    public String getElapsedDaysText(Calendar c1, Calendar c2) {
        String elapsedDaysText = null;

        long milliSeconds1 = c1.getTimeInMillis();
        long milliSeconds2 = c2.getTimeInMillis();
        long periodSeconds = (milliSeconds2 - milliSeconds1) / 1000;
        long elapsedDays = periodSeconds / 60 / 60 / 24;
        if (elapsedDays < 1 && periodSeconds < 60)
            elapsedDaysText = "meno di un minuto fa";
        else if (elapsedDays < 1 && periodSeconds < 3600)
            elapsedDaysText = periodSeconds * 60 + " minuti fa";
        else if (elapsedDays < 1 && periodSeconds < 7200)
            elapsedDaysText = (periodSeconds * 3600) + " ora fa";
        else if (elapsedDays < 1 && periodSeconds >= 7200)
            elapsedDaysText = (periodSeconds * 3600) + " ore fa";
        else if (elapsedDays < 2)
            elapsedDaysText = elapsedDays + " giorno fa";
        else if (elapsedDays < 30)
            elapsedDaysText = elapsedDays + " giorni fa";
        else if (elapsedDays < 60)
            elapsedDaysText = elapsedDays / 30 + " mese fa";
        else if (elapsedDays < 365)
            elapsedDaysText = (elapsedDays / 30) + " mesi fa";
        else if (elapsedDays < 730)
            elapsedDaysText = (elapsedDays / 365) + " anno fa";
        else
            elapsedDaysText = (elapsedDays / 365) + " anni fa";

        return elapsedDaysText;
    }
}
