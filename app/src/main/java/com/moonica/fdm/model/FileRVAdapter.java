package com.moonica.fdm.model;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
    FileFinto oldFile = new FileFinto();
    static int selectedPos = RecyclerView.NO_POSITION;

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_griglia, viewGroup, false);
        FileHolder fh = new FileHolder(v, (Activity) v.getContext());
        return fh;
    }

    @Override
    public void onBindViewHolder(@NonNull final FileHolder fileHolder, int i) {
        fileHolder.iv.setImageResource(lista.get(i).getImmagineId());
        fileHolder.tv.setText(lista.get(i).getNome());
        fileHolder.cv.setSelected(selectedPos == i);

        if(selectedPos==i)
            fileHolder.tv.setBackgroundColor(Color.parseColor("#4B75AC"));
        else
            fileHolder.tv.setBackgroundColor(Color.parseColor("#FFFFFF"));

        fileHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int img = lista.get(fileHolder.getAdapterPosition()).getImmagineId();
                int id = fileHolder.iv.getId();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("resultFilename", fileHolder.tv.getText().toString());

                fileHolder.myActivity.setResult(Activity.RESULT_OK, returnIntent);
                fileHolder.myActivity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {

        return lista.size();
    }

    public static class FileHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        CardView cv;
        private Activity myActivity;

        public FileHolder(View itemView, Activity myActivity) {
            super(itemView);
            iv = itemView.findViewById(R.id.ivFile);
            tv = itemView.findViewById(R.id.tvFile);
            cv = itemView.findViewById(R.id.cvFile);
            this.myActivity = myActivity;

        }
    }
    public FileRVAdapter(ArrayList<FileFinto> lista) {
        this.lista = lista;
    }

}


