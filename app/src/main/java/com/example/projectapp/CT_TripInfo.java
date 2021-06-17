package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CT_TripInfo extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_trip_info);

        ImageButton returnBtn = findViewById(R.id.returnBtn5);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView transportIcon = findViewById(R.id.transportIcon);
        TextView transport = findViewById(R.id.transportType);
        TextView origin = findViewById(R.id.origin);
        TextView destiny = findViewById(R.id.destiny);
        TextView depTime = findViewById(R.id.depTime);
        TextView arrTime = findViewById(R.id.arrTime);
        TextView travellingTime = findViewById(R.id.travellingTime);
        TextView ticketPrice = findViewById(R.id.ticketPrice);
        TextView placeofdeparture = findViewById(R.id.placeofdeparture);
        TextView placeofarrival = findViewById(R.id.placeofarrival);

        Trip trip = CT_SearchResults.currentTrip;
        MapSelectLocation.trip = trip;
        MapSelectLocation.isDeparture= false;

        transport.setText(trip.getTransport_type());
        origin.setText(trip.getOrigin_address() + " (" + trip.getOrigin() + ")");
        destiny.setText(trip.getDestiny_address() + " (" + trip.getDestiny() + ")");
        depTime.setText(trip.getDeparture_time().toString());
        arrTime.setText(trip.getArrival_time().toString());
        travellingTime.setText(trip.getTravelling_time() + " minutes");
        DecimalFormat df2 = new DecimalFormat("#.##");
        ticketPrice.setText(df2.format(trip.getPrice()) + "€");
        placeofdeparture.setText(trip.getOrigin_address());
        placeofarrival.setText(trip.getDestiny_address());

        //ir para o mapa com as coordenadas

        ImageButton depMap = findViewById(R.id.btnDeparture);
        depMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapSelectLocation.isDeparture = true;
                Intent goMap = new Intent(getApplicationContext(), MapSelectLocation.class);
                startActivity(goMap);
            }
        });

        ImageButton arrMap = findViewById(R.id.btnArrival);
        arrMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapSelectLocation.isDeparture = false;
                Intent goMap = new Intent(getApplicationContext(), MapSelectLocation.class);
                startActivity(goMap);
            }
        });
        //fazer set do icone de transporte


    }
}