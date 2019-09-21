package com.NTI.AppFVJ.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.NTI.AppFVJ.Activity.MainActivity;
import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.User;
import com.NTI.AppFVJ.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CommentsAdapter extends ArrayAdapter<Comment> {
    private DataHelper dataHelper;
    private List<User> userList;

    public CommentsAdapter(@NonNull Context context, @NonNull List<Comment> objects) {
        super(context, R.layout.adapter_comments_list, objects);
        dataHelper = new DataHelper(getContext());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View RowView = convertView;

        if (RowView == null)
            RowView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_comments_list, parent, false);

        Comment comments = getItem(position);

        TextView Nome, Data, Comentario,Id;

        Id  = RowView.findViewById(R.id.tv_id);
        Nome = RowView.findViewById(R.id.et_nome);
        Comentario = RowView.findViewById(R.id.et_comentario);

        userList = dataHelper.GetByIdUsers(comments.getUsers_Id());

        Id.setText(String.valueOf(comments.getId()));

        for (User user : userList) {
            String[] name = MaskEditUtil.returnOnlyName(user.getName());
            Nome.setText(name[0]+" "+name[1]);
        }

        Comentario.setText(comments.getText());

        return RowView;
    }
}
