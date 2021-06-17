package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class RouteAllDetails extends AppCompatActivity {

    public static final String TAG = "route";
    public static String json;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
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

        Route.savedRoutes.add(route);

        //save
        SharedPreferences sharedP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedP.edit();
        Gson gsonn = new Gson();
        String jsonstr = gsonn.toJson(Route.savedRoutes);
        editor.putString(TAG, jsonstr);
        editor.commit();


        TripAdapter adapter = new TripAdapter(this, CT_SearchResults.choosen_trips);
        ListView listView = (ListView) findViewById(R.id.listviewtrips);
        listView.setAdapter(adapter);

        EditText routeName = findViewById(R.id.routeName);
        routeName.setText(route.getTitle());
        routeName.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String currentTitle = routeName.getText().toString();
                boolean validName = true;
                for (Route r : Route.savedRoutes) {
                    if (r.getTitle() == s.toString()) validName = false;
                }
                if (validName) {
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(),"Please insert a route name", Toast.LENGTH_SHORT).show();
                        routeName.setText(currentTitle);
                    } else {
                        Toast.makeText(getApplicationContext(),"Changed route name successfully", Toast.LENGTH_SHORT).show();
                        route.setTitle(s.toString());
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"There's already a route with that title", Toast.LENGTH_SHORT).show();
                    routeName.setText(currentTitle);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


        TextView totalTravellingTime = findViewById(R.id.totalTravellingTime);
        totalTravellingTime.setText(route.getTotal_travelling_time() + " minutes");
        TextView totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText(route.getTotal_price().toString() + "€");

        Button deleteRoute = findViewById(R.id.deleteRouteBtn);
        deleteRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route.savedRoutes.remove(route);
                Toast.makeText(getApplicationContext(),"Route deleted successfully", Toast.LENGTH_SHORT).show();
                Intent gotosavedroutes = new Intent(getApplicationContext(), SavedRoutes.class);
                startActivity(gotosavedroutes);
            }
        });

    }
}