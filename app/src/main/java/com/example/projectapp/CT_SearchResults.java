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

    //filtros e cenas introduzidas pelo user

    public static String origem;
    public static String paragem;
    public static String destino;
    public static String dataSaida;
    public static String horaSaida;
    public static String horaChegada;
    public static boolean bus;
    public static boolean train;
    public static boolean metro;
    public static String order;

    //para leitura no scanner
    private String origin;
    private String destiny;
    private String origin_address;
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

        System.out.println("RESULTADOS:");
        System.out.println(origem);
        System.out.println(paragem);
        System.out.println(destino);
        System.out.println(dataSaida);
        System.out.println(horaSaida);
        System.out.println(horaChegada);
        System.out.println(bus);
        System.out.println(train);
        System.out.println(metro);
        System.out.println(order);

    }
}