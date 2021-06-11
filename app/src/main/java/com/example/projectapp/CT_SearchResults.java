package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
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

    //mostrar os resultados
    public void loadResults(String origin, String destiny) {

        TextView lblOrigin = (TextView) findViewById(R.id.lblOrigin);
        TextView lblDestiny = (TextView) findViewById(R.id.lblDestiny);
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

        System.out.println("SEARCH RESULTS - TRIP INFO");
        for (String s : selected_trips) System.out.println(s);

        for (int t = 0; t < selected_trips.size()-1 ; t++) {
            String origin = selected_trips.get(t);
            String destiny = selected_trips.get(t+1);
            loadResults(origin,destiny);
        }
    }
}
