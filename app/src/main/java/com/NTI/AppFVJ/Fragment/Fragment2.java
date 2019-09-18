package com.NTI.AppFVJ.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.NTI.AppFVJ.Activity.ProfileActivity;
import com.NTI.AppFVJ.Adapter.CommentsAdapter;
import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.R;

import java.util.List;

public class Fragment2 extends Fragment {
    private View view;

    private int id;

    private DataHelper datahelper;

    private List<Comment> commentsList;
    private ListView lv_comentario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        lv_comentario = view.findViewById(R.id.lv_comentarios);

        id = Integer.parseInt(ProfileActivity.getId());

        final EditText et_comment;
        et_comment = view.findViewById(R.id.et_comment);

        et_comment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (et_comment.getRight() - et_comment.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }

    private void DataComments() {
        commentsList = datahelper.GetByIdComments(id);
        CommentsAdapter adapter = new CommentsAdapter(view.getContext(), commentsList);
        lv_comentario.setAdapter(adapter);
    }
}
