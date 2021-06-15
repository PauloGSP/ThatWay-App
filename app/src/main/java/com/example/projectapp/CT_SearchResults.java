package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CT_SearchResults extends AppCompatActivity {

    public static LocalTime ultimaHoraChegada;
    public static Route currentRoute = null;
    public static int numberOfChilds;
    public static Trip currentTrip;
    public static ConstraintLayout currentContainer;
    public static ArrayList<Trip> trips;
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


    public void openDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Message
        alertDialog.setTitle("Save route");
        alertDialog.setMessage("Name the route to save it:");
        final EditText input = new EditText(getApplicationContext());
        alertDialog.setView(input);

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String name = input.getText().toString();
                    if (!name.trim().equals("")) {
                        //CRIAR CLASSE ROUTE COM TODAS AS TRIPS E COM O DEVIDO NOME
                        //VER SE JÁ EXISTEM ROUTES COM ESSE NOME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        Route route = new Route(name,trips);
                        boolean validName = true;
                        for (Route r : Route.savedRoutes) {
                            if (r.getTitle() == name) validName = false;
                        }
                        if (validName) {
                            Route.savedRoutes.add(route);
                            Route.currentRoute = route;
                            Toast.makeText(getApplicationContext(),"Route saved", Toast.LENGTH_SHORT).show();
                            System.out.println(name);
                            Intent goToRoute = new Intent(getApplicationContext(), RouteAllDetails.class);
                            startActivity(goToRoute);
                        } else {
                            Toast.makeText(getApplicationContext(),"There's already a route with that title.", Toast.LENGTH_SHORT).show();
                            openDialog();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Please name the route.", Toast.LENGTH_SHORT).show();
                        openDialog();
                    }
                }
        });
        alertDialog.show();
    }

    //mostrar os resultados
    public void loadResults(int current) {

        Button nextBtn = (Button) findViewById(R.id.btnNext);
        if (current > maxPages) {
            //passar para o modal de dar o nome à rota!

            openDialog();

        } else {
            if (current == maxPages) {
                nextBtn.setText("SAVE ROUTE");

            } else if (current < maxPages) {
                nextBtn.setText("NEXT (" + current + "/" + maxPages + ")");
            }

            String origin = selected_trips.get(current - 1);
            String destiny = selected_trips.get(current);

            CT_SearchResults.currentTrip = null;
            CT_SearchResults.currentContainer = null;

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
                ArrayList<String> origin_locations = new ArrayList<String>(Arrays.asList(t.getOrigin(), t.getOrigin_address()));
                ArrayList<String> destiny_locations = new ArrayList<String>(Arrays.asList(t.getDestiny(), t.getDestiny_address()));

                String transport = t.getTransport_type();
                //verificar se o tipo de transporte da trip está nos filtros introduzidos pelo utilizador
                if (!transports.contains(transport)) valid = false;

                if (!origin_locations.contains(origin)) valid = false;
                if (!destiny_locations.contains(destiny)) valid = false;

                LocalTime depTime = t.getDeparture_time();
                LocalTime arrTime = t.getArrival_time();

                if (!(depTime.isAfter(horaSaida) && arrTime.isBefore(horaChegada))) valid = false;
                if (!depTime.isAfter(ultimaHoraChegada)) valid = false;

                if (valid) trips.add(t);
            }

            TripAdapter adapter = new TripAdapter(this, trips);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);
            numberOfChilds = listView.getChildCount();

            TextView lblOrigin = (TextView) findViewById(R.id.lblOrigin);
            TextView lblDestiny = (TextView) findViewById(R.id.lblDestiny);

            lblOrigin.setText(origin);
            lblDestiny.setText(destiny);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_search_results);

        trips = new ArrayList<Trip>();
        maxPages = selected_trips.size() - 1;

        ultimaHoraChegada = LocalTime.parse("00:00");

        //load da primeira pagina
        loadResults(currentPage);

        Button nextBtn = (Button) findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //VER SE PODE SER VÁLIDO UMA PESSOA N ESCOLHER NENHUMA TRIP (EX: N LHE AGRADA NENHUMA)

                trips.add(currentTrip); //tanto pode adicionar uma trip selecionada como adiciona 'null'
                if (numberOfChilds == 0) {
                    loadResults(++currentPage);
                } else if (CT_SearchResults.currentContainer == null) {
                    Toast.makeText(getApplicationContext(), "Please select a trip!", Toast.LENGTH_LONG).show();
                } else {
                    loadResults(++currentPage);
                }
            }
        });

        System.out.println("SEARCH RESULTS - TRIP INFO");
        for (String s : selected_trips) System.out.println(s);

    }
}
