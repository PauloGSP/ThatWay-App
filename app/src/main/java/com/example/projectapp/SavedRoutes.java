package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SavedRoutes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_saved_routes);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString(RouteAllDetails.TAG, "");
        Type type = new TypeToken<ArrayList<Route>>() {}.getType();
        ArrayList<Route> arrayList = gson.fromJson(json, type);
        Route.savedRoutes = arrayList;

        System.out.println("SAVEDROUTES!!!");
        for (Route r : arrayList) System.out.println(r.getTitle());

        RouteAdapter adapter = new RouteAdapter(this, arrayList);
        ListView listView = (ListView) findViewById(R.id.listviewroutes);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}