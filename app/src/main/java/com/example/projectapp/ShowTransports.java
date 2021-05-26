package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class ShowTransports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_transports);

        //ir para o create trips com filtro do bus
        Button bustrip = findViewById(R.id.bus);
        bustrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateTrip = new Intent(getApplicationContext(), CT_LocationsFilters.class);
                startActivity(goToCreateTrip);
            }

        });
        //ir para o create trips com filtro do train

        Button traintrip = findViewById(R.id.train);
        traintrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateTrip = new Intent(getApplicationContext(), CT_LocationsFilters.class);
                startActivity(goToCreateTrip);
            }

        });
        //ir para o create trips com filtro do metro

        Button metrotrip = findViewById(R.id.metro);
        metrotrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateTrip = new Intent(getApplicationContext(), CT_LocationsFilters.class);
                startActivity(goToCreateTrip);
            }

        });
        // ir para a pagina do taxi harcoded
        Button taxi = findViewById(R.id.taxi);
        taxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPrivateTransport = new Intent(getApplicationContext(),taxiprivate.class ); //checkar
                startActivity(goToPrivateTransport);
            }

        });
    }
}