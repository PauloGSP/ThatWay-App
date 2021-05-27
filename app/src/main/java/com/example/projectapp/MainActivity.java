package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public static String currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

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
                Intent goToCreateTrip = new Intent(getApplicationContext(), CT_LocationsFilters.class);
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