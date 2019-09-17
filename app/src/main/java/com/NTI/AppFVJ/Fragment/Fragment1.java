package com.NTI.AppFVJ.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.NTI.AppFVJ.Activity.ProfileActivity;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.List;

public class Fragment1 extends Fragment {
    private View view;

    private int id;

    private TextView tv_nome, tv_email, tv_telefone, tv_curso, tv_endereco, tv_criado;
    private ListView lv_comentario;

    private List<Lead> leadsList;
    private List<Comment> commentsList;

    private DataHelper datahelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        tv_nome = view.findViewById(R.id.tv_nome);
        tv_email = view.findViewById(R.id.tv_email);
        tv_telefone = view.findViewById(R.id.tv_telefone);
        tv_curso = view.findViewById(R.id.tv_curso);
        tv_endereco = view.findViewById(R.id.tv_endereco);
        tv_criado = view.findViewById(R.id.tv_criado);

        lv_comentario = view.findViewById(R.id.lv_comentarios);

        id = Integer.parseInt(ProfileActivity.getId());
        Toast.makeText(view.getContext(), ""+id, Toast.LENGTH_LONG).show();

        // DataLeads();
        // DataComments();

        return view;
    }

    private void DataLeads(){
        leadsList = datahelper.GetByIdLeads(id);

        tv_nome.setText("");
        tv_email.setText("");
        tv_telefone.setText("");
        tv_curso.setText("");
        tv_endereco.setText("");

        for (Lead lead : leadsList) {
            tv_nome.setText(lead.getName());
            tv_email.setText(lead.getEmail());
            tv_telefone.setText(lead.getNumber_phone());
            tv_curso.setText(lead.getDesired_course());
            tv_endereco.setText(lead.getAddress() + " - " + lead.getTown());
        }
    }
/*
    private void DataComments() {
        commentsList = datahelper.GetByIdComments(id);
        CommentsAdapter adapter = new CommentsAdapter(view.getContext(), commentsList);
        lv_comentario.setAdapter(adapter);
    }
*/
}
