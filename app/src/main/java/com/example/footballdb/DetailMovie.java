package com.example.footballdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class DetailMovie extends AppCompatActivity {
    Bundle extras;
    String title;
    String poster;
    String desk;
    int tahunberdiri;
    String negara;
    String stadion;

    TextView tvJudul;
    ImageView ivPoster;
    TextView tvDeskripsi;
    TextView tvFormedYear;
    TextView tvCountry;
    TextView tvStadium;
    Button btnFavourite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        extras = getIntent().getExtras();
        tvJudul = (TextView)findViewById(R.id.tvJudul);
        tvDeskripsi =(TextView)findViewById(R.id.tvDeskripsi);
        tvFormedYear =(TextView)findViewById(R.id.tvFormedYear);
        tvCountry =(TextView)findViewById(R.id.tvCountry);
        ivPoster = (ImageView) findViewById(R.id.ivPoster);
        tvStadium =(TextView)findViewById(R.id.tvStadium);
        btnFavourite = (Button) findViewById(R.id.btnFavourite);

        if (extras != null) {
            title = extras.getString("judul");
            poster = extras.getString("path");
            desk = extras.getString("description");
            tahunberdiri = extras.getInt("date");
            negara = extras.getString("country");
            stadion = extras.getString("stadium");
            tvJudul.setText(title);
            tvDeskripsi.setText(desk);
            tvFormedYear.setText(""+tahunberdiri);
            tvCountry.setText(negara);
            tvStadium.setText(stadion);
            Glide.with(DetailMovie.this)
                    .load(poster)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivPoster);
            // and get whatever type user account id is
        }
    }

}
