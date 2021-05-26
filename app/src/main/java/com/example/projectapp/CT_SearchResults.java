package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CT_SearchResults<pp> extends AppCompatActivity {

    private String[] trips;
    private String[] trip_info;
    private ArrayList<Trip> list_of_trips;
    Trip trip;
    Scanner sc;

    private String origin;
    private String destiny;
    private String origin_address;
    private String destiny_address;
    private LocalTime departure_time;
    private LocalTime arrival_time;
    private String transport_type;
    private Double price;
    private String routine;
    private String origin_coords;
    private String destiny_coords;
    private long travelling_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_search_results);

        /*
        trips = new String[]{"aveiro estarreja estacao_aveiro estacao_estarreja 14:00 14:15 TRAIN 1.45 DAILY 40.64341386036852,-8.6408032773646 40.7512086333165,-8.575193730819068",
                "aveiro estarreja	aveiro_avenida_25_abril	estarreja_rua_caetano_ferreira	14:30	14:35	BUS 2.00	DAILY	40.6363056,-8.6492221	40.7569636,-8.5684835",
                "aveiro  estarreja	estacao_aveiro	estacao_estarreja	15:50	16:03	TRAIN	1.45	DAILY	40.64341386036852, -8.6408032773646	40.7512086333165, -8.575193730819068",
                "viseu	aveiro	estacao_ferroviaria_mangualde	estacao_aveiro	08:00	11:03	TRAIN	3.30	DAILY	40.5867498,-7.760322	40.66187619619079, -7.915905515083552	40.2141963,-8.4366716",
                "porto	lisboa	porto_campanha	lisboa_santa_apolonia	12:37	16:00	TRAIN	25.10	DAILY	41.149318, -8.585097	38.714176, -9.122615,744",
                "porto	aveiro	porto_terminal_casa_da_musica	aveiro_viacao_aveirense	15:00	16:00	BUS 96.77 DAILY	41.1568108, -8.6244826	40.6409332, -8.6573271",
                "coimbra	coimbra	coimbra_estacao_nova	coimbra_avenida_fernao_magalhaes	11:13	11:21	BUS	3.50	DAILY	40.2087667, -8.43205	40.2141963, -8.4366716"
        };
        */

        list_of_trips = new ArrayList<Trip>();

        try {

            sc = new Scanner(new File("trips.txt"));

            while (sc.hasNextLine()) {

                trip_info = sc.nextLine().split("\\s");

                origin = trip_info[0];
                destiny = trip_info[1];
                origin_address = trip_info[2];
                destiny_address = trip_info[3];
                departure_time = LocalTime.parse(trip_info[4]);
                arrival_time = LocalTime.parse(trip_info[5]);
                transport_type = trip_info[6];
                price = Double.parseDouble(trip_info[7]);
                routine = trip_info[8];
                origin_coords = trip_info[9];
                destiny_coords = trip_info[10];
                travelling_time = ChronoUnit.MINUTES.between(departure_time, arrival_time);

                trip = new Trip(origin, destiny, origin_address, destiny_address,
                        departure_time, arrival_time, transport_type, price, routine,
                        origin_coords, destiny_coords, travelling_time);

                list_of_trips.add(trip);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}