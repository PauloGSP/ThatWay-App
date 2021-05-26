package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class CT_LocationFilters3 extends AppCompatActivity {

    ArrayList<String> locations;     //primeiro é o de origem, segundo o de chegada e o resto são breakpoints (ordenados)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_locations_filters);

        locations = new ArrayList<String>();

        Button filtersBtn = (Button) findViewById(R.id.filtersBtn2);
        filtersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToFilters = new Intent(getApplicationContext(), CT_Filters.class);
                startActivity(goToFilters);
            }
        });

        Button searchResultsBtn = (Button) findViewById(R.id.searchResultsBtn2);
        searchResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToResults = new Intent(getApplicationContext(), CT_SearchResults.class);
                //PASSAR ARGUMENTOS
                startActivity(goToResults);
            }
        });

        //passar argumentos no search results
    }
}