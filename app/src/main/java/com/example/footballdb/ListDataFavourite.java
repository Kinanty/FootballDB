package com.example.footballdb;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListDataFavourite extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private DataAdapterFavourite adapter;
    private List<ModelFootballRealm> DataArrayList; //kit add kan ke adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        DataArrayList = realmHelper.getAllFootball();
        adapter = new DataAdapterFavourite(DataArrayList, new DataAdapterFavourite.Callback() {
            @Override
            public void onClick(int position) {
                //Intent ke detail movie
                Intent move = new Intent(getApplicationContext(), DetailFavourite.class);
                move.putExtra("id",DataArrayList.get(position).getId());
                move.putExtra("judul",DataArrayList.get(position).getJudul());
                move.putExtra("path",DataArrayList.get(position).getPoster());
                move.putExtra("description",DataArrayList.get(position).getDesc());
                move.putExtra("date",DataArrayList.get(position).getFormedYear());
                move.putExtra("country",DataArrayList.get(position).getCountry());
                move.putExtra("stadium",DataArrayList.get(position).getStadium());
                startActivity(move);
            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFavourite.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }


}
