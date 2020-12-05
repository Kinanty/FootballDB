package com.example.footballdb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {
    TextView tvNoData;
    ProgressDialog progressdialog;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<Model> DataArrayList; //kit add kan ke adapter
    ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        progressdialog = new ProgressDialog(ListData.this);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        tvNoData = (TextView) findViewById(R.id.tvNoData);
        tvNoData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //addData();
        addDataOnline();
    }

//    void addData() {
//        //offline, isi data offline dulu
//        DataArrayList = new ArrayList<>();
//        Model data1 = new Model();
//        data1.setIdTeam("133604");
//        data1.setStrTeam("Arsenal");
//        data1.setStrTeamLogo("https:\\/\\/www.thesportsdb.com\\/images\\/media\\/team\\/logo\\/q2mxlz1512644512.png");
//        data1.setIntFormedYear(1892);
//        DataArrayList.add(data1);
//
//
//        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
//            @Override
//            public void onClick(int position) {
//
//            }
//
//            @Override
//            public void test() {
//
//            }
//        });
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//
//        //get data online
//
//
//    }

    void addDataOnline() {
        progressdialog.setMessage("Loading...");
        progressdialog.show();
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setIdTeam(jsonObject.getInt("idTeam"));
                                modelku.setStrTeam(jsonObject.getString("strTeam"));
                                modelku.setStrTeamLogo(jsonObject.getString("strTeamBadge"));
                                modelku.setIntFormedYear(jsonObject.getInt("intFormedYear"));
                                modelku.setStrDescriptionEN(jsonObject.getString("strDescriptionEN"));
                                modelku.setStrCountry(jsonObject.getString("strCountry"));
                                modelku.setStrStadium(jsonObject.getString("strStadium"));


                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model football = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), DetailMovie.class);
                                    intent.putExtra("id", football.idTeam);
                                    intent.putExtra("judul", football.strTeam);
                                    intent.putExtra("path", football.strTeamLogo);
                                    intent.putExtra("description", football.strDescriptionEN);
                                    intent.putExtra("date", football.intFormedYear);
                                    intent.putExtra("country", football.strCountry);
                                    intent.putExtra("stadium", football.strStadium);

                                    startActivity(intent);
                                    Toast.makeText(ListData.this, "" + position, Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (progressdialog.isShowing()) {
                                progressdialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (progressdialog.isShowing()) {
                                progressdialog.dismiss();
                            }
                        }


                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}