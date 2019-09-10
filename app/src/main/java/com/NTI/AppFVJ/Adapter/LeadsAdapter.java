package com.NTI.AppFVJ.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.NTI.AppFVJ.Models.Leads;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LeadsAdapter extends ArrayAdapter<Leads> {
    public LeadsAdapter(@NonNull Context context, @NonNull List<Leads> objects) {
        super(context, R.layout.adapter_leads_list, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null)
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_leads_list, parent, false);

        TextView Name, Town;

        Name = rowView.findViewById(R.id.Name);
        Town = rowView.findViewById(R.id.Town);

        Leads Leads = getItem(position);

        Name.setText(Leads.getName());
        Town.setText(Leads.getTown());

        return rowView;
    }

}
