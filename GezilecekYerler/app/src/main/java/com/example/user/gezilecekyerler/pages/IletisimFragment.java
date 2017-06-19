package com.example.user.gezilecekyerler.pages;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.projefe.MainActivity;
import com.example.user.projefe.R;


public class IletisimFragment extends Fragment {

    Activity titleChange;
    Button harita, arama, mail;

    public IletisimFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        titleChange.setTitle("Iletisim");
        final View viewUser = inflater.inflate(R.layout.fragment_iletisim, container, false);

        harita = (Button)viewUser.findViewById(R.id.buton_harita);
        arama = (Button)viewUser.findViewById(R.id.buton_telefon);
        mail = (Button)viewUser.findViewById(R.id.buton_gmail);

       harita.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // adrese göre harita noktası
              // Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
// ya da enlem ve boylam yerine göre konum bilgisi
                Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z parametresi yaklaşma seviyesini belirler
               Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
               startActivity(mapIntent);
           }
       });

        arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:05387940043");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent = new Intent(Intent.ACTION_SEND);
              intent.setType("text/html");
              intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "uyarrelif@gmail.com", "fahri4297@gmail.com" });
              intent.putExtra(Intent.EXTRA_SUBJECT, "Android");
              intent.putExtra(Intent.EXTRA_TEXT, "Hiiiiiiiiiiiiiiiii");
              startActivity(Intent.createChooser(intent, "Send Email"));

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
