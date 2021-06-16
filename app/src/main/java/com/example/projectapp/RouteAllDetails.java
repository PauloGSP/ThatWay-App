package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RouteAllDetails extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_route_all_details);

        Route route = Route.currentRoute;

        Route.storeRoutes();

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

    }
}