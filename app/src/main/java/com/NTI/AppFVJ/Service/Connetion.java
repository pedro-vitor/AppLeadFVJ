package com.NTI.AppFVJ.Service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Connetion {

    private static Context _context;

    public Connetion(Context context){
        _context = context;
    }

    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( cm != null ) {
            NetworkInfo ni = cm.getActiveNetworkInfo();

            return ni != null && ni.isConnected();
        }

        return false;
    }
}
