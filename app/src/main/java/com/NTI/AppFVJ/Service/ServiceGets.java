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

    private static List<User> userListResult = new ArrayList<>();
    private static List<Lead> leadListResult = new ArrayList<>();
    private static List<Comment> commentListResult = new ArrayList<>();

    public ServiceGets(Context context, String email, String password){
        _context = context;
        dataHelper = new DataHelper(_context);
        _email = email;
        _password = password;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        String query = "username="+_email+"&password="+_password+"&grant_type=password";
        String result = HttpConnection.SETDATAS("token","POST", query);
        String access_token = JsonUtil.jsonValue(result, "access_token");

        userListResult = JsonUtil.jsonToListUsers(HttpConnection.GET("user"));
        leadListResult = JsonUtil.jsonToListLeads(HttpConnection.GET("lead",access_token));
        commentListResult = JsonUtil.jsonToListComment(HttpConnection.GET("comment",access_token));

        InsertUsersOnDb(userListResult);
        InsertLeadsOnDb(leadListResult);
        InsertCommentsOnDb(commentListResult);

        return null;
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
        boolean status = true;
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

}
