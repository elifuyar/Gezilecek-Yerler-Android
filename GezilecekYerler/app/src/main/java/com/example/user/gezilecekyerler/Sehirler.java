package com.example.user.gezilecekyerler;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.gezilecekyerler.models.Category;
import com.example.user.gezilecekyerler.models.News;
import com.example.user.gezilecekyerler.models.Newses;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.net.URL;
import java.util.UUID;

public class Sehirler extends Activity {

    Bundle bnd;
    private Newses newsList;
    private Category category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehirler);

        bnd = new Bundle();

        Bundle alinan = getIntent().getExtras();
        String name = alinan.getString("name");
        String url = alinan.getString("url");
        String id = alinan.getString("id");

        category = new Category();


        category.setId(UUID.fromString(id));
        category.setName(name);
        category.setUrl(url);

        new GetNewsesTask().execute();

    }


    private class MyListAdapter extends ArrayAdapter<News> {
        public MyListAdapter(){
            super(Sehirler.this, R.layout.item_news, newsList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_news, parent, false);
            }

            News currentNews = newsList.get(position);

            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            ImageLoader.getInstance().displayImage(currentNews.getImage(), imageView);

          /*  ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            ImageLoader.getInstance().displayImage(currentNews.getImage(), imageView);*/


           /* ImageLoader imageLoader = ImageLoader.getInstance();
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            String imageUrl = currentNews.getImage();
            imageLoader.displayImage(imageUrl, imageView);*/

            TextView isimText = (TextView)itemView.findViewById(R.id.name_textView);
            isimText.setText(currentNews.getTitle());

            return itemView;
        }
    }




    private class GetNewsesTask extends AsyncTask<URL, Integer, Newses> {
        protected Newses doInBackground(URL... urls) {

            newsList = Request.getNews(category, SharedPreferencesUtil.getNewses(Sehirler.this, category.getId().toString()));

            SharedPreferencesUtil.setNewses(Sehirler.this, newsList, category.getId().toString()); //newses � SharedPreferences a kaydettik
            return newsList;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Newses result) {
            newsList = result;

            ArrayAdapter<News> adapter = new MyListAdapter();
            final ListView list = (ListView)findViewById(R.id.listView);
            list.setAdapter(adapter);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Sehirler.this, Detay.class);
                    bnd.putString("image", newsList.get(position).getImage());
                    bnd.putString("title", newsList.get(position).getTitle());
                    bnd.putString("desc", newsList.get(position).getDescription());
                    bnd.putString("pudate", newsList.get(position).getDate());
                    bnd.putString("content", newsList.get(position).getContent());
                    intent.putExtras(bnd);
                    startActivity(intent);
                }
            });
        }
    }
}
