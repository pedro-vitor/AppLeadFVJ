package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;

public class UserProfileActivity extends AppCompatActivity {
    private TextView et_nome, et_email, et_usuario;

    private User user;
    private DataHelper datahelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        et_nome = findViewById(R.id.et_nome);
        et_email = findViewById(R.id.et_email);
        et_usuario = findViewById(R.id.et_usuario);

        datahelper = new DataHelper(this);
        id = MainActivity.getIduser();

        user = datahelper.GetByIdUsers(id).get(0);

        et_nome.setText(user.getName());
        et_email.setText(user.getEmail());
        et_usuario.setText(user.getUser());
    }

    public void backToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void changePassword(View view){
        Intent intent = new Intent(this, AlterarSenhaActivity.class);
        startActivity(intent);
    }

    public void Atualizar(View view) {
        if (et_nome.getText().toString().length() > 0 &&
            et_email.getText().toString().length() > 0 &&
            et_usuario.getText().toString().length() > 0) {

            user.setName(et_nome.getText().toString());
            user.setEmail(et_email.getText().toString());
            user.setUser(et_usuario.getText().toString());

            datahelper.updateUsers(user);

            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
