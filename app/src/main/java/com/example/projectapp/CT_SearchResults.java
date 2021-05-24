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
public class CT_SearchResults extends AppCompatActivity {


    //temos de receber os filtros selecionados a partir do CT_LocationFilters

    // arrays/ficheiros necessários
    static ArrayList<Trip> list_of_trips = new ArrayList<Trip>();  //contem todas as viagens


    //ler um ficheiro e adicionar objetos Trip para list_of_trips
    /*
    Scanner scanner = new Scanner(new File("trips.txt"));

    while (scanner.hasNextLine()) {
        String line = sc.nextLine();
        String[] trip_info = line.split("/t");

        String origin = trip_info[0];
        String destiny = trip_info[1];
        LocalTime departure_time = LocalTime.parse(trip_info[2]);
        LocalTime arrival_time = LocalTime.parse(trip_info[3]);
        String transport_type = trip_info[4];
        Double price = Double.parseDouble(trip_info[5]);
        long travelling_time = ChronoUnit.MINUTES.between(l1,l2);

        String departure_date_string = trip_info[6]; //pode ter data (Date) ou ser diário (DAILY)
        Date departure_date = null;
        String departure_routine = null;
        try {
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
            departure_date = df.parse(departure_date_string);
        } catch (Exception e) {
            departure_routine = departure_date_string;
        }

        String place_of_departure = trip_info[7];
        String place_of_arrival = trip_info[8];

        //ponto de origem / ponto de destino / hora saída / hora chegada / tipo transporte / preço / data de saída (se for DAILY repete-se todos os dias) / coordenadas do ponto de saida / coordenadas do ponto de chegada
        Trip trip;
        if (departure_date == null) {
            //caso seja uma trip que seja rotineira
            trip = new Trip(origin,destiny,departure_time,arrival_time,transport_type,price,travelling_time,departure_routine,place_of_departure,place_of_arrival);
        } else {
            //caso só aconteça uma vez
            trip = new Trip(origin,destiny,departure_time,arrival_time,transport_type,price,travelling_time,departure_date,place_of_departure,place_of_arrival);
        }
        list_of_trips.add(trip);
    }
    scanner.close();

    for (Trip t : list_of_trips) {
        System.out.println(t);
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_search_results);
    }
}