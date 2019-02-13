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

import de.hdodenhof.circleimageview.CircleImageView;

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

        String[] nome = cList.get(i).getAutore().getNome().split(" ");
        String[] cognome = cList.get(i).getAutore().getCognome().split(" ");


        StringBuilder piuNomi = new StringBuilder();
        StringBuilder piuCognomi = new StringBuilder();

        if (nome.length == 1 && cognome.length ==1)
            replyHolder.nomeAutoreCommento.setText(nome[0] + "\n" + cognome[0]);
        else if (nome.length > 1 || cognome.length >1){

            for (int j=0; j < nome.length; j++) {
                piuNomi.append(nome[j]);
                piuNomi.append("\n");
            }
            for (int j=0; j<cognome.length; j++) {
                piuCognomi.append(cognome[j]);
                if (j < (cognome.length)-1)
                    piuCognomi.append("\n");
            }

            replyHolder.nomeAutoreCommento.setText(piuNomi.toString() + piuCognomi.toString());
        }
        replyHolder.testo.setText(cList.get(i).getTesto());
        replyHolder.data.setText(cList.get(i).getData().getTime().toGMTString());
        replyHolder.avatar.setImageResource(cList.get(i).getAutore().getAvatar());
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
        CircleImageView avatar;

        public ReplyHolder(@NonNull View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cardView_thread);
            nomeAutoreCommento = (TextView)itemView.findViewById(R.id.nomeAutoreCommento);
            testo = (TextView)itemView.findViewById(R.id.testoRisposta);
            data = (TextView)itemView.findViewById(R.id.dataRisposta);
            avatar = (CircleImageView)itemView.findViewById(R.id.avatarUser_thread);
        }

    }

    ArrayList<Commento> cList;

    public ThreadRVAdapter (ArrayList<Commento> cList){
        this.cList = cList;
    }
}
