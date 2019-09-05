package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.NTI.AppFVJ.Adapters.LeadsAdapter;
import com.NTI.AppFVJ.Models.Leads;
import com.NTI.AppFVJ.Models.Users;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView LeadsListView = findViewById(R.id.LeadsList);

        List<Leads> LeadsList = new ArrayList<Leads>();
/*
        for (int Lead = 0; Lead < 10; Lead++)
            LeadsList.add(new Leads());
*/
        LeadsAdapter Adapter = new LeadsAdapter(this, LeadsList);
        LeadsListView.setAdapter(Adapter);
    }
}
