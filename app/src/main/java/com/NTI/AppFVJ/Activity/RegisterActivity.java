package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.Data.Filter;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.NTI.AppFVJ.CurrentTime.CurrentTime.GetCurrentTime;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_nome, et_email, et_senha, et_confirmsenha;
    private DataHelper datahelper;


    private List<User> userList;
    private List<User> userListResult;
    private Type listTypeUser = new TypeToken<List<User>>() {}.getType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_nome = findViewById(R.id.et_nome);
        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
        et_confirmsenha = findViewById(R.id.et_confirmSenha);
        userList = new ArrayList<>();

        datahelper = new DataHelper(this);
    }

    private void insertRequest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String name_upcase = et_nome.getText().toString().trim().substring(0, 1).toUpperCase().concat(et_nome.getText().toString().trim().substring(1));
                String Date = GetCurrentTime("yyyy-MM-dd");
                String Hours = GetCurrentTime("HH:mm:ss");

                User user = new User();
                user.setName(name_upcase);
                user.setEmail(et_email.getText().toString().trim());
                user.setPassword(et_senha.getText().toString().trim());
                user.setActive(1);
                user.setCreatedAt(Date + "T" + Hours);

                userList.add(user);

                Gson gson = new Gson();
                String jsonUser = gson.toJson(userList, listTypeUser);
                userListResult = JsonUtil.jsonToListUsers(HttpConnection.SETDATAS("user","POST", jsonUser));

                for (User usr : userListResult) {
                    datahelper.insertUsers(usr);
                }
            }
        });
        thread.start();
    }

    public void RegisterClick(View view) {

        if (!Filter.Nome(et_nome.getText().toString()))
            Toast.makeText(this, "O nome de usuario deve conter no mínimo 3 caracteres e no máximo 50", Toast.LENGTH_LONG).show();
        else
        if (!Filter.Senha(et_senha.getText().toString()))
            Toast.makeText(this, "A senha deve conter no mínimo 4 caracteres e no máximo 15", Toast.LENGTH_SHORT).show();
        else
        if (et_nome.getText().toString().trim().isEmpty() || et_email.getText().toString().trim().isEmpty() || et_senha.getText().toString().trim().isEmpty() || et_confirmsenha.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
        }
        else
        if(!et_senha.getText().toString().equals(et_confirmsenha.getText().toString())){
            Toast.makeText(this, "Senhas incompativeis", Toast.LENGTH_SHORT).show();
        }
        else
        if(!MaskEditUtil.validEmail(et_email.getText().toString().trim())){
            Toast.makeText(this,"Informe um Email valido", Toast.LENGTH_SHORT).show();
        }
        else {

            try{
                insertRequest();
                Toast.makeText(RegisterActivity.this, "Usuário inserido com sucesso",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }catch (Exception e){
                Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_LONG);
            }
        }
    }
}
