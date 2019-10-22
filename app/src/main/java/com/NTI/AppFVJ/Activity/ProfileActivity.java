package com.NTI.AppFVJ.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.NTI.AppFVJ.ui.main.SectionsPagerAdapter;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
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
                intent2.putExtra("Id", intent.getStringExtra("id"));
                startActivity(intent2);
                break;
            case R.id.delete:
                DeleteLead();
                break;
            case R.id.more:
                dialogAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DeleteLead() {
        new AsyncDeleteLead().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);

        return true;
    }

    private void dialogAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        List<Lead> leads = dataHelper.GetByIdLeads(Integer.parseInt(getId()));
        List<User> user = dataHelper.GetByIdUsers(leads.get(0).getUserId());
        /*for (Lead ld : leads) {
            user
        }*/

        builder.setTitle("Mais Informações");
        builder.setMessage("Criado em: "+MaskEditUtil.setDateFormat(leads.get(0).getCreatedAt())+"\r\n\n"+
                           "Criado por: "+user.get(0).getName());

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert = builder.create();
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

        setTitle(name[0]);
    }

    private class AsyncDeleteLead extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            int id;
            id = Integer.parseInt(intent.getStringExtra("id"));
            Lead lead = dataHelper.GetByIdLeads(id).get(0);
            lead.setActive(0);
            lead.setUpdated(1);

            dataHelper.updateLeads(lead);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            finish();
        }
    }
}