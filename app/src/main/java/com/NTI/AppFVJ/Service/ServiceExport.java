package com.NTI.AppFVJ.Service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ServiceExport extends Service{
    private final Handler handler = new Handler();
    private String  _email;
    private String  _password;
    private SharedPreferences sharedpreferences;
    private Connetion _connetion;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedpreferences = getSharedPreferences("user_preference", MODE_PRIVATE);

        _email = sharedpreferences.getString("email","");
        _password = sharedpreferences.getString("senha","");
        _connetion = new Connetion(ServiceExport.this);
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
                            new ServiceGets(ServiceExport.this, _email, _password).execute();
                        }
                    });
                }
            };
            timer.schedule(timerTask, 1 * 60 * 1000, 1000);
    }

    private class AsyncInsertWS extends AsyncTask<Void, Void, Void>{

        private List<User> userListResult = new ArrayList<>();
        private List<Lead> leadListResult = new ArrayList<>();
        private List<Comment> commentListResult = new ArrayList<>();
        private DataHelper dataHelper = new DataHelper(ServiceExport.this);
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

            String query = "username=" + _email + "&password=" + _password + "&grant_type=password";
            String result = HttpConnection.SETDATAS("token", "POST", query);
            String access_token = JsonUtil.jsonValue(result, "access_token");

            List<User> userList = dataHelper.GetByCreatedUsers();
            List<Lead> leadList = dataHelper.GetByCreatedLeads();
            List<Comment> commentList = dataHelper.GetByCreatedComments();

            Gson gson = new Gson();
            String jsonUser = gson.toJson(userList, listTypeUser);
            String jsonLead = gson.toJson(leadList, listTypeLead);
            String jsonComment = gson.toJson(commentList, listTypeComment);

            if(_connetion.isConnected()) {
                try {
                    userListResult = JsonUtil.jsonToListUsers(HttpConnection.SETDATAS("user", "POST", jsonUser));
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.SETDATAS("lead", jsonLead, "POST", access_token));
                    commentListResult = JsonUtil.jsonToListComment(HttpConnection.SETDATAS("comment", jsonComment, "POST", access_token));
                } catch (Exception e) {
                    Toast.makeText(ServiceExport.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG);
                }

                if (userListResult != null) {
                    for (User user : userListResult) {
                        dataHelper.updateUsers(user);
                    }
                }

                if (leadListResult.size() > 0) {
                    for (Lead lead : leadListResult) {
                        dataHelper.updateLeads(lead);
                    }
                }

                if (commentListResult != null) {
                    for (Comment comment : commentListResult) {
                        dataHelper.updateComments(comment);
                    }
                }
            }
            return  null;
        }
    }

    private class AsyncUpdateWS extends AsyncTask<Void, Void, Void>{

        private List<User> userListResult = new ArrayList<>();
        private List<Lead> leadListResult = new ArrayList<>();
        private List<Comment> commentListResult = new ArrayList<>();
        private DataHelper dataHelper = new DataHelper(ServiceExport.this);
        private String _email;
        private String _password;
        private Type listTypeUser = new TypeToken<List<User>>() {}.getType();
        private Type listTypeLead = new TypeToken<List<Lead>>() {}.getType();
        private Type listTypeComment = new TypeToken<List<Comment>>() {}.getType();

        public AsyncUpdateWS(String email, String password){
            _email = email;
            _password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String query = "username=" + _email + "&password=" + _password + "&grant_type=password";
            String result = HttpConnection.SETDATAS("token","PUT", query);
            String access_token = JsonUtil.jsonValue(result, "access_token");

            List<User> userList = dataHelper.GetByUpdatedUsers();
            List<Lead> leadList = dataHelper.GetByUpdatedLeads();
            List<Comment> commentList = dataHelper.GetByUpdatedComments();

            Gson gson = new Gson();
            String jsonUser = gson.toJson(userList, listTypeUser);
            String jsonLead = gson.toJson(leadList, listTypeLead);
            String jsonComment = gson.toJson(commentList, listTypeComment);

            if(_connetion.isConnected()) {
                try {
                    userListResult = JsonUtil.jsonToListUsers(HttpConnection.SETDATAS("user", "PUT", jsonUser));
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.SETDATAS("lead", jsonLead, "PUT", access_token));
                    commentListResult = JsonUtil.jsonToListComment(HttpConnection.SETDATAS("comment", jsonComment, "PUT", access_token));
                } catch (Exception e) {
                    Toast.makeText(ServiceExport.this, "Error Update: " + e.getMessage(), Toast.LENGTH_LONG);
                }

                if (userListResult != null) {
                    for (User user : userListResult)
                        dataHelper.updateUsers(user);
                }
                if (leadListResult != null) {
                    for (Lead lead : leadListResult)
                        dataHelper.updateLeads(lead);
                }
                if (commentListResult != null) {
                    for (Comment comment : commentListResult) {
                        dataHelper.updateComments(comment);
                    }
                }
            }
            return  null;
        }
    }
}
