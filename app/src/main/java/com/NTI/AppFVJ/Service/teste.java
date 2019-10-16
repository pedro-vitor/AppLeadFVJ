package com.NTI.AppFVJ.Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

public class teste {

    private String  _email;
    private String  _password;
    private Context _context;
    private List<User> userListResult = new ArrayList<>();
    private List<Lead> leadListResult = new ArrayList<>();
    private List<Comment> commentListResult = new ArrayList<>();
    private DataHelper dataHelper;

    public teste(Context context, String email, String password){
        _context = context;
        _email = email;
        _password = password;
        dataHelper = new DataHelper(_context);
    }

    public void run(){
        /*AsyncUpdateWS bb = new AsyncUpdateWS(_email,_password);
        bb.execute();*/
        /*AsyncInsertWS asyncInsertWS = new AsyncInsertWS (_email,_password);
        asyncInsertWS.execute();*/
        /*ServiceGets aa = new ServiceGets(_context,_email,_password);
        aa.execute();*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Type listTypeUser = new TypeToken<List<User>>() {}.getType();
                Type listTypeLead = new TypeToken<List<Lead>>() {}.getType();
                Type listTypeComment = new TypeToken<List<Comment>>() {}.getType();


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
                    userListResult = JsonUtil.jsonToListUsers(HttpConnection.POST("user", jsonUser));
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.POST("lead", jsonLead, access_token));
                    commentListResult = JsonUtil.jsonToListComment(HttpConnection.POST("comment", jsonComment, access_token));
                }catch (Exception e){
                    Toast.makeText(_context, "Error: "+e.getMessage(),Toast.LENGTH_LONG);
                }

                if(userListResult != null) {
                    for (User user : userListResult) {
                        dataHelper.updateUsers(user);
                    }
                }

                if(leadListResult.size() > 0) {
                    for (Lead lead : leadListResult) {
                        dataHelper.updateLeads(lead);
                    }
                }

                if(commentListResult != null) {
                    for (Comment comment : commentListResult) {
                        dataHelper.updateComments(comment);
                    }
                }
            }
        });
        thread.start();
    }


    private class AsyncInsertWS extends AsyncTask<Void, Void, Void> {

        private List<User> userListResult = new ArrayList<>();
        private List<Lead> leadListResult = new ArrayList<>();
        private List<Comment> commentListResult = new ArrayList<>();
        private DataHelper dataHelper = new DataHelper(_context);
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
                userListResult = JsonUtil.jsonToListUsers(HttpConnection.POST("user", jsonUser));
                leadListResult = JsonUtil.jsonToListLeads(HttpConnection.POST("lead", jsonLead, access_token));
                commentListResult = JsonUtil.jsonToListComment(HttpConnection.POST("comment", jsonComment, access_token));
            }catch (Exception e){
                Toast.makeText(_context, "Error: "+e.getMessage(),Toast.LENGTH_LONG);
            }

            if(userListResult != null) {
                for (User user : userListResult) {
                    dataHelper.updateUsers(user);
                }
            }

            if(leadListResult.size() > 0) {
                for (Lead lead : leadListResult) {
                    dataHelper.updateLeads(lead);
                }
            }

            if(commentListResult != null) {
                for (Comment comment : commentListResult) {
                    dataHelper.updateComments(comment);
                }
            }

            return  null;
        }
    }

    private class AsyncUpdateWS extends AsyncTask<Void, Void, Void>{

        private List<User> userListResult = new ArrayList<>();
        private List<Lead> leadListResult = new ArrayList<>();
        private List<Comment> commentListResult = new ArrayList<>();
        private DataHelper dataHelper = new DataHelper(_context);
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
                userListResult = JsonUtil.jsonToListUsers(HttpConnection.PUT("user", jsonUser));
                leadListResult = JsonUtil.jsonToListLeads(HttpConnection.PUT("lead", jsonLead, access_token));
                commentListResult = JsonUtil.jsonToListComment(HttpConnection.PUT("comment", jsonComment,access_token));
            }catch (Exception e){
                Toast.makeText(_context, "Error Update: "+e.getMessage(),Toast.LENGTH_LONG);
            }

            if(userListResult  != null) {
                for (User user : userListResult)
                    dataHelper.updateUsers(user);
            }
            if(leadListResult != null) {
                for (Lead lead : leadListResult)
                    dataHelper.updateLeads(lead);
            }
            if(commentListResult != null) {
                for (Comment comment : commentListResult) {
                    dataHelper.updateComments(comment);
                }
            }
            return  null;
        }
    }
}
