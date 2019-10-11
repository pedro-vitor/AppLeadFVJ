package com.NTI.AppFVJ.Service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.telecom.Connection;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

public class ServiceExport extends Service {
    private final Handler handler = new Handler();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startTimer(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        new AsyncInsertWS().execute();
                    }
                });
            }
        };
        timer.schedule(timerTask, 5 * 60 * 1000, 1000);
    }

    private class AsyncInsertWS extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            HttpConnection httpConnection = new HttpConnection();
            DataHelper dataHelper = new DataHelper(ServiceExport.this);
            ConnectionMoble con = new ConnectionMoble(ServiceExport.this);

            return  null;
        }
    }
}
