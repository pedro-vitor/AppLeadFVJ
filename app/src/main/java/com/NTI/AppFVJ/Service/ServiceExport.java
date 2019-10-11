package com.NTI.AppFVJ.Service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import com.NTI.AppFVJ.Activity.MainActivity;
import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
                        new AsyncUpdateWS().execute();
                    }
                });
            }
        };
        timer.schedule(timerTask, 5 * 60 * 1000, 1000);
    }

    private class AsyncInsertWS extends AsyncTask<Void, Void, Void>{

        private List<User> userListResult = new ArrayList<>();
        private List<Lead> leadListResult = new ArrayList<>();
        private List<Comment> commentListResult = new ArrayList<>();
        private DataHelper dataHelper = new DataHelper(ServiceExport.this);
        private Connetion con = new Connetion(ServiceExport.this);

        @Override
        protected Void doInBackground(Void... voids) {

            Type listTypeUser = new TypeToken<List<User>>() {}.getType();
            Type listTypeLead = new TypeToken<List<Lead>>() {}.getType();
            Type listTypeComment = new TypeToken<List<Comment>>() {}.getType();

            if(con.isConnected()){

                List<User> userList = dataHelper.GetByCreatedUsers();
                List<Lead> leadList = dataHelper.GetByCreatedLeads();
                List<Comment> commentList = dataHelper.GetByCreatedComments();

                Gson gson = new Gson();
                String jsonUser = gson.toJson(userList, listTypeUser);
                String jsonLead = gson.toJson(leadList, listTypeLead);
                String jsonComment = gson.toJson(commentList, listTypeComment);

                try {
                    userListResult = JsonUtil.jsonToListUsers(HttpConnection.POST("user", jsonUser));
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.POST("lead", jsonLead));
                    commentListResult = JsonUtil.jsonToListComment(HttpConnection.POST("comment", jsonComment));
                }catch (Exception e){
                    Toast.makeText(ServiceExport.this, "Error: "+e.getMessage(),Toast.LENGTH_LONG);
                }
            }

            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for (User user : userListResult) {
                dataHelper.updateUsers(user);
            }

            for (Lead lead : leadListResult) {
                dataHelper.updateLeads(lead);
            }

            for (Comment comment : commentListResult) {
                dataHelper.updateComments(comment);
            }
        }
    }

    private class AsyncUpdateWS extends AsyncTask<Void, Void, Void>{

        private List<User> userListResult = new ArrayList<>();
        private List<Lead> leadListResult = new ArrayList<>();
        private List<Comment> commentListResult = new ArrayList<>();
        private DataHelper dataHelper = new DataHelper(ServiceExport.this);
        private Connetion con = new Connetion(ServiceExport.this);

        @Override
        protected Void doInBackground(Void... voids) {

            Type listTypeUser = new TypeToken<List<User>>() {}.getType();
            Type listTypeLead = new TypeToken<List<Lead>>() {}.getType();
            Type listTypeComment = new TypeToken<List<Comment>>() {}.getType();

            if(con.isConnected()){

                List<User> userList = dataHelper.GetByUpdatedUsers();
                List<Lead> leadList = dataHelper.GetByUpdatedLeads();
                List<Comment> commentList = dataHelper.GetByUpdatedComments();

                Gson gson = new Gson();
                String jsonUser = gson.toJson(userList, listTypeUser);
                String jsonLead = gson.toJson(leadList, listTypeLead);
                String jsonComment = gson.toJson(commentList, listTypeComment);

                try {
                    userListResult = JsonUtil.jsonToListUsers(HttpConnection.POST("user", jsonUser));
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.POST("lead", jsonLead));
                    commentListResult = JsonUtil.jsonToListComment(HttpConnection.POST("comment", jsonComment));
                }catch (Exception e){
                    Toast.makeText(ServiceExport.this, "Error Update: "+e.getMessage(),Toast.LENGTH_LONG);
                }
            }

            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for (User user : userListResult) {
                dataHelper.updateUsers(user);
            }

            for (Lead lead : leadListResult) {
                dataHelper.updateLeads(lead);
            }

            for (Comment comment : commentListResult) {
                dataHelper.updateComments(comment);
            }
        }
    }
}
