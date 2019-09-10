package com.NTI.AppFVJ.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.NTI.AppFVJ.Activity.ProfileActivity;
import com.NTI.AppFVJ.Adapters.CommentsAdapter;
import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.Models.Comments;
import com.NTI.AppFVJ.R;

import java.util.List;

public class Fragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment2, container, false);

    }
}
