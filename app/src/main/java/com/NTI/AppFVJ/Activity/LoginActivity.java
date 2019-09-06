package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.NTI.AppFVJ.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void ForgotPasswordView(View view) {
        Intent intent = new Intent(this, ForgotPassActivity.class);
        startActivity(intent);
    }

    public void RegisterView(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void MainView(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);


    }
}
