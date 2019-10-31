package com.NTI.AppFVJ.Fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.NTI.AppFVJ.Activity.MainActivity;
import com.NTI.AppFVJ.Activity.ProfileActivity;
import com.NTI.AppFVJ.Adapter.CommentsAdapter;
import com.NTI.AppFVJ.CurrentTime.CurrentTime;
import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;
import com.NTI.AppFVJ.Service.Connetion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends Fragment {
    private View view;

    private List<Comment> commentsList;

    private DataHelper datahelper;

    private int id;

    private ListView lv_comentarios;

    private AlertDialog alert;

    private SharedPreferences sharedpreferences;
    private String _email;
    private String _password;

    private Connetion connetion;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        id = Integer.parseInt(ProfileActivity.getId());

        sharedpreferences = getContext().getSharedPreferences("user_preference", MODE_PRIVATE);

        _email = sharedpreferences.getString("email","");
        _password = sharedpreferences.getString("senha","");

        lv_comentarios = view.findViewById(R.id.lv_comentarios);
        datahelper = new DataHelper(view.getContext());

        id = Integer.parseInt(ProfileActivity.getId());

        sharedpreferences = getContext().getSharedPreferences("user_preference", MODE_PRIVATE);

        DataComments();

        final EditText et_comment;
        et_comment = view.findViewById(R.id.et_comment);
        et_comment.setElevation(150);

        connetion = new Connetion(view.getContext());

        et_comment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (et_comment.getRight() - et_comment.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) - 100) {
                        if(et_comment.getText().toString().trim().isEmpty()){
                            Toast.makeText(getContext(),"Preencha o campo de comentário", Toast.LENGTH_SHORT).show();
                        }else{
                            //comments inserts
                            new AsyncInsertComment(et_comment.getText().toString()).execute();
                            et_comment.setText("");
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        lv_comentarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_id;

                tv_id = view.findViewById(R.id.tv_id);
                showComments(Integer.parseInt(tv_id.getText().toString()));
            }
        });
        return view;
    }

    private void DataComments() {
        commentsList = datahelper.GetByIdComments(id);
        if(!commentsList.isEmpty()){
            CommentsAdapter adapter = new CommentsAdapter(getContext(), commentsList);
            lv_comentarios.setAdapter(adapter);
        }
    }

    private void showComments(final int commentid){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        commentsList = datahelper.GetCommentsById(commentid);
        String mensagem = "";

        builder.setTitle("Comentário");
        for (Comment comment:commentsList) {
            mensagem = "Postado em: " + comment.getCreatedAt() + " \n \n "+comment.getText();
        }
        builder.setMessage(mensagem);


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        if (commentsList.get(0).getUserId() == MainActivity.getinternaluserid()) {
            builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Excluir comentário?");
                    builder.setMessage("Tem certeza que deseja excluir este comentário?");

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        /*commentsList.get(0).setActive(0);
                        datahelper.updateComments(commentsList.get(0));

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(Fragment2.this).attach(Fragment2.this).commit();*/
                            new AsyncDeleteComment().execute();
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Cancelar delete
                        }
                    });
                    alert = builder.create();
                    alert.show();
                }
            });
        }
        alert =  builder.create();
        alert.show();
    }

    private class AsyncInsertComment extends AsyncTask<Void, Void, Void>{
        private String _text;
        private boolean _connected = true;

        public AsyncInsertComment(String txt){
            _text = txt;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Type listTypeComment = new TypeToken<List<Comment>>() {}.getType();
            List<Comment> cmmList = new ArrayList<>();
            Lead lead = datahelper.GetByIdLeads(id).get(0);

            Comment comment = new Comment();
            comment.setLeadId(lead.getExternId());
            comment.setUserId(MainActivity.getIduser());
            comment.setText(_text);
            comment.setCreatedAt(CurrentTime.GetCurrentTime("yyyy-MM-dd HH:mm:ss"));
            comment.setActive(1);
            cmmList.add(comment);

            Gson gson = new Gson();
            String jsonComment = gson.toJson(cmmList, listTypeComment);

            if(connetion.isConnected()) {
                try {
                    String query = "username=" + _email + "&password=" + _password + "&grant_type=password";
                    String result = HttpConnection.SETDATAS("token", "POST", query);
                    String access_token = JsonUtil.jsonValue(result, "access_token");

                    cmmList.clear();
                    cmmList = JsonUtil.jsonToListComment(HttpConnection.SETDATAS("comment", jsonComment, "POST", access_token));

                    if(cmmList != null){
                        for (Comment cmm : cmmList) {
                            datahelper.insertComments(cmm);
                        }
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(Fragment2.this).attach(Fragment2.this).commit();
                    }
                }catch (Exception e){}
            }else{
                _connected = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(!_connected)
                Toast.makeText(getContext(),"Conecte-se a internet", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(),"Comentário adicionado com sucesso", Toast.LENGTH_SHORT).show();
            this.cancel(true);
        }
    }

    private class AsyncDeleteComment extends AsyncTask<Void, Void, Void>{
        private boolean _connected = true;

        @Override
        protected Void doInBackground(Void... voids) {
            Type listTypeComment = new TypeToken<List<Comment>>() {}.getType();
            List<Comment> cmmList = new ArrayList<>();

            commentsList.get(0).setActive(0);
            cmmList.add(commentsList.get(0));

            Gson gson = new Gson();
            String jsonComment = gson.toJson(cmmList, listTypeComment);

            if(connetion.isConnected()) {
                try {
                    String query = "username=" + _email + "&password=" + _password + "&grant_type=password";
                    String result = HttpConnection.SETDATAS("token", "POST", query);
                    String access_token = JsonUtil.jsonValue(result, "access_token");

                    cmmList.clear();
                    cmmList = JsonUtil.jsonToListComment(HttpConnection.SETDATAS("comment", jsonComment, "PUT", access_token));

                    if(cmmList != null){
                        for (Comment cmm : cmmList) {
                            datahelper.updateComments(cmm);
                        }
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(Fragment2.this).attach(Fragment2.this).commit();
                    }
                }catch (Exception e){}
            }else{
                _connected = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(!_connected)
                Toast.makeText(getContext(),"Conecte-se a internet", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(),"Comentário deletado com sucesso", Toast.LENGTH_SHORT).show();
            this.cancel(true);
        }
    }
}
