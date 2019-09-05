package com.NTI.AppFVJ.Adapters;

import com.NTI.AppFVJ.Models.Leads;
import com.NTI.AppFVJ.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        View RowView = convertView;

        if (RowView == null)
            RowView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_leads_list, parent, false);

        Leads Leads = getItem(position);

        TextView Name, Town;

        Name = RowView.findViewById(R.id.Name);
        Town = RowView.findViewById(R.id.Town);

        Name.setText(Leads.getName());
        Town.setText(Leads.getTown());

        return RowView;
    }
}
