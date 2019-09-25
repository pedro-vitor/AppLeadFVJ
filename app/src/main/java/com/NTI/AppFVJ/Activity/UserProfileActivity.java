package com.NTI.AppFVJ.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;

public class UserProfileActivity extends AppCompatActivity {
    private TextView tv_nome;
    private EditText et_nome, et_email;

    private User user;
    private DataHelper datahelper;
    private int id;

    private boolean edit_pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tv_nome = findViewById(R.id.tv_nome);
        et_nome = findViewById(R.id.et_nome);
        et_email = findViewById(R.id.et_email);

        et_nome.setEnabled(edit_pressed);
        et_email.setEnabled(edit_pressed);

        datahelper = new DataHelper(this);
        id = MainActivity.getIduser();

        user = datahelper.GetByIdUsers(id).get(0);

        getSupportActionBar().setElevation(0);

        tv_nome.setText(user.getName().split(" ")[0]);
        et_nome.setText(user.getName());
        et_email.setText(user.getEmail());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                edit_pressed = true;

                et_nome.setEnabled(edit_pressed);
                et_email.setEnabled(edit_pressed);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_profile, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void dialogAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Descartar Informações?");
        builder.setMessage("Deseja descartar as alterações feitas?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(UserProfileActivity.this, MainActivity.class));
                finish();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alert;
        alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        if (edit_pressed)
            dialogAlert();
        else {
            startActivity(new Intent(UserProfileActivity.this, MainActivity.class));
            finish();
        }
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
            et_email.getText().toString().length() > 0) {

            user.setName(et_nome.getText().toString());
            user.setEmail(et_email.getText().toString());

            datahelper.updateUsers(user);

            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
