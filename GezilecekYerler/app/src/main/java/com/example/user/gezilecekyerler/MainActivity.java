package com.example.user.gezilecekyerler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.gezilecekyerler.models.Categories;
import com.example.user.gezilecekyerler.models.Category;

import java.util.UUID;

public class MainActivity extends Activity {


    String[] urls = {"park-bahce-istanbul","mesire-yerleri-istanbul","adalar","muzeler-istanbul","carsi-pazar-istanbul","lunaparklar","camiler-turbeler-istanbul","plaj-yerleri-istanbul", "havuzlar-istanbul","cafe-restaurant-istanbul","sosyal-tesisleri","otel-pansiyon-istanbul"};
    String[] category_names = {"Park Bahce","Mesire Yerleri","Adalar","Muzeler","Carsi Pazar","Lunaparklar","Camiler Turbeler","Plaj Yerleri","Havuzlar","Cafe Restaurant","Sosyal Tesisler","Otel Pansiyon"};
    ViewGroup categories_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Categories categories = new Categories();

      //  if (SharedPreferencesUtil.isFirstRun(MainActivity.this)){//sadece ilk �al��ma an�nda olacak olanlar

            for (int i = 0; i < urls.length; i++) {

                Category cat = new Category();
                cat.setName(category_names[i]);
                cat.setUrl("http://www.gezilmesigerekenyerler.com/category/" + urls[i] + "/feed");
                cat.setId(UUID.randomUUID());
                categories.add(cat);
            }
            SharedPreferencesUtil.setCategories(MainActivity.this, categories);//Categories i sharedPreference kaydettik
       // }

        categories = SharedPreferencesUtil.getCategories(MainActivity.this);//SharedPreference ye kaydettigimiz veriyi categories e aktard�k

        categories_lay = (ViewGroup) findViewById(R.id.categories_lay);
        categories_lay.removeAllViews();
        int[] cat_colors = getResources().getIntArray(R.array.category_colors);

        for (int i = 0; i < categories.size(); i++) {

            addCategoryToLayout(cat_colors[i], categories.get(i));
        }
    }


    private void addCategoryToLayout(int color, Category cat) {

        View v = getLayoutInflater().inflate(R.layout.item_category, null);
        View layout = v.findViewById(R.id.categoryback);
        TextView title = (TextView) v.findViewById(R.id.category_name);
        layout.setBackgroundColor(color);
        title.setText(cat.getName());
        v.setTag(cat);
        v.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Category cat = (Category) v.getTag();
                Bundle bnd = new Bundle();

                Intent intent = new Intent(MainActivity.this, Sehirler.class);

                bnd.putString("name", cat.getName());
                bnd.putString("url", cat.getUrl());
                bnd.putString("id", cat.getId().toString());

                intent.putExtras(bnd);
                startActivity(intent);
            }
        });

        categories_lay.addView(v);

    }
}
