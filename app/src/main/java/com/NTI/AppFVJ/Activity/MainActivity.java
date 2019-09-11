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
import com.NTI.AppFVJ.Models.Leads;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView List = findViewById(R.id.LeadsList);

        List<Leads> ListLeads = new ArrayList<Leads>();

        Leads Le = new Leads();
        Le.setTown("Aracati");
        Le.setName("Arthur da Silva Braga");
        Le.setAddress("Rua Coronel Alexanzito");

        Leads Le1 = new Leads();
        Le1.setTown("Fortim");
        Le1.setName("Davi Lima Silva");
        Le1.setAddress("Rua Test");

        Leads Le2 = new Leads();
        Le2.setTown("Icapui");
        Le2.setName("Izabelle Silva Costa");

        Leads Le3 = new Leads();
        Le3.setTown("Russas");
        Le3.setName("Maria Correia Silva");

        Leads Le4 = new Leads();
        Le4.setTown("Itaiçaba");
        Le4.setName("Matheus Oliveira dos Santos");

        Leads Le5 = new Leads();
        Le5.setTown("Itaiçaba");
        Le5.setName("Felipe Barros Lima");

        Leads Le6 = new Leads();
        Le6.setTown("Pontal");
        Le6.setName("Rafaela Barbosa");

        Leads Le7 = new Leads();
        Le7.setTown("Santa Tereza");
        Le7.setName("Armando Teobaldo Silva");

        Leads Le8 = new Leads();
        Le8.setTown("Aracati");
        Le8.setName("Rosangela Torres Lima");

        ListLeads.add(Le);
        ListLeads.add(Le1);
        ListLeads.add(Le2);
        ListLeads.add(Le3);
        ListLeads.add(Le4);
        ListLeads.add(Le5);
        ListLeads.add(Le6);
        ListLeads.add(Le7);
        ListLeads.add(Le8);

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        LeadsAdapter Adapter = new LeadsAdapter(this, ListLeads);
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