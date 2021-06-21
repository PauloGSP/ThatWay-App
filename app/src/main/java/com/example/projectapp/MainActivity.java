package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MainActivity extends Activity {

    public static boolean control;
    public static LastPage lastPage;
    public static String currentLocation;
    public static ArrayList<Trip> allTrips;
    public static ArrayList<String> allLocations;
    public static HashMap<String,ArrayList<String>> cityTransports;
    public static ArrayList<Ptransport> allPrivateTransports;

    // fazer load de todas as trips
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void loadPrivateTransport(){
        try{

            BufferedReader rd = new BufferedReader(new InputStreamReader(getAssets().open("priv.txt")));
            String l;
            while ((l = rd.readLine()) != null) {
                String[] transportinfo= l.split("/");
                String city=transportinfo[0];
                String type= transportinfo[1];
                String name=transportinfo[2];
                String contact= transportinfo[3];
                Ptransport ptransport = new Ptransport(city,type,name,contact);
                allPrivateTransports.add(ptransport);
            }
        }
        catch (IOException e) {

        e.printStackTrace();
    }
    }
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

            }


        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // load de todos os locais de saída/chegada
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loadAllLocations() {

        for (Trip t : allTrips) {
            String origin = t.getOrigin();
            String destiny = t.getDestiny();
            String origin_address = t.getOrigin_address();
            String destiny_address = t.getDestiny_address();
            String[] temp = new String[] {origin,destiny,origin_address,destiny_address};

            for (String str : temp) {
                if (!allLocations.stream().map(s -> s.toLowerCase()).collect(Collectors.toList()).contains(str.toLowerCase())) {
                    allLocations.add(str);
                }
            }
        }

    }

    public void loadcityTransports(){


        for (Trip t : allTrips) {
            String origin = t.getOrigin();
            String destiny = t.getDestiny();
            String transport = t.getTransport_type();
            if(!cityTransports.containsKey(origin.toLowerCase())){
                cityTransports.put(origin.toLowerCase(),new ArrayList<>(Arrays.asList(transport)));

            }else {
                ArrayList<String> transportes = new ArrayList(cityTransports.get(origin.toLowerCase()));
                if(!transportes.contains(transport)){
                    transportes.add(transport);
                    cityTransports.put(origin.toLowerCase(),transportes);
                }

            }
            if(!cityTransports.containsKey(destiny.toLowerCase())){
                cityTransports.put(destiny.toLowerCase(),new ArrayList<>(Arrays.asList(transport)));


            }else {
                ArrayList<String> transportes = new ArrayList(cityTransports.get(destiny.toLowerCase()));
                if (!transportes.contains(transport)) {
                    transportes.add(transport);
                    cityTransports.put(destiny.toLowerCase(), transportes);
                }
            }



        }
        for (Ptransport p : allPrivateTransports){

            String city= p.getCity();
            String type=p.getType();
            if(!cityTransports.containsKey(city.toLowerCase())){
                cityTransports.put(city.toLowerCase(),new ArrayList<>(Arrays.asList(type)));
            }else{
                ArrayList<String> transportes = new ArrayList(cityTransports.get(city.toLowerCase()));
                if (!transportes.contains(type)) {
                    transportes.add(type);

                    cityTransports.put(city.toLowerCase(), transportes);
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
        setContentView(R.layout.activity_main);

        lastPage = new LastPage(getApplicationContext());

        allTrips = new ArrayList<Trip>();
        allLocations = new ArrayList<String>();
        cityTransports= new HashMap<String, ArrayList<String>>();
        allPrivateTransports = new ArrayList<Ptransport>();

        CT_SearchResults.order = "DEPARTURE";
        control = false;
        Route.savedRoutes = new ArrayList<Route>();

        loadAllTrips();
        loadAllLocations();
        loadPrivateTransport();
        loadcityTransports();

        AutoCompleteTextView locationText = findViewById(R.id.locationTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,allLocations);
        locationText.setAdapter(adapter);

        //ir para o create trips
        Button createTripBtn = findViewById(R.id.createTripBtn);
        createTripBtn.setOnClickListener(v -> {
            currentLocation = locationText.getText().toString();
            Intent goToCreateTrip = new Intent(getApplicationContext(), CT_Locations.class);
            goToCreateTrip.putExtra("activity","MainActivity.class");
            startActivity(goToCreateTrip);
        });

        //ir para show transports
        Button showTransportsBtn = findViewById(R.id.showTransportsBtn);
        showTransportsBtn.setOnClickListener(v -> {
            currentLocation = locationText.getText().toString();
            if (!allLocations.stream().map(s -> s.toLowerCase()).collect(Collectors.toList()).contains(currentLocation.trim().toLowerCase())) {
                Toast.makeText(getApplicationContext(),"Invalid location", Toast.LENGTH_SHORT).show();
            } else {
                Intent goToShowTransports = new Intent(getApplicationContext(), ShowTransports.class);
                startActivity(goToShowTransports);
            }
        });

        //ir para saved routes
        Button savedRoutesBtn = findViewById(R.id.savedRoutesBtn);
        savedRoutesBtn.setOnClickListener(v -> {
            currentLocation = locationText.getText().toString();
            Intent goToSavedRoutes = new Intent(getApplicationContext(), SavedRoutes.class);
            startActivity(goToSavedRoutes);
        });
    }
}