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

    private Intent data;
    private String depDate;
    private String depTime;
    private String arrTime;
    private boolean bus;
    private boolean train;
    private boolean metro;
    private String order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_locations_filters);

        AutoCompleteTextView location2_1 = findViewById(R.id.location2_1);
        AutoCompleteTextView location2_2 = findViewById(R.id.location2_2);

        Button addBreakpointBtn = (Button) findViewById(R.id.addBreakpointBtn1);
        addBreakpointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoNextPage = new Intent(getApplicationContext(), CT_LocationFilters3.class);
                startActivityForResult(gotoNextPage,1);
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
                String location1 = location2_1.getText().toString();
                String location2 = location2_2.getText().toString();
                if (location1 != "" || location2 != "") {
                    Intent goToResults = new Intent(getApplicationContext(), CT_SearchResults.class);
                    goToResults.putExtra("location1",location1);
                    goToResults.putExtra("location2",location2);
                    //PASSAR ARGUMENTOS
                    startActivity(goToResults);
                } else {
                    System.out.println("Falta parametros");
                }
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                depDate = data.getStringExtra("depDate");
                depTime = data.getStringExtra("depTime");
                arrTime = data.getStringExtra("arrTime");
                bus = data.getBooleanExtra("bus",true);
                train = data.getBooleanExtra("train",true);
                metro = data.getBooleanExtra("metro",true);
                order = data.getStringExtra("order");
            }
        }
    }

}