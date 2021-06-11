package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public static String currentLocation;
    public static ArrayList<Trip> allTrips;
    public static ArrayList<String> allLocations;

    // fazer load de todas as trips
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void loadAllTrips() {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("trips.txt")));

            String line;
            while ((line = reader.readLine()) != null) {

                String[] trip_info = line.split("/");

                String origin = trip_info[0];
                String destiny = trip_info[1];
                String origin_address = trip_info[2];
                String destiny_address = trip_info[3];
                LocalTime departure_time = LocalTime.parse(trip_info[4]);
                LocalTime arrival_time = LocalTime.parse(trip_info[5]);
                String transport_type = trip_info[6];
                Double price = Double.parseDouble(trip_info[7]);
                String origin_coords = trip_info[8];
                String destiny_coords = trip_info[9];
                long travelling_time = ChronoUnit.MINUTES.between(departure_time, arrival_time);

                Trip trip = new Trip(origin, destiny, origin_address, destiny_address,
                        departure_time, arrival_time, transport_type, price,
                        origin_coords, destiny_coords, travelling_time);

                allTrips.add(trip);

                System.out.println(trip);

            }

            System.out.println("!!!!!!!!!!!!!!!added trips!!!!!!!!!!!!!!!!!");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // load de todos os locais de saída/chegada
    public void loadAllLocations() {

        System.out.println("loadAllLocations");

        for (Trip t : allTrips) {
            String origin = t.getOrigin();
            String destiny = t.getDestiny();
            String origin_address = t.getOrigin_address();
            String destiny_address = t.getDestiny_address();
            String[] temp = new String[] {origin,destiny,origin_address,destiny_address};

            for (String s : temp) {
                if (!allLocations.contains(s.toLowerCase())) {
                    allLocations.add(s.toLowerCase());
                    System.out.println(s.toLowerCase());
                }
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        allTrips = new ArrayList<Trip>();
        allLocations = new ArrayList<String>();

        System.out.println("yauuuu");

        loadAllTrips();
        loadAllLocations();

        EditText locationText = (EditText) findViewById(R.id.locationTextView);

        //ir para a página de mapa
        ImageButton locationBtn = findViewById(R.id.locationBtn);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLocation = locationText.getText().toString();
                Intent goToMaps = new Intent(getApplicationContext(), MapSelectLocation.class);
                startActivity(goToMaps);
            }
        });

        //ir para o create trips
        Button createTripBtn = findViewById(R.id.createTripBtn);
        createTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLocation = locationText.getText().toString();
                Intent goToCreateTrip = new Intent(getApplicationContext(), CT_Locations.class);
                startActivity(goToCreateTrip);
            }
        });

        //ir para show transports
        Button showTransportsBtn = findViewById(R.id.showTransportsBtn);
        showTransportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLocation = locationText.getText().toString();
                Intent goToShowTransports = new Intent(getApplicationContext(), ShowTransports.class);
                startActivity(goToShowTransports);
            }
        });

        //ir para saved routes
        Button savedRoutesBtn = findViewById(R.id.savedRoutesBtn);
        savedRoutesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLocation = locationText.getText().toString();
                Intent goToSavedRoutes = new Intent(getApplicationContext(), SavedRoutes.class);
                startActivity(goToSavedRoutes);
            }
        });
    }
}