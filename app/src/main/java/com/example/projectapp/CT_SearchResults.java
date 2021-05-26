package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CT_SearchResults<pp> extends AppCompatActivity {

    private String[] trip_info;
    private ArrayList<Trip> list_of_trips;
    Trip trip;
    Scanner sc;
    Intent intent;

    private String origin;
    private String breakpoint;
    private String destiny;
    private String origin_address;
    private String breakpoint_address;
    private String destiny_address;
    private String departure_date;
    private LocalTime departure_time;
    private LocalTime arrival_time;
    private String transport_type;
    private Double price;
    private String routine;
    private String origin_coords;
    private String destiny_coords;
    private long travelling_time;

    private boolean bus;
    private boolean train;
    private boolean metro;
    private String order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_search_results);

        list_of_trips = new ArrayList<Trip>();

        try {

            sc = new Scanner(new File("trips.txt"));

            while (sc.hasNextLine()) {

                trip_info = sc.nextLine().split("\\s");

                origin = trip_info[0];
                destiny = trip_info[1];
                origin_address = trip_info[2];
                destiny_address = trip_info[3];
                departure_time = LocalTime.parse(trip_info[4]);
                arrival_time = LocalTime.parse(trip_info[5]);
                transport_type = trip_info[6];
                price = Double.parseDouble(trip_info[7]);
                routine = trip_info[8];
                origin_coords = trip_info[9];
                destiny_coords = trip_info[10];
                travelling_time = ChronoUnit.MINUTES.between(departure_time, arrival_time);

                trip = new Trip(origin, destiny, origin_address, destiny_address,
                        departure_time, arrival_time, transport_type, price, routine,
                        origin_coords, destiny_coords, travelling_time);

                list_of_trips.add(trip);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        intent = getIntent();
        origin = intent.getStringExtra("location1");
        destiny = intent.getStringExtra("location2");
        breakpoint = intent.getStringExtra("breakpoint");
        departure_date = intent.getStringExtra("depDate");
        departure_time = LocalTime.parse(intent.getStringExtra("depTime"));
        arrival_time = LocalTime.parse(intent.getStringExtra("arrTime"));
        bus = intent.getBooleanExtra("bus",true);
        train = intent.getBooleanExtra("train",true);
        metro = intent.getBooleanExtra("metro",true);
        order = intent.getStringExtra("order");

        System.out.println("RESULTADOS:");
        System.out.println(origin);
        System.out.println(breakpoint);
        System.out.println(destiny);
        System.out.println(departure_date);
        System.out.println(departure_time);
        System.out.println(arrival_time);
        System.out.println(bus);
        System.out.println(train);
        System.out.println(metro);
        System.out.println(order);

    }
}