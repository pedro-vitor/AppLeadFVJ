package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;

public class ScreenLoadingActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    private SharedPreferences FirstRun;
    private String email;
    private String senha;
    private int TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_loding);

        sharedpreferences = getSharedPreferences("user_preference", MODE_PRIVATE);
        FirstRun = getSharedPreferences("firstRun", MODE_PRIVATE);

        email = sharedpreferences.getString("email","");
        senha = sharedpreferences.getString("senha","");

        Loading loading = new Loading(email,senha);
        loading.execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent act = new Intent(ScreenLoadingActivity.this,MainActivity.class);
                startActivity(act);
                finish();
            }
        },TIME_OUT);
    }

    private class Loading extends AsyncTask<Void, Void, Void>{
        private DataHelper dataHelper;

        private String _email;
        private String _password;

        private  List<User> userListResult = new ArrayList<>();
        private  List<Lead> leadListResult = new ArrayList<>();
        private  List<Comment> commentListResult = new ArrayList<>();

        public Loading(String email, String password){
            dataHelper = new DataHelper(ScreenLoadingActivity.this);
            _email = email;
            _password = password;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            String query = "username="+_email+"&password="+_password+"&grant_type=password";
            String result = HttpConnection.POST("token", query);
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
            List<User> userList = dataHelper.GetAllUsers();

            if(userListResult != null || userListResult.size() != 0) {
                if (userList.size() > 0) {
                    for (User user : userList) {
                        for (User us : userListResult) {
                            if (user.getExternId() != us.getExternId()) {
                                dataHelper.insertUsers(us);
                            }
                        }
                    }
                } else {
                    for (User us : userListResult) {
                        dataHelper.insertUsers(us);
                    }
                }
            }
        }

        public void InsertLeadsOnDb(List<Lead> leadListResult){
            List<Lead> leadList = dataHelper.GetAllLeads();

            if(leadListResult != null || leadListResult.size() != 0) {
                if (leadList.size() > 0) {
                    for (Lead lead : leadList) {
                        for (Lead ld : leadListResult) {
                            if (lead.getExternId() != ld.getExternId()) {
                                dataHelper.insertLeads(ld);
                            }
                        }
                    }
                } else {
                    for (Lead ld : leadListResult) {
                        dataHelper.insertLeads(ld);
                    }
                }
            }
        }

        public void InsertCommentsOnDb(List<Comment> commentListResult){
            List<Comment> commentList = dataHelper.GetAllComments();

            if(commentListResult != null || commentListResult.size() != 0) {
                if (commentList.size() > 0) {
                    for (Comment user : commentList) {
                        for (Comment cmm : commentListResult) {
                            if (user.getExternId() != cmm.getExternId()) {
                                dataHelper.insertComments(cmm);
                            }
                        }
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
