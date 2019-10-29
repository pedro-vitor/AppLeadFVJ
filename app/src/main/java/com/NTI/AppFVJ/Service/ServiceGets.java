package com.NTI.AppFVJ.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;

import java.util.ArrayList;
import java.util.List;

public class ServiceGets extends AsyncTask<Void, Void, Void> {

    private Context _context;
    private DataHelper dataHelper;

    private String _email;
    private String _password;
    private Connetion _connetion;

    private static List<User> userListResult = new ArrayList<>();
    private static List<Lead> leadListResult = new ArrayList<>();
    private static List<Comment> commentListResult = new ArrayList<>();

    private List<Integer> deletedUser = new ArrayList<>();
    private List<Integer> deletedLead = new ArrayList<>();
    private List<Integer> deletedComment = new ArrayList<>();

    public ServiceGets(Context context, String email, String password){
        _context = context;
        dataHelper = new DataHelper(_context);
        _email = email;
        _password = password;
        _connetion = new Connetion(_context);
    }


    @Override
    protected Void doInBackground(Void... voids) {
        if(_connetion.isConnected()) {
            String query = "username=" + _email + "&password=" + _password + "&grant_type=password";
            String result = HttpConnection.SETDATAS("token", "POST", query);
            String access_token = JsonUtil.jsonValue(result, "access_token");

            String usersQuery = HttpConnection.GET("user");
            String leadQuery = HttpConnection.GET("lead", access_token);
            String commentQuery = HttpConnection.GET("comment", access_token);

            userListResult = JsonUtil.jsonToListUsers(JsonUtil.jsonValue(usersQuery,"Result"));
            leadListResult = JsonUtil.jsonToListLeads(JsonUtil.jsonValue(leadQuery,"Result"));
            commentListResult = JsonUtil.jsonToListComment(JsonUtil.jsonValue(commentQuery,"Result"));

            deletedUser = JsonUtil.jsonToListIds(JsonUtil.jsonValue(usersQuery, "Deleted"));
            deletedLead = JsonUtil.jsonToListIds(JsonUtil.jsonValue(leadQuery, "Deleted"));
            deletedComment = JsonUtil.jsonToListIds(JsonUtil.jsonValue(commentQuery,"Deleted"));

            InsertUsersOnDb(userListResult);
            InsertLeadsOnDb(leadListResult);
            InsertCommentsOnDb(commentListResult);

            deleteUsers(deletedUser);
            deleteLead(deletedLead);
            deleteComment(deletedComment);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.cancel(true);
    }

    public void  InsertUsersOnDb(List<User> userListResult){
        boolean status;
        DataHelper dataHelper = new DataHelper(_context);
        List<User> userList = dataHelper.GetAllUsers();

        if(userListResult != null || userListResult.size() != 0) {
            if (userList.size() > 0) {
                for (User us : userListResult) {
                    status = true;
                    for (User user : userList) {
                        if (us.getExternId() == user.getExternId()) {
                            status = false;
                        }
                    }
                    if(status)
                        dataHelper.insertUsers(us);
                }
            } else {
                for (User us : userListResult) {
                    dataHelper.insertUsers(us);
                }
            }
        }
    }

    public void InsertLeadsOnDb(List<Lead> leadListResult){
        boolean status;
        DataHelper dataHelper = new DataHelper(_context);
        List<Lead> leadList = dataHelper.GetAllLeads();

        if(leadListResult != null || leadListResult.size() != 0) {
            if (leadList.size() > 0) {
                for (Lead ld : leadListResult) {
                    status = true;
                    for (Lead lead : leadList) {
                        if (lead.getExternId() == ld.getExternId()) {
                            status = false;
                        }
                    }
                    if(status)
                        dataHelper.insertLeads(ld);
                }
            } else {
                for (Lead ld : leadListResult) {
                    dataHelper.insertLeads(ld);
                }
            }
        }
    }

    public void InsertCommentsOnDb(List<Comment> commentListResult){
        boolean status = true;
        DataHelper dataHelper = new DataHelper(_context);
        List<Comment> commentList = dataHelper.GetAllComments();

        if(commentListResult != null || commentListResult.size() != 0) {
            if (commentList.size() > 0) {
                for (Comment cmm : commentListResult) {
                    status = true;
                    for (Comment comment : commentList) {
                        if (comment.getExternId() == cmm.getExternId()) {
                            status = false;
                        }
                    }
                    if(status)
                        dataHelper.insertComments(cmm);
                }
            } else {
                for (Comment cmm : commentListResult) {
                    dataHelper.insertComments(cmm);
                }
            }
        }
    }

    public void deleteUsers(List<Integer> deletedUser){
        DataHelper dataHelper = new DataHelper(_context);
        List<User> userList = dataHelper.GetAllUsers();

        if(deletedUser.size() != 0){
            if(userList.size() != 0){
                for (int i : deletedUser) {
                    for (User user : userList) {
                        if(user.getExternId() == i){
                            user.setActive(0);
                            dataHelper.updateUsers(user);
                        }
                    }
                }
            }
        }
    }

    public void deleteLead(List<Integer> deletedLead){
        DataHelper dataHelper = new DataHelper(_context);
        List<Lead> leadList = dataHelper.GetAllLeads();

        if(deletedLead.size() != 0 ){
            if(leadList.size() != 0){
                for (int i : deletedLead) {
                    for (Lead lead : leadList) {
                        if(lead.getExternId() == i){
                            lead.setActive(0);
                            dataHelper.updateLeads(lead);
                        }
                    }
                }
            }
        }
    }

    public void deleteComment(List<Integer> deletedComment){
        DataHelper dataHelper = new DataHelper(_context);
        List<Comment> commentList = dataHelper.GetAllComments();

        if(deletedComment.size() != 0 ){
            if(commentList.size() != 0){
                for (int i : deletedComment) {
                    for (Comment comment : commentList) {
                        if(comment.getExternId() == i){
                            comment.setActive(0);
                            dataHelper.updateComments(comment);
                        }
                    }
                }
            }
        }
    }
}
