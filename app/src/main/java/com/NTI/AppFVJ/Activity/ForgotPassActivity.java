package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.R;

public class ForgotPassActivity extends AppCompatActivity {
    private TextView et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        EditText Code = findViewById(R.id.Code);
        Code.addTextChangedListener(MaskEditUtil.mask(Code,"####-####"));

        et_email = findViewById(R.id.et_email);
        String email = getIntent().getStringExtra("Email");

        et_email.setText(email);
    }

    public void CorrectEmail(View view){
        Intent intent = new Intent(this, ForgotEmailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra("Email", et_email.getText().toString());
        startActivity(intent);
    }
}
