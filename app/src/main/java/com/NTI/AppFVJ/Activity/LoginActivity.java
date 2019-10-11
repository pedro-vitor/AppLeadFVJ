package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;
import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_senha;

    private DataHelper dataHelper;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences("user_preference", MODE_PRIVATE);
        if (sharedpreferences.contains("logged")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);

        dataHelper = new DataHelper(this);
    }

    public void ForgotPasswordView(View view) {
        Intent intent = new Intent(this, ForgotEmailActivity.class);
        startActivity(intent);
    }

    public void RegisterView(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private String access_token = null;
    private boolean token_okay = false;

    public void LoginRequest(final String query) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = HttpConnection.POST("token", query);
                access_token = JsonUtil.jsonToken(result);

                if (access_token != null) {
                    editor = sharedpreferences.edit();
                    editor.putBoolean("logged", true);
                    editor.putString("email", et_email.getText().toString());
                    editor.putString("senha", et_senha.getText().toString());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("token", access_token);
                    startActivity(intent);
                    finish();
                }
            }
        });
        thread.start();
    }

    public void MainView(View view) {
        if (et_email.getText().toString().trim().isEmpty() || et_senha.getText().toString().trim().isEmpty()) {
            Toast toast = Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        if(!MaskEditUtil.validEmail(et_email.getText().toString().trim())){
            Toast.makeText(this, "Email invalido", Toast.LENGTH_SHORT).show();
        }
        else {
            LoginRequest("username="+et_email.getText().toString()+"&password="+et_senha.getText().toString()+"&grant_type=password");

            // User temp = JsonUtil.jsonToUser(result);
/*
            User user = new User();
            user.setEmail(et_email.getText().toString().trim());
            user.setPassword(et_senha.getText().toString().trim());

            int id = dataHelper.login(user);

            if (id > 0) {
                List<User> userList = dataHelper.GetByIdUsers(id);
                String name = "";

                for (User user1 : userList)
                    name = user1.getName();

                editor = sharedpreferences.edit();
                editor.putBoolean("logged", true);
                editor.putInt("id", id);
                editor.putString("name", name);
                editor.commit();

                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            else {
                Toast.makeText(this, "Email ou senha incorreto!", Toast.LENGTH_SHORT).show();
                et_senha.setText("");
            }*/
        }
    }
}