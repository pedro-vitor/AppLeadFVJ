package com.NTI.AppFVJ.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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
    private LeadsAdapter leadsadapter;


    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private static int id_user = 0;
    private static String name_user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences("user_preference", MODE_PRIVATE);
        id_user = sharedpreferences.getInt("id",0);
        name_user = sharedpreferences.getString("name","");

        List = findViewById(R.id.lv_listleads);

        datahelper = new DataHelper(this);
        listLeads = datahelper.GetAllLeads();

        leadsadapter = new LeadsAdapter(this, listLeads);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                startActivity(new Intent(this, UserProfileActivity.class));
                break;
            case R.id.Register:
                startActivity(new Intent(this, RegisterPeopleActivity.class));
                break;
            case R.id.Logout:
                editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static int getIduser(){
        return id_user;
    }
    public static String getNameUser(){return name_user;}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(searchViewItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                List.setAdapter(leadsadapter);
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
        });
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setQueryHint("Procurar...");
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_result_arraylist = new ArrayList<Lead>();
                search_result_arraylist.clear();
                keyword = query.toUpperCase();

                for(int i = 0 ; i < listLeads.size(); i++){
                    if(listLeads.get(i).getName().toUpperCase().contains(keyword)) {
                        search_result_arraylist.add(listLeads.get(i));
                    }
                }

                LeadsAdapter leadssearchadapter = new LeadsAdapter(MainActivity.this, search_result_arraylist);
                List.setAdapter(leadssearchadapter);
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