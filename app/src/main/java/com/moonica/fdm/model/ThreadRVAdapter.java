package com.moonica.fdm.model;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;
import com.moonica.fdm.R;
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
    public void onBindViewHolder(@NonNull final ReplyHolder replyHolder, int i) {

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

        int j;
        for (j=0; j<cList.get(replyHolder.getAdapterPosition()).getNomeAllegati().size(); j++) {

            LinearLayout sectionLayout = new LinearLayout(replyHolder.allegatiLayout.getContext());
            ImageView imageView = new ImageView(replyHolder.allegatiLayout.getContext());
            final TextView textView = new TextView(replyHolder.allegatiLayout.getContext());

            LinearLayout.LayoutParams llParamas = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llParamas.setMargins(0,0,0,30);
            sectionLayout.setLayoutParams(llParamas);
            sectionLayout.setOrientation(LinearLayout.HORIZONTAL);


            LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(50, 50);
            paramsImage.gravity = Gravity.CENTER_VERTICAL;
            imageView.setImageResource(cList.get(replyHolder.getAdapterPosition()).getIconaAllegati().get(j));
            imageView.setLayoutParams(paramsImage);


            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textParams.gravity = Gravity.CENTER_VERTICAL;

            textView.setText(cList.get(replyHolder.getAdapterPosition()).getNomeAllegati().get(j));
            textView.setTextColor(Color.parseColor("#225599"));
            textView.setLayoutParams(textParams);
            textView.setTextSize(12f);
            textView.setPadding(10, 0, 10, 0);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast t = Toast.makeText(replyHolder.allegatiLayout.getContext(), "Hai scaricato " + String.valueOf(textView.getText()), Toast.LENGTH_SHORT);
                    t.show();
                }
            });


            sectionLayout.addView(imageView);
            sectionLayout.addView(textView);
            sectionLayout.addView(new Space(replyHolder.allegatiLayout.getContext()));

            replyHolder.allegatiLayout.addView(sectionLayout);
            replyHolder.allegatiLayout.setVisibility(View.VISIBLE);
        }




        replyHolder.testo.setText(cList.get(i).getTesto());
        replyHolder.data.setText(cList.get(i).getData().getTime().toGMTString().substring(0, cList.get(i).getData().getTime().toGMTString().lastIndexOf(" ")));
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
        LinearLayout allegatiLayout;
        CircleImageView avatar;

        public ReplyHolder(@NonNull View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cardView_thread);
            nomeAutoreCommento = (TextView)itemView.findViewById(R.id.nomeAutoreCommento);
            testo = (TextView)itemView.findViewById(R.id.testoRisposta);
            data = (TextView)itemView.findViewById(R.id.dataRisposta);

            allegatiLayout = (LinearLayout)itemView.findViewById(R.id.allegatiCommenti);

            avatar = (CircleImageView)itemView.findViewById(R.id.avatarUser_thread);
        }
    }
    ArrayList<Commento> cList;

    public ThreadRVAdapter (ArrayList<Commento> cList){
        this.cList = cList;
    }
}
