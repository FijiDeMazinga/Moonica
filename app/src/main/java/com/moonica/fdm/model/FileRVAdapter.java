package com.moonica.fdm.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonica.fdm.R;

import java.util.ArrayList;

public class FileRVAdapter extends RecyclerView.Adapter<FileRVAdapter.FileHolder> {
    ArrayList<FileFinto> lista = new ArrayList<>();

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_griglia, viewGroup, false);
        FileHolder fh = new FileHolder(v);
        return fh;
    }

    @Override
    public void onBindViewHolder(@NonNull FileHolder fileHolder, int i) {
        fileHolder.iv.setImageResource(lista.get(i).getImmagineId());
        fileHolder.tv.setText(lista.get(i).getNome());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class FileHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public FileHolder(View itemView){
            super(itemView);
            iv = itemView.findViewById(R.id.ivFile);
            tv = itemView.findViewById(R.id.tvFile);
        }
    }
    public FileRVAdapter(ArrayList<FileFinto> lista){
        this.lista = lista;
    }
}
