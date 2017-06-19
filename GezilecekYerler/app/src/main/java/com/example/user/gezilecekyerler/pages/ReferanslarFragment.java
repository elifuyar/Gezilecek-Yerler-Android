package com.example.user.gezilecekyerler.pages;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.projefe.MainActivity;
import com.example.user.projefe.R;


public class ReferanslarFragment extends Fragment {

    Activity titleChange;

    public ReferanslarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        titleChange.setTitle("Referanslar");
        return inflater.inflate(R.layout.fragment_referanslar, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        titleChange = (MainActivity) activity;
    }
}
