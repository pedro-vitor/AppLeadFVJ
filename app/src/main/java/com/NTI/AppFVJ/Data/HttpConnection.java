package com.NTI.AppFVJ.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
            con.setRequestProperty("Content-Type", "application/json");
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
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.addRequestProperty("Authorization", "Bearer " + token);
            con.setUseCaches(false);
            con.setDoOutput(true);

            //Write
            OutputStream outputStream = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(json);
            writer.close();
            outputStream.close();

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                //Read
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else if (responseCode == 400 || responseCode == 401) {
                return "Erro de autenticação.";
            } else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String PUT(String path, String json) {
        try {
            URL obj = new URL(URL_API + path);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
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

    public static String PUT(String path, String json, String token) {
        try {
            URL obj = new URL(URL_API + path);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
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
            objConnection.connect();

            InputStream inputStream = objConnection.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
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
