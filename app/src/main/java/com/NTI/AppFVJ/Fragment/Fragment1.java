package com.NTI.AppFVJ.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.NTI.AppFVJ.Activity.MainActivity;
import com.NTI.AppFVJ.Activity.ProfileActivity;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;
import com.google.gson.Gson;

import java.util.List;

public class Fragment1 extends Fragment implements Toolbar.OnMenuItemClickListener {
    private View view;

    private int id;

    private TextView tv_nome, tv_email, tv_telefone, tv_curso, tv_endereco;
    private List<Lead> leadsList;

    private DataHelper datahelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        tv_nome = view.findViewById(R.id.tv_nome);
        tv_email = view.findViewById(R.id.tv_email);
        tv_telefone = view.findViewById(R.id.tv_telefone);
        tv_curso = view.findViewById(R.id.tv_curso);
        tv_endereco = view.findViewById(R.id.tv_endereco);

        id = Integer.parseInt(ProfileActivity.getId());

        datahelper = new DataHelper(view.getContext());

        id = Integer.parseInt(ProfileActivity.getId());

        DataLeads();
        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.delete:
                DeleteLead();
                return true;
        }
        return false;
    }

    private void DeleteLead() {
        Lead lead = datahelper.GetByIdLeads(id).get(0);
        lead.setActive(0);

        Gson gson = new Gson();
        String jsonLead = gson.toJson(lead);

        String result = HttpConnection.SETDATAS("lead", "DELETE", jsonLead);
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
            tv_telefone.setText(MaskEditUtil.setmask(lead.getNumberPhone()));
            tv_curso.setText(lead.getDesiredCourse());
            tv_endereco.setText(lead.getAddress() + " - " + lead.getTown());
        }
    }
}
