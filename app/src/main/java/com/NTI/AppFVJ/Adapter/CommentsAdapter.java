package com.NTI.AppFVJ.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.NTI.AppFVJ.Models.Comments;
import com.NTI.AppFVJ.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CommentsAdapter extends ArrayAdapter<Comments> {


    public CommentsAdapter(@NonNull Context context, @NonNull List<Comments> objects) {
        super(context, R.layout.adapter_comments_list, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View RowView = convertView;

        if (RowView == null)
            RowView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_comments_list, parent, false);

        Comments comments = getItem(position);

        TextView Nome, Data, Comentario;

        Nome = RowView.findViewById(R.id.et_nome);
        Data = RowView.findViewById(R.id.et_data);
        Comentario = RowView.findViewById(R.id.et_comentario);

        Nome.setText(comments.getText());
        Data.setText(comments.getText());
        Comentario.setText(comments.getText());

        return RowView;
    }
}
