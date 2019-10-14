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
import android.widget.Toast;

import com.NTI.AppFVJ.Adapter.LeadsAdapter;
import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.HttpConnection;
import com.NTI.AppFVJ.Data.JsonUtil;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;
import com.NTI.AppFVJ.Service.ServiceExport;
import com.NTI.AppFVJ.Service.ServiceGets;

import java.util.ArrayList;
import java.util.List;

import static com.NTI.AppFVJ.CurrentTime.CurrentTime.GetCurrentTime;

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

    private static List<User> userListResult = new ArrayList<>();
    private static List<Lead> leadListResult = new ArrayList<>();
    private static List<Comment> commentListResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, getIntent().getStringExtra("token"), Toast.LENGTH_LONG).show();

        sharedpreferences = getSharedPreferences("user_preference", MODE_PRIVATE);
        sharedpreferences = getSharedPreferences("firstRun", MODE_PRIVATE);

        List = findViewById(R.id.lv_listleads);

        datahelper = new DataHelper(this);
        listLeads = datahelper.GetAllLeads();
        userListResult = datahelper.GetAllUsers();

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

        Intent intent = new Intent(MainActivity.this, ServiceExport.class);
        startService(intent);
    }

    private void Resume() {
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (sharedpreferences.getBoolean("firstRun", true)) {
                    if (datahelper.GetAllUsers().size() > 0) {

                        userListResult = JsonUtil.jsonToListUsers(HttpConnection.GET("user"));
                        leadListResult = JsonUtil.jsonToListLeads(HttpConnection.GET("lead"));
                        commentListResult = JsonUtil.jsonToListComment(HttpConnection.GET("comment"));

                        ServiceGets serviceGets = new ServiceGets(MainActivity.this);
                        serviceGets.InsertUsersOnDb(userListResult);
                        serviceGets.InsertLeadsOnDb(leadListResult);
                        serviceGets.InsertCommentsOnDb(commentListResult);

                        sharedpreferences.edit().putBoolean("firstRun", false).apply();
                    }
                }
            }
        });
        thread.start();*/
        Toast.makeText(getApplicationContext(),"AAAAAAAAA", Toast.LENGTH_LONG);

        /*if (sharedpreferences.getBoolean("firstRun", true)) {
            if(datahelper.GetAllUsers().size() > 0) {
                ServiceGets serviceGets = new ServiceGets(this);
                serviceGets.execute();
                sharedpreferences.edit().putBoolean("firstRun", false).apply();
            }
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedpreferences.getBoolean("firstRun", true)) {
            sharedpreferences.edit().putBoolean("firstRun", false).apply();

                        //if (datahelper.GetAllUsers().size() > 0) {
                        List<User> userList = datahelper.GetAllUsers();

                        userListResult = JsonUtil.jsonToListUsers(HttpConnection.GET("user"));

                        if(userList.size() > 0) {
                            for (User user : userList) {
                                for (User us : userListResult) {
                                    if (user.getExternId() != us.getExternId()) {
                                        datahelper.insertUsers(us);
                                    }
                                }
                            }
                        }else {
                            for (User us : userListResult) {
                                datahelper.insertUsers(us);
                            }
                        }
                        //}

        } else {
            Toast.makeText(getApplicationContext(), "segundo? terceiro?...", Toast.LENGTH_LONG).show();
        }
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