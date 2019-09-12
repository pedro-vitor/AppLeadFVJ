package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_nome;
    private EditText et_email;
    private EditText et_usuario;
    private EditText et_senha;
    private EditText et_confirmSenha;

    private DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_nome = findViewById(R.id.et_nome);
        et_email = findViewById(R.id.et_email);
        et_usuario = findViewById(R.id.et_usuario);
        et_senha = findViewById(R.id.et_senha);
        et_confirmSenha = findViewById(R.id.et_confirmSenha);
    }

    public void RegisterClick(View view) {
        if(et_nome.getText().toString().trim().isEmpty()   ||
           et_email.getText().toString().trim().isEmpty()  ||
           et_usuario.getText().toString().trim().isEmpty()||
           et_senha.getText().toString().trim().isEmpty()  ||
           et_confirmSenha.getText().toString().trim().isEmpty()){

            Toast toast = Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG);
            toast.show();

        }else if(et_senha.getText().toString().trim() != et_confirmSenha.getText().toString().trim()){

            Toast toast = Toast.makeText(this, "Senhas incompativeis", Toast.LENGTH_LONG);
            toast.show();

        }else{
            User user = new User();
            user.setName(et_nome.getText().toString().trim());
            user.setEmail(et_email.getText().toString().trim());
            user.setUser(et_usuario.getText().toString().trim());
            user.setPassword(et_senha.getText().toString().trim());
            user.setActive(0);
            user.setCreatedAt(GetCurrentTime());

            dataHelper.insertUsers(user);

            Toast toast = Toast.makeText(this, "Usuário inserido com sucesso",Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private String GetCurrentTime(){
        SimpleDateFormat date_timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date time = calendar.getTime();

        String currentTime = date_timeFormat.format(time);

        return currentTime;
    }
}
