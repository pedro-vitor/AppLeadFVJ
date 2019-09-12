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

        View rowView = convertView;

        if (rowView == null)
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_leads_list, parent, false);

        TextView Name, Town, Address;

        Name = rowView.findViewById(R.id.Name);
        Town = rowView.findViewById(R.id.Town);
        Address = rowView.findViewById(R.id.Address);

        Lead Lead = getItem(position);

        Name.setText(Lead.getName());
        Town.setText(Lead.getTown());
        Address.setText(Lead.getAddress());

        return rowView;
    }

}
