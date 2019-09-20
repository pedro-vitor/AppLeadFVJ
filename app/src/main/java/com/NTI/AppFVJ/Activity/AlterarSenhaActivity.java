package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.NTI.AppFVJ.R;

public class AlterarSenhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);
    }

    public void backToMain(View view){
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
        finish();
    }
}
