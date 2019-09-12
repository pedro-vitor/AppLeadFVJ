package com.NTI.AppFVJ.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

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

        ListView List = findViewById(R.id.lv_listleads);

        List<Lead> listLeads = new ArrayList<Lead>();

<<<<<<< HEAD
        Lead Le = new Lead();
=======
        Leads Le = new Leads();
        Le.setId(99);
>>>>>>> ae3fbb23aff3d19eae95630d5df2c89550db9961
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
                TextView tv_id, tv_name, tv_town, tv_address;

                tv_id = view.findViewById(R.id.tv_id);
                tv_name = view.findViewById(R.id.tv_name);
                tv_town = view.findViewById(R.id.tv_town);
                tv_address = view.findViewById(R.id.tv_address);

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("id", tv_id.getText().toString());
                intent.putExtra("name", tv_name.getText().toString());
                intent.putExtra("town", tv_town.getText().toString());
                intent.putExtra("address", tv_address.getText());

                startActivity(intent);
            }
        });

<<<<<<< HEAD
        LeadsAdapter Adapter = new LeadsAdapter(this, listLeads);
        List.setAdapter(Adapter);
=======
        LeadsAdapter leadsadapter = new LeadsAdapter(this, ListLeads);
        List.setAdapter(leadsadapter);
>>>>>>> ae3fbb23aff3d19eae95630d5df2c89550db9961
    }

    public void MoreOptions(View view) {
        final PopupWindow popupwindow;

        LayoutInflater layoutinflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customview = layoutinflater.inflate(R.layout.options_leads_list, null);

        popupwindow = new PopupWindow(customview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupwindow.setOutsideTouchable(true);
        popupwindow.setTouchable(true);
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
        popupwindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupwindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        popupwindow.showAsDropDown(view);
    }

    public void RegisterPeopleView(View view) {
        Intent intent = new Intent(this, RegisterPeopleActivity.class);
        startActivity(intent);
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