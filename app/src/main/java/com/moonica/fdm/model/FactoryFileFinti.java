package com.moonica.fdm.model;

import com.moonica.fdm.R;

import java.util.ArrayList;

public class FactoryFileFinti {
    private static FactoryFileFinti singleton;

    public static FactoryFileFinti getInstance(){
        if(singleton == null)
            singleton = new FactoryFileFinti();
        return singleton;
    }
    private ArrayList<FileFinto> listaFile = new ArrayList<FileFinto>();
    private ArrayList<FileFinto> listaAvatar = new ArrayList<FileFinto>();

    private FactoryFileFinti(){
        FileFinto f1 = new FileFinto();
        f1.setNome("mauro.png");
        f1.setImmagineId(R.drawable.mauro);
        listaAvatar.add(f1);

        FileFinto f2 = new FileFinto();
        f2.setNome("coky.png");
        f2.setImmagineId(R.drawable.coky);
        listaAvatar.add(f2);

        FileFinto f3 = new FileFinto();
        f3.setNome("lore.png");
        f3.setImmagineId(R.drawable.io);
        listaAvatar.add(f3);

        FileFinto f4 = new FileFinto();
        f4.setNome("a1.png");
        f4.setImmagineId(R.drawable.a1);
        listaAvatar.add(f4);

        FileFinto f5 = new FileFinto();
        f5.setNome("a2.png");
        f5.setImmagineId(R.drawable.a2);
        listaAvatar.add(f5);

        FileFinto f6 = new FileFinto();
        f6.setNome("a3.png");
        f6.setImmagineId(R.drawable.a3);
        listaAvatar.add(f6);

        FileFinto f7 = new FileFinto();
        f7.setNome("a4.png");
        f7.setImmagineId(R.drawable.a4);
        listaAvatar.add(f7);

        FileFinto f8 = new FileFinto();
        f8.setNome("a5.png");
        f8.setImmagineId(R.drawable.a5);
        listaAvatar.add(f8);

        FileFinto f9 = new FileFinto();
        f9.setNome("a6.png");
        f9.setImmagineId(R.drawable.a6);
        listaAvatar.add(f9);

        FileFinto f10 = new FileFinto();
        FileFinto f11 = new FileFinto();
        FileFinto f12 = new FileFinto();
        FileFinto f13 = new FileFinto();
        FileFinto f14 = new FileFinto();
        FileFinto f15 = new FileFinto();
        FileFinto f16 = new FileFinto();
        FileFinto f17 = new FileFinto();
        FileFinto f18 = new FileFinto();
        FileFinto f19 = new FileFinto();
        FileFinto f20 = new FileFinto();
        FileFinto f21 = new FileFinto();
        FileFinto f22 = new FileFinto();
        FileFinto f23 = new FileFinto();
        FileFinto f24 = new FileFinto();
        FileFinto f25 = new FileFinto();
    }
    public ArrayList<FileFinto> restituisciAvatar(){
        ArrayList<FileFinto> fff = new ArrayList<>();
        fff.addAll(listaAvatar);
        return fff;
    }
}
