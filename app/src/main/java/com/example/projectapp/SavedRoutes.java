package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SavedRoutes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_saved_routes);

        ImageButton homeBtn = findViewById(R.id.homeBtnShowTransports7);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });

        ImageButton returnBtn = findViewById(R.id.returnBtn7);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //load
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString(RouteAllDetails.TAG, "");
        Type type = new TypeToken<ArrayList<Route>>() {}.getType();
        ArrayList<Route> arrayList = gson.fromJson(json, type);
        Route.savedRoutes = arrayList;

        if (Route.savedRoutes == null) Route.savedRoutes = new ArrayList<Route>();

        MainActivity.control = false;
        RouteAdapter adapter = new RouteAdapter(this, Route.savedRoutes);
        ListView listView = (ListView) findViewById(R.id.listviewroutes);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}