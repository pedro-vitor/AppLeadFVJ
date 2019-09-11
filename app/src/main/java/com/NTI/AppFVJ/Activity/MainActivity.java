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
import com.NTI.AppFVJ.Models.Leads;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;

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