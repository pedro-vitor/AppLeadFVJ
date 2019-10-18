package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.NTI.AppFVJ.Service.Connetion;
import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_senha;

    private DataHelper dataHelper;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences FirstRun;
    private Connetion _connetion;
    private AlertDialog alert;

    private String email;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences("user_preference", MODE_PRIVATE);
        FirstRun = getSharedPreferences("firstRun", MODE_PRIVATE);
        _connetion = new Connetion(this);

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

    private void LoginRequest(final String query, final String email, final String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = HttpConnection.SETDATAS("token","POST", query);
                access_token = JsonUtil.jsonValue(result, "access_token");
                Intent intent;

                if (access_token != null) {
                    editor = sharedpreferences.edit();
                    editor.putBoolean("logged", true);
                    editor.putString("email", email);
                    editor.putString("senha", password);
                    editor.commit();

                    if (FirstRun.getBoolean("firstRun", true)) {
                        FirstRun.edit().putBoolean("firstRun", false).apply();
                        intent = new Intent(LoginActivity.this, ScreenLodingActivity.class);
                    }else {
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                    }

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
            if(!_connetion.isConnected()) {
                dialogAlert();
                et_senha.setText("");
            }else
                LoginRequest("username=" + et_email.getText().toString() + "&password=" + et_senha.getText().toString() + "&grant_type=password", et_email.getText().toString(), et_senha.getText().toString());
        }
    }

    private void dialogAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sem Conexão");
        builder.setMessage("Conecte-se a internet para fazer login");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert = builder.create();
        alert.show();
    }
}