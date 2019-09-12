package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Users;
import com.NTI.AppFVJ.R;

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
    }

    public void RegisterClick(View view) {
        if(et_nome.getText().toString().trim().isEmpty()   ||
           et_email.getText().toString().trim().isEmpty()  ||
           et_usuario.getText().toString().trim().isEmpty()||
           et_senha.getText().toString().trim().isEmpty()  ||
           et_confirmsenha.getText().toString().trim().isEmpty()) {

            Toast toast = Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG);
            toast.show();
        }
        else if(et_senha.getText().toString().trim() != et_confirmsenha.getText().toString().trim()) {
            Toast toast = Toast.makeText(this, "Senhas incompativeis", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Users users = new Users();
            users.setName(et_nome.getText().toString().trim());
            users.setEmail(et_email.getText().toString().trim());
            users.setUser(et_usuario.getText().toString().trim());
            users.setPassword(et_senha.getText().toString().trim());

            datahelper.insertUsers(users);

            Toast toast = Toast.makeText(this, "Usuário inserido com sucesso",Toast.LENGTH_SHORT);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
