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
import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView List = findViewById(R.id.lv_listleads);
        dataHelper = new DataHelper(this);

        List<Lead> listLeads = dataHelper.GetAllLeads();
        

        LeadsAdapter leadsadapter = new LeadsAdapter(this, listLeads);
        List.setAdapter(leadsadapter);


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