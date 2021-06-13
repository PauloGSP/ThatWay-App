package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CT_SearchResults extends AppCompatActivity {

    public static ArrayList<String> selected_trips;  //guarda todas as trips (origem + breakpoint(s) + destino)
    public static String dataSaida;
    public static String horaSaida;
    public static String horaChegada;
    public static boolean bus = true;
    public static boolean train = true;
    public static boolean metro = true;
    public static String order;

    private int currentPage = 1;
    private int maxPages;

    //mostrar os resultados
    public void loadResults(int current) {

        Button nextBtn = (Button) findViewById(R.id.btnNext);
        TextView lblOrigin = (TextView) findViewById(R.id.lblOrigin);
        TextView lblDestiny = (TextView) findViewById(R.id.lblDestiny);

        if (current >= maxPages) {
            //passar para a proxima atividade

            Intent goToRoute = new Intent(getApplicationContext(), RouteAllDetails.class);
            startActivity(goToRoute);

        } else if (current == maxPages -1) {
            nextBtn.setText("SAVE ROUTE");

        } else {
            nextBtn.setText("NEXT (" + current + "/" + maxPages + ")");
        }

        String origin = selected_trips.get(current - 1);
        String destiny = selected_trips.get(current);

        lblOrigin.setText(origin);
        lblDestiny.setText(destiny);

        //mostrar resultados no recyclerview



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_search_results);

        maxPages = selected_trips.size() - 1;

        TripAdapter adapter = new TripAdapter(this, MainActivity.allTrips);
        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //recyclerView.setAdapter(adapter);



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
