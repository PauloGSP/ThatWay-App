package com.example.projectapp;

import androidx.annotation.RequiresApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class RouteAllDetails extends Activity {

    public static final String TAG = "all_routes";

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_route_all_details);

        ImageButton returnBtn = findViewById(R.id.returnBtn8);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageButton homeBtn = findViewById(R.id.homeBtnShowTransports8);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });


        Route route = Route.currentRoute;

        //load
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString(RouteAllDetails.TAG, "");
        Type type = new TypeToken<ArrayList<Route>>() {}.getType();
        ArrayList<Route> arrayList = gson.fromJson(json, type);
        Route.savedRoutes = arrayList;

        if (Route.savedRoutes == null) Route.savedRoutes = new ArrayList<Route>();
        if (MainActivity.control) Route.savedRoutes.add(route);

        //save
        SharedPreferences sharedP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedP.edit();
        Gson gsonn = new Gson();
        String jsonstr = gsonn.toJson(Route.savedRoutes);
        editor.putString(TAG, jsonstr);
        editor.clear();
        editor.apply();

        if (MainActivity.control) {
            TripAdapter adapter = new TripAdapter(this, CT_SearchResults.choosen_trips);
            ListView listView = (ListView) findViewById(R.id.listviewtrips);
            listView.setAdapter(adapter);
        } else {
            TripAdapter adapter = new TripAdapter(this, route.getList_of_trips());
            ListView listView = (ListView) findViewById(R.id.listviewtrips);
            listView.setAdapter(adapter);
        }

        TextView routeName = (TextView) findViewById(R.id.routeName);
        routeName.setText(route.getTitle());

        TextView totalTravellingTime = findViewById(R.id.totalTravellingTime);
        totalTravellingTime.setText(route.getTotal_travelling_time() + " minutes");
        TextView totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText(route.getTotal_price().toString() + "€");


        Button deleteRoute = findViewById(R.id.deleteRouteBtn);
        deleteRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //load
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Gson gson = new Gson();
                String json = sharedPrefs.getString(RouteAllDetails.TAG, "");
                Type type = new TypeToken<ArrayList<Route>>() {}.getType();
                ArrayList<Route> arrayList = gson.fromJson(json, type);
                Route.savedRoutes = arrayList;

                Route.savedRoutes.remove(route);

                //save
                SharedPreferences sharedP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedP.edit();
                Gson gsonn = new Gson();
                String jsonstr = gsonn.toJson(Route.savedRoutes);
                editor.putString(TAG, jsonstr);
                editor.clear();
                editor.apply();

                Toast.makeText(getApplicationContext(),"Route deleted successfully", Toast.LENGTH_SHORT).show();
                Intent gotosavedroutes = new Intent(getApplicationContext(), SavedRoutes.class);
                startActivity(gotosavedroutes);
            }
        });

    }
}