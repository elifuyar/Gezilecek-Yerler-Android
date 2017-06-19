package com.example.user.gezilecekyerler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);


        Bundle alinan = getIntent().getExtras();
        String image = alinan.getString("image");
        String title = alinan.getString("title");
        String puDate = alinan.getString("pudate");
        String description = alinan.getString("desc");
        String content = alinan.getString("content");
        final String maps = (String) alinan.get("maps");

       /* if(!Validations.isEmpty(search)){

            if(description.toLowerCase().contains(search.toLowerCase()))

                description = description.replaceAll("(?i)("+search+")", "<font color='#d94338'>$1</font>");

            if(title.toLowerCase().contains(search.toLowerCase()))

                title = title.replaceAll("(?i)("+search+")", "<font color='#d94338'>$1</font>");

        }*/


       /* ImageView iconn = (ImageView)findViewById(R.id.image_view);
        ImageLoader.getInstance().displayImage(image, iconn);*/

        TextView titlee = (TextView)findViewById(R.id.baslik_textView);
        titlee.setText(title);

        TextView  datee = (TextView)findViewById(R.id.tarih_textView);
        datee.setText(puDate);

        TextView  desc = (TextView)findViewById(R.id.detay_textView);
        desc.setText(description);


        Button map = (Button)findViewById(R.id.harita_button);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri;
                Intent intent;

                uri = Uri.parse(maps);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });


     /*   ImageView iconn = (ImageView)findViewById(R.id.image_view);
        ImageLoader.getInstance().displayImage(content, iconn);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
