package com.NTI.AppFVJ.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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

    public void MoreOptions(View view) {
        final PopupWindow popupWindow;
        LinearLayout linearLayout1 = findViewById(R.id.activity_main);

        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.options_leads_list,null);

        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        popupWindow.showAsDropDown(view);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                break;
            case R.id.Register:
                Intent intent2 = new Intent(this, RegisterPeopleActivity.class);
                startActivity(intent2);
                break;
            case R.id.Logout:
                Intent intent3 = new Intent(this, LoginActivity.class);
                startActivity(intent3);
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