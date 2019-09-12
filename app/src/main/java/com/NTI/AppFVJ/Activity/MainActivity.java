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
import com.NTI.AppFVJ.Models.Leads;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView List = findViewById(R.id.lv_listleads);

        List<Leads> ListLeads = new ArrayList<Leads>();

        Leads Le = new Leads();
        Le.setId(99);
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

        LeadsAdapter leadsadapter = new LeadsAdapter(this, ListLeads);
        List.setAdapter(leadsadapter);
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