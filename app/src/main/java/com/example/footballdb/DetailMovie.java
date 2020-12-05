package com.example.footballdb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailMovie extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ModelFootballRealm movieModel;

    Bundle extras;
    String title;
    String poster;
    String desk;
    int tahunberdiri;
    String negara;
    String stadion;
    String id;

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
            id = extras.getString("id");
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
        //Set up Realm
        Realm.init(DetailMovie.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieModel = new ModelFootballRealm();
                movieModel.setDesc(desk);
                movieModel.setJudul(title);
                movieModel.setPoster(poster);
                movieModel.setFormedYear(tahunberdiri);
                movieModel.setCountry(negara);
                movieModel.setStadium(stadion);


                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);

            }
        });
    }

}
