package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.NTI.AppFVJ.R;

public class ForgotEmailActivity extends AppCompatActivity {
    private EditText et_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_email);

        /*if(is){
            et_email = findViewById(R.id.et_email);
            String email = getIntent().getStringExtra("Email");
            et_email.setText(email);
        }*/
    }

    public void InformEmail(View view){
        EditText email = findViewById(R.id.et_email);

        if(email.getText().toString().trim().isEmpty()){

            Toast toast = Toast.makeText(this, "Informe o email", Toast.LENGTH_SHORT);
            toast.show();

        }else {

            Intent intent = new Intent(this, ForgotPassActivity.class);
            intent.putExtra("Email", email.getText().toString());
            startActivity(intent);
            finish();

        }
    }

    public void CancelForgot(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public static class UserProfileActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_profile);
        }
    }
}
