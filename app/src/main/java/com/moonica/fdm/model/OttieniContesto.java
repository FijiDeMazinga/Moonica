package com.moonica.fdm.model;

import android.app.Application;
import android.content.Context;

public class OttieniContesto extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        OttieniContesto.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return OttieniContesto.context;
    }
}
