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
    private EditText et_nome, et_email, et_usuario, et_senha, et_confirmsenha;

    private DataHelper datahelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_nome = findViewById(R.id.et_nome);
        et_email = findViewById(R.id.et_email);
        et_usuario = findViewById(R.id.et_usuario);
        et_senha = findViewById(R.id.et_senha);
        et_confirmsenha = findViewById(R.id.et_confirmSenha);

        datahelper = new DataHelper(this);
    }

    public void RegisterClick(View view) {
        if(et_nome.getText().toString().trim().isEmpty()   ||
           et_email.getText().toString().trim().isEmpty()  ||
           et_usuario.getText().toString().trim().isEmpty()||
           et_senha.getText().toString().trim().isEmpty()  ||
           et_confirmsenha.getText().toString().trim().isEmpty()) {

            Toast toast = Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG);
            toast.show();
        }else if(!et_senha.getText().toString().equals(et_confirmsenha.getText().toString())){

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

            datahelper.insertUsers(user);


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
