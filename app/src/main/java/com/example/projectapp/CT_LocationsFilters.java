package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class CT_LocationsFilters extends AppCompatActivity {

    ArrayList<String> locations;     //primeiro é o de origem, segundo o de chegada e o resto são breakpoints (ordenados)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_locations_filters);

        locations = new ArrayList<String>();

        Button addBreakpointBtn = (Button) findViewById(R.id.addBreakpointBtn1);
        addBreakpointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoNextPage = new Intent(getApplicationContext(), CT_LocationFilters3.class);
                startActivity(gotoNextPage);
            }
        });

        Button filtersBtn = (Button) findViewById(R.id.filtersBtn1);
        filtersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToFilters = new Intent(getApplicationContext(), CT_Filters.class);
                startActivity(goToFilters);
            }
        });

        ImageButton swapBtn = (ImageButton) findViewById(R.id.swapBtn);
        swapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView location2_1 = findViewById(R.id.location2_1);
                AutoCompleteTextView location2_2 = findViewById(R.id.location2_2);
                String location1 = location2_1.getText().toString();
                String location2 = location2_2.getText().toString();
                location2_1.setText(location2);
                location2_2.setText(location1);

            }
        });

        Button searchResultsBtn = (Button) findViewById(R.id.searchResultsBtn);
        searchResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToResults = new Intent(getApplicationContext(), CT_SearchResults.class);
                //PASSAR ARGUMENTOS
                startActivity(goToResults);
            }
        });

    }
}