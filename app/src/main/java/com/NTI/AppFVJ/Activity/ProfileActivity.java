package com.NTI.AppFVJ.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Fragment.Fragment1;
import com.NTI.AppFVJ.Fragment.Fragment2;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.NTI.AppFVJ.ui.main.SectionsPagerAdapter;
import java.util.List;
import com.NTI.AppFVJ.Adapters.CommentsAdapter;

public class ProfileActivity extends AppCompatActivity {
    private Fragment1 fragment1;
    private TextView tv_nome;
    private TextView tv_email;
    private TextView tv_telefone;
    private TextView tv_curso;
    private TextView tv_endereco;
    private TextView tv_criado;

    private Fragment2 fragment2;
    private ListView lv_comentario;

    private List<Lead> leadList;
    private List<Comment> commentsList;

    private DataHelper dataHelper;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        tv_nome = findViewById(R.id.tv_nome);
        tv_email = findViewById(R.id.tv_email);
        tv_telefone = findViewById(R.id.tv_telefone);
        tv_curso = findViewById(R.id.tv_curso);
        tv_endereco = findViewById(R.id.tv_endereco);
        tv_criado = findViewById(R.id.tv_criado);

        lv_comentario = findViewById(R.id.lv_comentarios);

        //datasLeads(id);
        //datasComments(id);
    }

    public void datasLeads(int id){
        leadList = dataHelper.GetByIdLeads(id);

        tv_nome.setText("");
        tv_email.setText("");
        tv_telefone.setText("");
        tv_curso.setText("");
        tv_endereco.setText("");

        for (Lead lead : leadList) {
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
    }
}