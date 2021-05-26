package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CT_SearchResults<pp> extends AppCompatActivity {

    private String[] trips;
    private String[] trip_info;
    private ArrayList<Trip> list_of_trips;
    Trip trip;

    private String origin;
    private String destiny;
    private String origin_address;
    private String destiny_address;
    private LocalTime departure_time;
    private LocalTime arrival_time;
    private String transport_type;
    private Double price;
    private String routine;
    private String origin_coords;
    private String destiny_coords;
    private long travelling_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_search_results);

        trips = new String[]{"aveiro estarreja estacao_aveiro estacao_estarreja 14:00 14:15 TRAIN 1.45 DAILY 40.64341386036852,-8.6408032773646 40.7512086333165,-8.575193730819068",
                "aveiro estarreja	aveiro_avenida_25_abril	estarreja_rua_caetano_ferreira	14:30	14:35	BUS 2.00	DAILY	40.6363056,-8.6492221	40.7569636,-8.5684835",
                "aveiro  estarreja	estacao_aveiro	estacao_estarreja	15:50	16:03	TRAIN	1.45	DAILY	40.64341386036852, -8.6408032773646	40.7512086333165, -8.575193730819068"
        };

        list_of_trips = new ArrayList<Trip>();

        for (String t : trips)

            trip_info = t.split("\\s");

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
            travelling_time = ChronoUnit.MINUTES.between(departure_time,arrival_time);

            trip = new Trip(origin,destiny,origin_address,destiny_address,
                    departure_time,arrival_time,transport_type,price,routine,
                    origin_coords,destiny_coords,travelling_time);

            list_of_trips.add(trip);

    }
}