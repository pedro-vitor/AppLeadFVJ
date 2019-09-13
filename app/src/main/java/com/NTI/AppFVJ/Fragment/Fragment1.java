package com.NTI.AppFVJ.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.List;

public class Fragment1 extends Fragment {
    private TextView tv_nome, tv_email, tv_telefone, tv_curso, tv_endereco, tv_criado;
    private ListView lv_comentario;

    private List<Lead> leadsList;
    private List<Comment> commentsList;

    private DataHelper datahelper;

    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        tv_nome = view.findViewById(R.id.tv_nome);
        tv_email = view.findViewById(R.id.tv_email);
        tv_telefone = view.findViewById(R.id.tv_telefone);
        tv_curso = view.findViewById(R.id.tv_curso);
        tv_endereco = view.findViewById(R.id.tv_endereco);
        tv_criado = view.findViewById(R.id.tv_criado);

        lv_comentario = view.findViewById(R.id.lv_comentarios);
/*
        id = Integer.parseInt(getArguments().getString("id"));

        tv_nome.setText(getArguments().getString("nome"));
        tv_email.setText(getArguments().getString("email"));
        tv_telefone.setText(getArguments().getString("telefone"));
        tv_curso.setText(getArguments().getString("curso"));
        tv_endereco.setText(getArguments().getString("endereco"));
        tv_criado.setText(getArguments().getString("criado"));

        //datasLeads(id);
        //datasComments(id);
*/
        return view;
    }

    /*public void datasLeads(int id){
        leadsList = dataHelper.GetByIdLeads(id);

        tv_nome.setText("");
        tv_email.setText("");
        tv_telefone.setText("");
        tv_curso.setText("");
        tv_endereco.setText("");

        for (Leads lead : leadsList) {
            tv_nome.setText(lead.getName());
            tv_email.setText(lead.getEmail());
            tv_telefone.setText(lead.getNumber_phone());
            tv_curso.setText(lead.getDesired_course());
            tv_endereco.setText(lead.getAddress() + " - " + lead.getTown());
        }
    }

    public void datasComments(int id){
        commentsList = dataHelper.GetByIdComments(id);
        CommentsAdapter adapter = new CommentsAdapter(this, commentsList);
        lv_comentario.setAdapter(adapter);
    }*/
}
