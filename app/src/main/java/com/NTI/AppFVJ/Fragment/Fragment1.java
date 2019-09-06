package com.NTI.AppFVJ.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.NTI.AppFVJ.Activity.MainActivity;
import com.NTI.AppFVJ.Activity.ProfileActivity;
import com.NTI.AppFVJ.R;

public class Fragment1 extends Fragment {
    private Spinner sp_curso;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment1, container, false);

    }
}
