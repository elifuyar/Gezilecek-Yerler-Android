package com.example.user.gezilecekyerler.pages;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.user.projefe.GaleriAdapter;
import com.example.user.projefe.MainActivity;
import com.example.user.projefe.R;


public class GaleriFragment extends Activity {

    Activity titleChange;

    public GaleriFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        titleChange.setTitle("Galeri");
        final View viewUser = inflater.inflate(R.layout.fragment_galeri, container, false);

        GridView grid = (GridView) viewUser.findViewById(R.id.gridView1);
        grid.setAdapter(new GaleriAdapter(titleChange.getApplicationContext()));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Toast.makeText(titleChange.getBaseContext(),
                        "Resim " + (position + 1) + " secildi",
                        Toast.LENGTH_SHORT).show();

            }
        });

        return viewUser;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        titleChange = (MainActivity) activity;
    }

}
