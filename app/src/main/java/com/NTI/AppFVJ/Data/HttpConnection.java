package com.NTI.AppFVJ.Data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpConnection {

    public static final String URL_API = "https://portal.fvj.br/APIs/Lead/api/";

    public static String POST(String path, String json) {
        try {
            URL obj = new URL(URL_API + path);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("charset", "utf-8");
            con.setUseCaches(false);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(json);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String POST(String path, String json, String token) {
        try {
            URL obj = new URL(URL_API + path);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("charset", "utf-8");
            con.addRequestProperty("Authorization", "Bearer " + token);
            con.setUseCaches(false);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(json);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String GET(String path) {
        try {
            URL uURLAddress = new URL(URL_API + path);
            HttpURLConnection objConnection = (HttpURLConnection) uURLAddress.openConnection();
            objConnection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(objConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String GET(String path, String token) {
        try {
            URL uURLAddress = new URL(URL_API + path);
            HttpURLConnection objConnection = (HttpURLConnection) uURLAddress.openConnection();
            objConnection.setRequestMethod("GET");
            objConnection.addRequestProperty("Authorization", "Bearer " + token);

            BufferedReader in = new BufferedReader(new InputStreamReader(objConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
