package com.NTI.AppFVJ.Data;

import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static String jsonToken(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String token = jsonObject.getString("access_token");
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User jsonToUser(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            User user = new User();

            user.setId(jsonObject.getInt("Id"));
            user.setExternId(jsonObject.getInt("ExternId"));
            user.setName(jsonObject.getString("name"));
            user.setEmail(jsonObject.getString("email"));
            user.setPassword(jsonObject.getString("password"));
            user.setCreatedAt(jsonObject.getString("createdat"));
            user.setActive(jsonObject.getInt("active"));

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<User> jsonToListUsers(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            List<User> users = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                User user = new User();

                user.setId(jsonObject.getInt("Id"));
                user.setExternId(jsonObject.getInt("ExternId"));
                user.setName(jsonObject.getString("name"));
                user.setEmail(jsonObject.getString("email"));
                user.setPassword(jsonObject.getString("password"));
                user.setCreatedAt(jsonObject.getString("createdat"));
                user.setActive(jsonObject.getInt("active"));

                users.add(user);
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Lead> jsonToListLeads(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            List<Lead> leads = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Lead campo = new Lead();

                campo.setId(jsonObject.getInt("Id"));
                campo.setExternId(jsonObject.getInt("ExternId"));
                campo.setUserId(jsonObject.getInt("UserId"));
                campo.setName(jsonObject.getString("name"));
                campo.setEmail(jsonObject.getString("email"));
                campo.setNumberPhone(jsonObject.getString("numberphone"));
                campo.setDesiredCourse(jsonObject.getString("desiredcourse"));
                campo.setTown(jsonObject.getString("town"));
                campo.setAddress(jsonObject.getString("address"));
                campo.setActive(jsonObject.getInt("active"));

                leads.add(campo);
            }

            return leads;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Comment> jsonToListComment(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            List<Comment> comments   = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Comment comment = new Comment();

                comment.setId(jsonObject.getInt("Id"));
                comment.setExternId(jsonObject.getInt("ExternId"));
                comment.setLeadsId(jsonObject.getInt("LeadId"));
                comment.setUsersId(jsonObject.getInt("UserId"));
                comment.setText(jsonObject.getString("text"));
                comment.setActive(jsonObject.getInt("active"));

                comments.add(comment);
            }

            return comments;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
