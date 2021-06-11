package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class CT_Locations extends AppCompatActivity {

    private Intent data;
    private String depDate;
    private String depTime;
    private String arrTime;
    private boolean bus;
    private boolean train;
    private boolean metro;
    private String order;

    private int numOfBreakpoints;
    private ListView listView;
    ArrayList<Breakpoint> breakpoints;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_locations_filters);

        AutoCompleteTextView location2_1 = findViewById(R.id.origem);
        AutoCompleteTextView location2_2 = findViewById(R.id.destino);
        // update origin location with the main activity current location
        location2_1.setText(MainActivity.currentLocation);

        numOfBreakpoints = 0;
        listView = findViewById(R.id.listview);
        breakpoints = new ArrayList<Breakpoint>();

        BreakpointAdapter adapter = new BreakpointAdapter(this, breakpoints);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        Button filtersBtn = (Button) findViewById(R.id.filtersBtn1);
        filtersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToFilters = new Intent(getApplicationContext(), CT_Filters.class);
                startActivityForResult(goToFilters,1);
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

        Button addBreakpoint = (Button) findViewById(R.id.addBreakpoint);
        addBreakpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //add xml row to the listview
                Breakpoint bk = new Breakpoint();
                //breakpoints.add(bk);
                adapter.add(bk);
                //adapter.notifyDataSetChanged();
            }
        });

        Button searchResultsBtn = (Button) findViewById(R.id.searchResultsBtn);
        searchResultsBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String location1 = location2_1.getText().toString();
                String location2 = location2_2.getText().toString();
                if (location1 != "" && location2 != "") {

                    CT_SearchResults.origem = location1;
                    CT_SearchResults.destino = location2;
                    Intent goToResults = new Intent(getApplicationContext(), CT_SearchResults.class);
                    startActivity(goToResults);

                } else {
                    System.out.println("Falta parametros");
                }
            }
        });

    }

    //receber filtros e armazená-los
    @RequiresApi(api = Build.VERSION_CODES.O)
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

                CT_SearchResults.dataSaida = depDate;
                CT_SearchResults.horaSaida = depTime;
                CT_SearchResults.horaChegada = arrTime;
                CT_SearchResults.bus = bus;
                CT_SearchResults.train = train;
                CT_SearchResults.metro = metro;
                CT_SearchResults.order = order;
            }
        }
    }

}