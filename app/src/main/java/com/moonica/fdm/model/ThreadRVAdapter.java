package com.moonica.fdm.model;

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

public class ThreadRVAdapter extends RecyclerView.Adapter<ThreadRVAdapter.ReplyHolder> {


    @NonNull
    @Override
    public ReplyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_thread, viewGroup, false);
        ReplyHolder rh = new ReplyHolder(v);
        return rh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyHolder replyHolder, int i) {

        replyHolder.nomeAutoreCommento.setText(cList.get(i).getAutore().getNome() + "\n" + cList.get(i).getAutore().getCognome());
        replyHolder.testo.setText(cList.get(i).getTesto());
        replyHolder.data.setText(cList.get(i).getData().getTime().toGMTString());
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ReplyHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView nomeAutoreCommento, testo, data;

        public ReplyHolder(@NonNull View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cardView_thread);
            nomeAutoreCommento = (TextView)itemView.findViewById(R.id.nomeAutoreCommento);
            testo = (TextView)itemView.findViewById(R.id.testoRisposta);
            data = (TextView)itemView.findViewById(R.id.dataRisposta);
        }

    }

    ArrayList<Commento> cList;

    public ThreadRVAdapter (ArrayList<Commento> cList){
        this.cList = cList;
    }
}
