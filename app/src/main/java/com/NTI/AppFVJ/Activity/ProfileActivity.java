package com.NTI.AppFVJ.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Fragment.Fragment1;
import com.NTI.AppFVJ.Fragment.Fragment2;
import com.NTI.AppFVJ.Models.Comments;
import com.NTI.AppFVJ.Models.Leads;
import com.NTI.AppFVJ.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.NTI.AppFVJ.ui.main.SectionsPagerAdapter;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}