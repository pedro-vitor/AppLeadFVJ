package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.Filter;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;

public class AlterarSenhaActivity extends AppCompatActivity {
    private TextView et_senhaAtual, et_senha, et_confirmSenha;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    private DataHelper datahelper;
    private int id;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        id = MainActivity.getinternaluserid();
        datahelper = new DataHelper(this);

        et_senhaAtual = findViewById(R.id.et_senhaAtual);
        et_senha = findViewById(R.id.et_senha);
        et_confirmSenha = findViewById(R.id.et_confirmSenha);

        sharedpreferences = getSharedPreferences("user_preference", MODE_PRIVATE);

        user = datahelper.GetByIdUsers(id).get(0);
    }

    public void Atualizar(View view) {
        if (et_senhaAtual.getText().toString().length() > 0)
            if (et_senhaAtual.getText().toString().equals(user.getPassword())) {
                if (Filter.Senha(et_senha.getText().toString()) && Filter.Senha(et_confirmSenha.getText().toString())) {
                    if (et_senha.getText().toString().equals(et_confirmSenha.getText().toString())) {
                        user.setPassword(et_senha.getText().toString());
                        user.setUpdated(1);

                        datahelper.updateUsers(user);

                        editor = sharedpreferences.edit();
                        editor.putBoolean("logged", true);
                        editor.putString("email", user.getEmail());
                        editor.putString("senha", user.getPassword());
                        editor.commit();

                        startActivity(new Intent(this, MainActivity.class));
                    }
                }
                else {
                    Toast.makeText(this, "A senha deve conter no mínimo 4 caracteres e no máximo 15", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Digite a sua senha atual", Toast.LENGTH_SHORT).show();
            }
    }
}
