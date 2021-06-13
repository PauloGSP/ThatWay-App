package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CT_SearchResults extends AppCompatActivity {

    public static ArrayList<String> selected_trips;  //guarda todas as trips (origem + breakpoint(s) + destino)
    public static LocalTime horaSaida;
    public static LocalTime horaChegada;
    public static String horaSaida_str = "choose time";
    public static String horaChegada_str = "choose time";
    public static boolean bus = true;
    public static boolean train = true;
    public static boolean metro = true;
    public static String order;

    private int currentPage = 1;
    private int maxPages;

    //mostrar os resultados
    public void loadResults(int current) {

        String origin = selected_trips.get(current - 1);
        String destiny = selected_trips.get(current);

        ArrayList<Trip> trips = new ArrayList<Trip>();
        ArrayList<String> transports = new ArrayList<String>();
        if (bus) transports.add("BUS");
        if (train) transports.add("TRAIN");
        if (metro) transports.add("METRO");

        //Filtrar trips
        for (Trip t : MainActivity.allTrips) {

            //variavel para saber se vai ser apresentado no search results ou não
            boolean valid = true;

            //guardar numa lista as localizações da trip que está a ser analisada
            ArrayList<String> origin_locations = new ArrayList<String>(Arrays.asList(t.getOrigin(),t.getOrigin_address()));
            ArrayList<String> destiny_locations = new ArrayList<String>(Arrays.asList(t.getDestiny(),t.getDestiny_address()));

            String transport = t.getTransport_type();
            //verificar se o tipo de transporte da trip está nos filtros introduzidos pelo utilizador
            if (!transports.contains(transport)) valid = false;

            if (!origin_locations.contains(origin)) valid = false;
            if (!destiny_locations.contains(destiny)) valid = false;

            LocalTime depTime = t.getDeparture_time();
            LocalTime arrTime = t.getArrival_time();

            if (!(depTime.isAfter(horaSaida) && arrTime.isBefore(horaChegada))) valid = false;

            if (valid) trips.add(t);
        }

        TripAdapter adapter = new TripAdapter(this, trips);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        Button nextBtn = (Button) findViewById(R.id.btnNext);
        TextView lblOrigin = (TextView) findViewById(R.id.lblOrigin);
        TextView lblDestiny = (TextView) findViewById(R.id.lblDestiny);

        if (current > maxPages) {
            //passar para a proxima atividade

            Intent goToRoute = new Intent(getApplicationContext(), RouteAllDetails.class);
            startActivity(goToRoute);

        } else if (current == maxPages) {
            nextBtn.setText("SAVE ROUTE");

        } else {
            nextBtn.setText("NEXT (" + current + "/" + maxPages + ")");
        }

        lblOrigin.setText(origin);
        lblDestiny.setText(destiny);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_search_results);

        maxPages = selected_trips.size() - 1;

        //load da primeira pagina
        loadResults(currentPage);

        Button nextBtn = (Button) findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sempre que se clica NEXT mostra a proxima página de resultados se tudo tiver selecionado ou, se for a ultima pagina,
                //passa para a proxima activity
                loadResults(++currentPage);

            }
        });

        System.out.println("SEARCH RESULTS - TRIP INFO");
        for (String s : selected_trips) System.out.println(s);

    }
}
