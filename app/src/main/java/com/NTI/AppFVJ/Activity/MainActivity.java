package com.NTI.AppFVJ.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.NTI.AppFVJ.Adapter.LeadsAdapter;
import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DataHelper datahelper;
    private List<Lead> listLeads;
    private List<Lead> search_result_arraylist;
    private String     keyword;
    private ListView   List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List = findViewById(R.id.lv_listleads);
        datahelper = new DataHelper(this);

        listLeads = datahelper.GetAllLeads();

        Lead lead = new Lead();
        lead.setId(999);
        lead.setName("Mariana Costa Silva");
        lead.setAddress("Rua Oliveira Barros");
        lead.setTown("Fortaleza");

        listLeads.add(lead);

        LeadsAdapter leadsadapter = new LeadsAdapter(this, listLeads);
        List.setAdapter(leadsadapter);

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_id;

                tv_id = view.findViewById(R.id.tv_id);

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("id", tv_id.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void RegisterPeopleView(View view) {
        Intent intent = new Intent(this, RegisterPeopleActivity.class);
        startActivity(intent);
    }

    public void UpdatePeopleView(View view) {
        ListView listView = findViewById(R.id.lv_listleads);
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

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();

                search_result_arraylist = new ArrayList<Lead>();
                search_result_arraylist.clear();
                keyword = query.toUpperCase();

                for(int i = 0 ; i < listLeads.size(); i++){
                    if(listLeads.get(i).getName().toUpperCase().contains(keyword)){
                        search_result_arraylist.add(listLeads.get(i));
                    }
                }

                LeadsAdapter leadsadapter = new LeadsAdapter(MainActivity.this, search_result_arraylist);
                List.setAdapter(leadsadapter);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}