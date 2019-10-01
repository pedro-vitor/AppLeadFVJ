package com.NTI.AppFVJ.Data;

import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static User jsonToUser(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            User user = new User(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"));
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Lead> jsonToListCampos(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            List<Lead> leads = new ArrayList<>();
            /*for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Lead campo = new Lead(
                        jsonObject.getInt("Id"),
                        jsonObject.getString("PesquisaCodigo"),
                        jsonObject.getString("Titulo"),
                        jsonObject.getString("Tipo"),
                        jsonObject.getInt("Ordem"),
                        jsonObject.getBoolean("IsCategoria"),
                        jsonObject.getBoolean("Destaque"),
                        jsonObject.getBoolean("Obrigatorio"));
                leads.add(campo);
            }*/
            return leads;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
