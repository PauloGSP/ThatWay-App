package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CT_Locations extends Activity {

    private int numOfBreakpoints;
    ArrayList<View> breakpoints;
    LinearLayout layoutList;

    public void addView() {

        View locationView = getLayoutInflater().inflate(R.layout.row_location, null, false);

        AutoCompleteTextView locationText = (AutoCompleteTextView) locationView.findViewById(R.id.locationAutoComplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,MainActivity.allLocations);
        locationText.setAdapter(adapter);

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

    //percorrer todos os filhos do viewgroup (linear layout neste caso)
    public ArrayList<String> loopBreakpoints(ViewGroup parent) {
        ArrayList<String> breakpoints = new ArrayList<String>();
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                AutoCompleteTextView autocomplete = child.findViewById(R.id.locationAutoComplete);
                String location = autocomplete.getText().toString().trim();
                breakpoints.add(location);
            }
        }
        return breakpoints;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ct_locations_filters);

        ImageButton returnBtn = findViewById(R.id.returnBtn2);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageButton homeBtn = findViewById(R.id.homeBtnShowTransports2);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });

        AutoCompleteTextView location2_1 = findViewById(R.id.origem);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,MainActivity.allLocations);
        location2_1.setAdapter(adapter);
        location2_1.setDropDownAnchor(R.id.container1);
        AutoCompleteTextView location2_2 = findViewById(R.id.destino);
        location2_2.setAdapter(adapter);
        location2_2.setDropDownAnchor(R.id.container2);
        // update origin location with the main activity current location
        location2_1.setText(MainActivity.currentLocation);

        numOfBreakpoints = 0;
        breakpoints = new ArrayList<View>();
        layoutList = findViewById(R.id.layout_list);

        CT_SearchResults.horaSaida = (CT_SearchResults.horaSaida_str.equals("choose time")) ? LocalTime.parse("00:00") : LocalTime.parse(CT_SearchResults.horaSaida_str);
        CT_SearchResults.horaChegada = (CT_SearchResults.horaChegada_str.equals("choose time")) ? LocalTime.parse("23:59") : LocalTime.parse(CT_SearchResults.horaChegada_str);

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
                ArrayList<String> breakpoints = loopBreakpoints(layoutList);
                locations.addAll(breakpoints);
                locations.add(location2);

                for (String loc : locations) {
                    if (!MainActivity.allLocations.stream().map(s -> s.toLowerCase()).collect(Collectors.toList()).contains(loc.toLowerCase())) validLocations = false;
                }

                if (validLocations == false) Toast.makeText(getApplicationContext(),"Invalid locations", Toast.LENGTH_SHORT).show();


                Set<String> set = new HashSet<String>(locations);
                if (set.size() != locations.size()) {
                    noRepeatedLocations = false;
                    Toast.makeText(getApplicationContext(),"Repeated locations", Toast.LENGTH_SHORT).show();
                }

                if (validLocations && noRepeatedLocations) {
                    CT_SearchResults.selected_trips = locations;
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
                String depTime = data.getStringExtra("depTime");
                String arrTime = data.getStringExtra("arrTime");
                boolean bus = data.getBooleanExtra("bus", true);
                boolean train = data.getBooleanExtra("train", true);
                boolean metro = data.getBooleanExtra("metro", true);
                String order = data.getStringExtra("order");

                CT_SearchResults.horaSaida = (depTime.equals("choose time")) ? LocalTime.parse("00:00") : LocalTime.parse(depTime);
                CT_SearchResults.horaChegada = (arrTime.equals("choose time")) ? LocalTime.parse("23:59") : LocalTime.parse(arrTime);
                CT_SearchResults.bus = bus;
                CT_SearchResults.train = train;
                CT_SearchResults.metro = metro;
                CT_SearchResults.order = order;
            }
        }
    }

}