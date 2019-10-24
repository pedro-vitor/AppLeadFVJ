package com.NTI.AppFVJ.Service;

import android.app.Activity;
import android.content.Context;
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
    private List<User> userListResult = new ArrayList<>();
    private List<Lead> leadListResult = new ArrayList<>();
    private List<Comment> commentListResult = new ArrayList<>();
    private DataHelper dataHelper;
    private String _email;
    private String _password;
    private Context _context;

    public teste(Context context, String email, String password){
        _context = context;
        _email = email;
        _password = password;
        dataHelper = new DataHelper(_context);
    }

    public void run(){
        //new AsyncInsertWS(_email,_password).execute();
        //new AsyncUpdateWS(_email,_password).execute();
        new ServiceGets(_context,_email,_password).execute();
    }


    private class AsyncInsertWS extends AsyncTask<Void, Void, Void> {

        private List<User> userListResult = new ArrayList<>();
        private List<Lead> leadListResult = new ArrayList<>();
        private List<Comment> commentListResult = new ArrayList<>();
        private DataHelper dataHelper = new DataHelper(_context);
        private String _email;
        private String _password;
        private Connetion _connetion;
        private int _AllRight = 0;

        public AsyncInsertWS(String email, String password){
            _email = email;
            _password = password;
            _connetion = new Connetion(_context);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Type listTypeUser = new TypeToken<List<User>>() {
            }.getType();
            Type listTypeLead = new TypeToken<List<Lead>>() {
            }.getType();
            Type listTypeComment = new TypeToken<List<Comment>>() {
            }.getType();

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

            if (_connetion.isConnected()) {
                try {
                    userListResult = JsonUtil.jsonToListUsers(HttpConnection.SETDATAS("user", "POST", jsonUser));
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.SETDATAS("lead", jsonLead, "POST", access_token));
                    commentListResult = JsonUtil.jsonToListComment(HttpConnection.SETDATAS("comment", jsonComment, "POST", access_token));
                } catch (Exception e) {
                    Toast.makeText(_context, "Error: " + e.getMessage(), Toast.LENGTH_LONG);
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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(_context, "All right", Toast.LENGTH_SHORT).show();
        }
    }

    private class AsyncUpdateWS extends AsyncTask<Void, Void, Void>{

        private List<User> userListResult = new ArrayList<>();
        private List<Lead> leadListResult = new ArrayList<>();
        private DataHelper dataHelper;
        private String _email;
        private String _password;
        private Type listTypeUser = new TypeToken<List<User>>() {}.getType();
        private Type listTypeLead = new TypeToken<List<Lead>>() {}.getType();
        private Connetion _connetion;

        public AsyncUpdateWS(String email, String password){
            _email = email;
            _password = password;
            dataHelper = new DataHelper(_context);
            _connetion = new Connetion(_context);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String query = "username=" + _email + "&password=" + _password + "&grant_type=password";
            String result = HttpConnection.SETDATAS("token","PUT", query);
            String access_token = JsonUtil.jsonValue(result, "access_token");

            List<User> userList = dataHelper.GetByUpdatedUsers();
            List<Lead> leadList = dataHelper.GetByUpdatedLeads();

            Gson gson = new Gson();
            String jsonUser = gson.toJson(userList, listTypeUser);
            String jsonLead = gson.toJson(leadList, listTypeLead);

            if(_connetion.isConnected()) {
                try {
                    userListResult = JsonUtil.jsonToListUsers(HttpConnection.SETDATAS("user", "PUT", jsonUser));
                    leadListResult = JsonUtil.jsonToListLeads(HttpConnection.SETDATAS("lead", jsonLead, "PUT", access_token));
                } catch (Exception e) {
                    Toast.makeText(_context, "Error Update: " + e.getMessage(), Toast.LENGTH_LONG);
                }

                if (userListResult != null) {
                    for (User user : userListResult)
                        dataHelper.updateUsers(user);
                }
                if (leadListResult != null) {
                    for (Lead lead : leadListResult)
                        dataHelper.updateLeads(lead);
                }
            }
            return  null;
        }
    }

    public class ServiceGets extends AsyncTask<Void, Void, Void> {

        private Context _context;
        private DataHelper dataHelper;

        private String _email;
        private String _password;
        private Connetion _connetion;

        public ServiceGets(Context context, String email, String password) {
            _context = context;
            dataHelper = new DataHelper(_context);
            _email = email;
            _password = password;
            _connetion = new Connetion(_context);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            if (_connetion.isConnected()) {
                String query = "username=" + _email + "&password=" + _password + "&grant_type=password";
                String result = HttpConnection.SETDATAS("token", "POST", query);
                String access_token = JsonUtil.jsonValue(result, "access_token");

                userListResult = JsonUtil.jsonToListUsers(JsonUtil.jsonValue(HttpConnection.GET("user"),"Result"));
                leadListResult = JsonUtil.jsonToListLeads(JsonUtil.jsonValue(HttpConnection.GET("lead", access_token),"Result"));
                commentListResult = JsonUtil.jsonToListComment(JsonUtil.jsonValue(HttpConnection.GET("comment", access_token),"Result"));

                InsertUsersOnDb(userListResult);
                InsertLeadsOnDb(leadListResult);
                InsertCommentsOnDb(commentListResult);
            }
            return null;
        }

        public void InsertUsersOnDb(List<User> userListResult) {
            boolean status;
            DataHelper dataHelper = new DataHelper(_context);
            List<User> userList = dataHelper.GetAllUsers();

            if (userListResult != null || userListResult.size() != 0) {
                if (userList.size() > 0) {
                    for (User us : userListResult) {
                        status = true;
                        for (User user : userList) {
                            if (us.getExternId() == user.getExternId()) {
                                status = false;
                            }
                        }
                        if (status)
                            dataHelper.insertUsers(us);
                    }
                } else {
                    for (User us : userListResult) {
                        dataHelper.insertUsers(us);
                    }
                }
            }
        }

        public void InsertLeadsOnDb(List<Lead> leadListResult) {
            boolean status;
            DataHelper dataHelper = new DataHelper(_context);
            List<Lead> leadList = dataHelper.GetAllLeads();

            if (leadListResult != null || leadListResult.size() != 0) {
                if (leadList.size() > 0) {
                    for (Lead ld : leadListResult) {
                        status = true;
                        for (Lead lead : leadList) {
                            if (lead.getExternId() == ld.getExternId()) {
                                status = false;
                            }
                        }
                        if (status)
                            dataHelper.insertLeads(ld);
                    }
                } else {
                    for (Lead ld : leadListResult) {
                        dataHelper.insertLeads(ld);
                    }
                }
            }
        }

        public void InsertCommentsOnDb(List<Comment> commentListResult) {
            boolean status = true;
            DataHelper dataHelper = new DataHelper(_context);
            List<Comment> commentList = dataHelper.GetAllComments();

            if (commentListResult != null || commentListResult.size() != 0) {
                if (commentList.size() > 0) {
                    for (Comment cmm : commentListResult) {
                        status = true;
                        for (Comment comment : commentList) {
                            if (comment.getExternId() == cmm.getExternId()) {
                                status = false;
                            }
                        }
                        if (status)
                            dataHelper.insertComments(cmm);
                    }
                } else {
                    for (Comment cmm : commentListResult) {
                        dataHelper.insertComments(cmm);
                    }
                }
            }
        }
    }
}
