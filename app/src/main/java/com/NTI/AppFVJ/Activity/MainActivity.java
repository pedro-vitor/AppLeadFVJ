package com.NTI.AppFVJ.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.NTI.AppFVJ.Adapter.LeadsAdapter;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView List = findViewById(R.id.LeadsList);

        List<Lead> listLeads = new ArrayList<Lead>();

        Lead Le = new Lead();
        Le.setTown("Aracati");
        Le.setName("Arthur da Silva Braga");
        Le.setAddress("Rua Coronel Alexanzito");

        Lead Le1 = new Lead();
        Le1.setTown("Fortim");
        Le1.setName("Davi Lima Silva");
        Le1.setAddress("Rua Test");

        Lead Le2 = new Lead();
        Le2.setTown("Icapui");
        Le2.setName("Izabelle Silva Costa");

        Lead Le3 = new Lead();
        Le3.setTown("Russas");
        Le3.setName("Maria Correia Silva");

        Lead Le4 = new Lead();
        Le4.setTown("Itaiçaba");
        Le4.setName("Matheus Oliveira dos Santos");

        Lead Le5 = new Lead();
        Le5.setTown("Itaiçaba");
        Le5.setName("Felipe Barros Lima");

        Lead Le6 = new Lead();
        Le6.setTown("Pontal");
        Le6.setName("Rafaela Barbosa");

        Lead Le7 = new Lead();
        Le7.setTown("Santa Tereza");
        Le7.setName("Armando Teobaldo Silva");

        Lead Le8 = new Lead();
        Le8.setTown("Aracati");
        Le8.setName("Rosangela Torres Lima");

        listLeads.add(Le);
        listLeads.add(Le1);
        listLeads.add(Le2);
        listLeads.add(Le3);
        listLeads.add(Le4);
        listLeads.add(Le5);
        listLeads.add(Le6);
        listLeads.add(Le7);
        listLeads.add(Le8);

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        LeadsAdapter Adapter = new LeadsAdapter(this, listLeads);
        List.setAdapter(Adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                break;
            case R.id.Register:
                break;
            case R.id.Logout:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
}