package com.NTI.AppFVJ.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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
import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.List;


import com.NTI.AppFVJ.Activity.ProfileActivity;
import com.NTI.AppFVJ.Adapter.CommentsAdapter;
import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.AdapterView.*;

public class Fragment2 extends Fragment {
    private View view;

    private List<Comment> commentsList;

    private DataHelper datahelper;

    private int id;

    private ListView lv_comentarios;

    private AlertDialog alert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);


        id = Integer.parseInt(ProfileActivity.getId());

        lv_comentarios = view.findViewById(R.id.lv_comentarios);
        datahelper = new DataHelper(view.getContext());

        id = Integer.parseInt(ProfileActivity.getId());

        DataComments();

        final EditText et_comment;
        et_comment = view.findViewById(R.id.et_comment);

        et_comment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (et_comment.getRight() - et_comment.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(et_comment.getText().toString().trim().isEmpty()){
                            Toast.makeText(getContext(),"Preencha o campo de comentário", Toast.LENGTH_SHORT).show();
                        }else{
                            Comment comment = new Comment();
                            comment.setLeads_Id(id);
                            comment.setUsers_Id(MainActivity.getIduser());
                            comment.setText(et_comment.getText().toString().trim());
                            comment.setCreatedAt(CurrentTime.GetCurrentTime("yyyy-MM-dd HH:mm:ss"));

                            if(datahelper.insertComments(comment) > 0){
                                Toast.makeText(getContext(),"Comentário adicionado com sucesso", Toast.LENGTH_SHORT).show();
                                et_comment.setText("");

                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(Fragment2.this).attach(Fragment2.this).commit();

                            }else{
                                Toast.makeText(getContext(),"Erro ao adicionar comentário", Toast.LENGTH_SHORT).show();
                            }
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

    private void showComments(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        commentsList = datahelper.GetCommentsById(id);
        String mensagem = "";

        builder.setTitle("Comentário");
        for (Comment comment:commentsList) {
            mensagem = "Postado em: " + comment.getCreatedAt() + " \n \n "
                              +comment.getText();
        }
        builder.setMessage(mensagem);


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        alert =  builder.create();
        alert.show();
    }
}
