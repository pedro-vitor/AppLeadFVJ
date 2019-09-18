package com.NTI.AppFVJ.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.NTI.AppFVJ.R;


public class Fragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);

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
}
