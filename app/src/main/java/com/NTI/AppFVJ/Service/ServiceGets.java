package com.NTI.AppFVJ.Service;

import android.content.Context;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;

import java.util.List;

public class ServiceGets{

    private Context _context;

    public ServiceGets(Context context){
        _context = context;
    }

    public void  InsertUsersOnDb(List<User> userListResult){
        DataHelper dataHelper = new DataHelper(_context);
        List<User> userList = dataHelper.GetAllUsers();

        if(userList.size() > 0) {
            for (User user : userList) {
                for (User us : userListResult) {
                    if (user.getExternId() != us.getExternId()) {
                        dataHelper.insertUsers(us);
                    }
                }
            }
        }else {
            for (User us : userListResult) {
                dataHelper.insertUsers(us);
            }
        }
    }

    public void InsertLeadsOnDb(List<Lead> leadListResult){
        DataHelper dataHelper = new DataHelper(_context);
        List<Lead> leadList = dataHelper.GetAllLeads();

        if(leadList.size() > 0) {
            for (Lead lead : leadList) {
                for (Lead ld : leadListResult) {
                    if (lead.getExternId() != ld.getExternId()) {
                        dataHelper.insertLeads(ld);
                    }
                }
            }
        }else{
            for (Lead ld : leadList) {
                dataHelper.insertLeads(ld);
            }
        }
    }

    public void InsertCommentsOnDb(List<Comment> commentListResult){
        DataHelper dataHelper = new DataHelper(_context);
        List<Comment> commentList = dataHelper.GetAllComments();

        if(commentList.size() > 0) {
            for (Comment user : commentList) {
                for (Comment cmm : commentListResult) {
                    if (user.getExternId() != cmm.getExternId()) {
                        dataHelper.insertComments(cmm);
                    }
                }
            }
        }else{
            for (Comment cmm : commentList) {
                dataHelper.insertComments(cmm);
            }
        }
    }
}
