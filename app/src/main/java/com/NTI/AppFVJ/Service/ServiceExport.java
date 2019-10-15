package com.NTI.AppFVJ.Service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

public class ServiceExport extends Service {
    private final Handler handler = new Handler();
    private final String _email;
    private final String _password;

    public ServiceExport(String email, String password){
        _email = email;
        _password = password;
    }

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
                        new AsyncInsertWS(_email, _password).execute();
                        new AsyncUpdateWS(_email, _password).execute();
                        new ServiceGets(ServiceExport.this,_email,_password).execute();
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
        private String _email;
        private String _password;

        public AsyncInsertWS(String email, String password){
            _email = email;
            _password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Type listTypeUser = new TypeToken<List<User>>() {}.getType();
            Type listTypeLead = new TypeToken<List<Lead>>() {}.getType();
            Type listTypeComment = new TypeToken<List<Comment>>() {}.getType();

            if(con.isConnected()){

                String query = "username="+_email+"&password="+_password+"&grant_type=password";
                String result = HttpConnection.POST("token", query);
                String access_token = JsonUtil.jsonValue(result, "access_token");

                List<User> userList = dataHelper.GetByCreatedUsers();
                List<Lead> leadList = dataHelper.GetByCreatedLeads();
                List<Comment> commentList = dataHelper.GetByCreatedComments();

                Gson gson = new Gson();
                String jsonUser = gson.toJson(userList, listTypeUser);
                String jsonLead = gson.toJson(leadList, listTypeLead);
                String jsonComment = gson.toJson(commentList, listTypeComment);

                try {
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.POST("lead", jsonLead,access_token));
                    commentListResult = JsonUtil.jsonToListComment(HttpConnection.POST("comment", jsonComment,access_token));
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
        private String _email;
        private String _password;

        public AsyncUpdateWS(String email, String password){
            _email = email;
            _password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Type listTypeUser = new TypeToken<List<User>>() {}.getType();
            Type listTypeLead = new TypeToken<List<Lead>>() {}.getType();
            Type listTypeComment = new TypeToken<List<Comment>>() {}.getType();

            if(con.isConnected()){
                String query = "username="+_email+"&password="+_password+"&grant_type=password";
                String result = HttpConnection.POST("token", query);
                String access_token = JsonUtil.jsonValue(result, "access_token");

                List<User> userList = dataHelper.GetByUpdatedUsers();
                List<Lead> leadList = dataHelper.GetByUpdatedLeads();
                List<Comment> commentList = dataHelper.GetByUpdatedComments();

                Gson gson = new Gson();
                String jsonUser = gson.toJson(userList, listTypeUser);
                String jsonLead = gson.toJson(leadList, listTypeLead);
                String jsonComment = gson.toJson(commentList, listTypeComment);

                try {
                    userListResult = JsonUtil.jsonToListUsers(HttpConnection.POST("user", jsonUser));
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.POST("lead", jsonLead, access_token));
                    commentListResult = JsonUtil.jsonToListComment(HttpConnection.POST("comment", jsonComment,access_token));
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
