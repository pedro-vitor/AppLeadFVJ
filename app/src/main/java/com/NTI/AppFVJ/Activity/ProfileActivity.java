package com.NTI.AppFVJ.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.NTI.AppFVJ.ui.main.SectionsPagerAdapter;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private TextView tv_firstName, tv_secondName;

    private static Intent intent;

    private DataHelper dataHelper;

    private AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        getSupportActionBar().setElevation(0);

        tv_firstName = findViewById(R.id.tv_firstname);
        tv_secondName = findViewById(R.id.tv_secondname);

        intent = getIntent();
        dataHelper = new DataHelper(this);

        setNames();
    }

    public static String getId() { return intent.getStringExtra("id"); }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                Intent intent2 = new Intent(this, UpdatePeopleActivity.class);
                intent2.putExtra("Id",intent.getStringExtra("id"));
                startActivity(intent2);
                break;
            case R.id.delete:
                dialogAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);

        return true;
    }

    private void dialogAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Deletar");
        builder.setMessage("Você deseja realmente deletar esta pessoa?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        alert =  builder.create();
        alert.show();
    }

    private void setNames(){
        List<Lead> leadList;
        String[] name = new String[2];
        int id = Integer.parseInt(intent.getStringExtra("id"));
        leadList = dataHelper.GetByIdLeads(id);

        for (Lead lead : leadList) {
            name = MaskEditUtil.returnOnlyName(lead.getName());
        }

        if(name.length > 1){
            tv_firstName.setText(name[0]);
            tv_secondName.setText(name[1]);
        }else {
            tv_firstName.setText(name[0]);
            tv_secondName.setText("");
        }


    }

}