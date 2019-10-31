package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.R;

import com.NTI.AppFVJ.Service.Connetion;


public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_senha;

    private DataHelper dataHelper;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences FirstRun;
    private Connetion _connetion;
    private AlertDialog alert;

    private String email;
    private String senha;

    private boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences("user_preference", MODE_PRIVATE);
        FirstRun = getSharedPreferences("firstRun", MODE_PRIVATE);
        _connetion = new Connetion(this);

        if (sharedpreferences.contains("logged")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);


        dataHelper = new DataHelper(this);
    }

    public void ForgotPasswordView(View view) {
        Intent intent = new Intent(this, ForgotEmailActivity.class);
        startActivity(intent);
    }

    public void RegisterView(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private String access_token = null;

    private void LoginRequest(final String query, final String email, final String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = HttpConnection.SETDATAS("token","POST", query);
                access_token = JsonUtil.jsonValue(result, "access_token");
                Intent intent;

                if (access_token != null) {
                    editor = sharedpreferences.edit();
                    editor.putBoolean("logged", true);
                    editor.putString("email", email);
                    editor.putString("senha", password);
                    editor.commit();

                    if (FirstRun.getBoolean("firstRun", true)) {
                        FirstRun.edit().putBoolean("firstRun", false).apply();
                        intent = new Intent(LoginActivity.this, ScreenLoadingActivity.class);
                    }else {
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                    }

                    startActivity(intent);
                    finish();
                }
            }
        });
        thread.start();
    }

    public void MainView(View view) {
        if (et_email.getText().toString().trim().isEmpty() || et_senha.getText().toString().trim().isEmpty()) {
            Toast toast = Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        if(!MaskEditUtil.validEmail(et_email.getText().toString().trim())){
            Toast.makeText(this, "Email invalido", Toast.LENGTH_SHORT).show();
        }
        else {
            if(!_connetion.isConnected()) {
                dialogAlert();
                et_senha.setText("");
            }
            else {
                if (clicked == false) {
                    //LoginRequest("username=" + et_email.getText().toString() + "&password=" + et_senha.getText().toString() + "&grant_type=password", et_email.getText().toString(), et_senha.getText().toString());
                    clicked = true;
                    new AsyncLogin(this, et_email.getText().toString(), et_senha.getText().toString(), "username=" + et_email.getText().toString() + "&password=" + et_senha.getText().toString() + "&grant_type=password", this).execute();
                }
            }
        }
    }

    private void dialogAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sem Conexão");
        builder.setMessage("Conecte-se a internet para fazer login");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert = builder.create();
        alert.show();
    }

    private class AsyncLogin extends AsyncTask<Void,Void,Void>{
        private Context _context;
        private String _query;
        private String _email;
        private String _password;
        private String _result;

        private Intent _intent;
        private Activity _activity;

        public AsyncLogin(Context context, String email, String password, String query, Activity activity){
            _context = context;
            _intent = new Intent();
            _email = email;
            _password = password;
            _query = query;
            _activity = activity;
        }

        private PopupWindow popupWindow;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.animation_loader, null);

            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;

            boolean focusable = false;

            popupWindow = new PopupWindow(popupView, width, height, focusable);
            View view = (LinearLayout)findViewById(R.id.Login);
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            _result = HttpConnection.SETDATAS("token","POST", _query);
            access_token = JsonUtil.jsonValue(_result, "access_token");

            if (access_token != null) {
                editor = sharedpreferences.edit();
                editor.putBoolean("logged", true);
                editor.putString("email", _email);
                editor.putString("senha", _password);
                editor.commit();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clicked = false;
                    popupWindow.dismiss();

                    if(!"Erro de autenticação.".equals(_result)) {
                        if (FirstRun.getBoolean("firstRun", true)) {
                            FirstRun.edit().putBoolean("firstRun", false).apply();
                            _intent = new Intent(LoginActivity.this, ScreenLoadingActivity.class);
                        }else {
                            _intent = new Intent(LoginActivity.this, MainActivity.class);
                        }
                        startActivity(_intent);
                        _activity.finish();
                    }else{
                        Toast.makeText(_context,"Dados invalidos",Toast.LENGTH_SHORT).show();
                    }
                }
            }, 5000);
        }
    }
}