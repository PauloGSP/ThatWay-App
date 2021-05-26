package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

public class CT_LocationFilters3 extends AppCompatActivity {

    ArrayList<String> locations;     //primeiro é o de origem, segundo o de chegada e o resto são breakpoints (ordenados)
    private Intent data;
    private Intent intent;
    private String origin;
    private String destiny;
    private String depDate;
    private String depTime;
    private String arrTime;
    private boolean bus = true;
    private boolean train = true;
    private boolean metro = true;
    private String order = "PRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_location_filters2);

        AutoCompleteTextView location1 = findViewById(R.id.location3_1);
        AutoCompleteTextView breakpoint = findViewById(R.id.location3_2);
        AutoCompleteTextView location2 = findViewById(R.id.location3_3);

        Intent intent = getIntent();
        origin = intent.getStringExtra("location1");
        destiny = intent.getStringExtra("location2");
        location1.setText(origin);
        location2.setText(destiny);

        depDate = data.getStringExtra("depDate");
        depTime = data.getStringExtra("depTime");
        arrTime = data.getStringExtra("arrTime");
        bus = data.getBooleanExtra("bus",true);
        train = data.getBooleanExtra("train",true);
        metro = data.getBooleanExtra("metro",true);
        order = data.getStringExtra("order");


        System.out.println("wowwwwwwwwwwwww man");
        System.out.println(depDate);
        System.out.println(depTime);
        System.out.println(arrTime);
        System.out.println(bus);
        System.out.println(train);
        System.out.println(metro);
        System.out.println(order);



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
                String l1 = location1.getText().toString();
                String l2 = location2.getText().toString();
                String bk = breakpoint.getText().toString();
                if (l1 != "" && l2 != "" && bk != "") {
                    Intent goToResults = new Intent(getApplicationContext(), CT_SearchResults.class);
                    goToResults.putExtra("location1",l1);
                    goToResults.putExtra("location2",l2);
                    goToResults.putExtra("breakpoint",bk);
                    goToResults.putExtra("depDate",depDate);
                    goToResults.putExtra("depTime",depTime);
                    goToResults.putExtra("arrTime",arrTime);
                    goToResults.putExtra("bus",bus);
                    goToResults.putExtra("train",train);
                    goToResults.putExtra("metro",metro);
                    goToResults.putExtra("order",order);
                    startActivity(goToResults);

                } else {
                    System.out.println("Falta parametros");
                }
            }
        });

        //passar argumentos no search results
    }

    /*
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
    */

}