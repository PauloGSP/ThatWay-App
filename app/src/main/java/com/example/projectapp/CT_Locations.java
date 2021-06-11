package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    ArrayList<View> breakpoints;
    LinearLayout layoutList;


    public void addView() {

        View locationView = getLayoutInflater().inflate(R.layout.row_location, null, false);

        AutoCompleteTextView locationText = (AutoCompleteTextView) locationView.findViewById(R.id.locationAutoComplete);

        ImageButton removeButton = (ImageButton) locationView.findViewById(R.id.deleteBreakpoint);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(locationView);
            }
        });

        layoutList.addView(locationView);
        breakpoints.add(locationView); //adicionar à lista de breakpoints
        numOfBreakpoints++;

        if (numOfBreakpoints >= 3) {
            Button addBreakpoint = (Button) findViewById(R.id.addBreakpoint);
            addBreakpoint.setVisibility(View.GONE);
        }

        if (numOfBreakpoints > 0) {
            ImageButton btnSwap = (ImageButton) findViewById(R.id.swapBtn);
            btnSwap.setVisibility(View.GONE);
        }


    }

    public void removeView(View view) {

        layoutList.removeView(view);
        breakpoints.remove(view); //remover da lista de breakpoints
        numOfBreakpoints--;

        if (numOfBreakpoints < 3) {
            Button addBreakpoint = (Button) findViewById(R.id.addBreakpoint);
            addBreakpoint.setVisibility(View.VISIBLE);
        }

        if (numOfBreakpoints == 0) {
            ImageButton btnSwap = (ImageButton) findViewById(R.id.swapBtn);
            btnSwap.setVisibility(View.VISIBLE);
        }

    }

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
        breakpoints = new ArrayList<View>();
        layoutList = findViewById(R.id.layout_list);

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
                addView();

            }
        });

        Button searchResultsBtn = (Button) findViewById(R.id.searchResultsBtn);
        searchResultsBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                ArrayList<String> locations = new ArrayList<String>();
                boolean validLocations = true;
                boolean noRepeatedLocations = true;

                String location1 = location2_1.getText().toString().trim();
                String location2 = location2_2.getText().toString().trim();

                locations.add(location1);
                locations.add(location2);

                if (!Arrays.asList(CT_SearchResults.LOCATIONS).contains(location1.toLowerCase()) ||
                    !Arrays.asList(CT_SearchResults.LOCATIONS).contains(location2.toLowerCase())) {
                    validLocations = false;
                }

                int currentBk = 1;

                for (View bk_view : breakpoints) {
                    AutoCompleteTextView autocomplete = bk_view.findViewById(R.id.locationAutoComplete);
                    String location = autocomplete.getText().toString().trim();

                    //ver se o valor atual não existe nas locations possíveis
                    if (!Arrays.asList(CT_SearchResults.LOCATIONS).contains(location.toLowerCase())) {
                        validLocations = false;
                        System.out.println("LOCATIONS INVÁLIDAS");
                        break;
                    } else {
                        locations.add(currentBk++,location);
                    }
                }

                Set<String> set = new HashSet<String>(locations);
                if (set.size() != locations.size()) {
                    noRepeatedLocations = false;
                    System.out.println("LOCATIONS repetidas");
                }

                if (validLocations && noRepeatedLocations) {
                        //passar todos os argumentos

                        CT_SearchResults.trips = locations;
                        Intent goToResults = new Intent(getApplicationContext(), CT_SearchResults.class);
                        startActivity(goToResults);

                } else {
                    locations.clear();
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