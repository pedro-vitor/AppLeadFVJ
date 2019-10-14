package com.NTI.AppFVJ.Service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Connetion {

    private Context context;

    public Connetion(Context context){
        context = context;
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( cm != null ) {
            NetworkInfo ni = cm.getActiveNetworkInfo();

            return ni != null && ni.isConnected();
        }

        return false;
    }
}
