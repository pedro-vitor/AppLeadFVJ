package com.NTI.AppFVJ.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LeadsAdapter extends ArrayAdapter<Lead> {
    public LeadsAdapter(@NonNull Context context, @NonNull List<Lead> objects) {
        super(context, R.layout.adapter_leads_list, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowview = convertView;

        if (rowview == null)
            rowview = LayoutInflater.from(getContext()).inflate(R.layout.adapter_leads_list, parent, false);

        TextView tv_id, tv_name, tv_town, tv_address;

        tv_id = rowview.findViewById(R.id.tv_id);
        tv_name = rowview.findViewById(R.id.tv_name);
        tv_town = rowview.findViewById(R.id.tv_town);
        tv_address = rowview.findViewById(R.id.tv_address);

        Lead Leads = getItem(position);

        tv_id.setText(Leads.getExternId()+"");
        tv_name.setText(Leads.getName());
        tv_town.setText(Leads.getTown());
        tv_address.setText(Leads.getAddress());

        return rowview;
    }
}
