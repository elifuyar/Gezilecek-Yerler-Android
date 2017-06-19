package com.example.user.gezilecekyerler.pages;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.user.projefe.ImageAdapter;
import com.example.user.projefe.MainActivity;
import com.example.user.projefe.R;
import com.example.user.projefe.SharedPreferencesUtil;
import com.example.user.projefe.Urunler;
import com.example.user.projefe.models.Categories;

import java.util.UUID;


public class UrunlerFragment extends Fragment{


    Activity titleChange;

    public UrunlerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        titleChange.setTitle("Ürünler");
        final View viewUser = inflater.inflate(R.layout.fragment_urunler, container, false);

        Categories categories = new Categories();


        if (SharedPreferencesUtil.isFirstRun(UrunlerFragment.this)){//sadece ilk �al��ma an�nda olacak olanlar

            for (int i = 0; i < urls.length; i++) {

                Category cat = new Category();
                cat.setName(category_names[i]);
                cat.setUrl("http://www.ensonhaber.com/rss/" + urls[i] + ".xml");
                cat.setId(UUID.randomUUID());
                categories.add(cat);
            }
            SharedPreferencesUtil.setCategories(MainActivity.this, categories);//Categories i sharedPreference kaydettik
        }

        categories = SharedPreferencesUtil.getCategories(MainActivity.this);//SharedPreference ye kaydettigimiz veriyi categories e aktard�k

        categories_lay = (ViewGroup) findViewById(R.id.categories_lay);
        categories_lay.removeAllViews();

        GridView grid;
        String[] web = {
                "facebook",
                "twitter",
                "google",
                "instagram"
        } ;
        int[] imageId = {
                R.drawable.facebook,
                R.drawable.twitter,
                R.drawable.google,
                R.drawable.instagram
        };


        ImageAdapter adapter = new ImageAdapter(titleChange.getBaseContext(), web, imageId);
        grid=(GridView)viewUser.findViewById(R.id.gridView1);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(titleChange.getBaseContext(), "Tiklandi!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(titleChange.getBaseContext(), Urunler.class);
                startActivity(intent);
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
