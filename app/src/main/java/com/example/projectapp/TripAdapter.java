package com.example.projectapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;

public class TripAdapter extends ArrayAdapter<Trip> {

    public Context context;
    public LocalTime ultimaHoraTemp;

    public TripAdapter(Context context, ArrayList<Trip> trips){
        super(context, R.layout.row_trip, trips);
        this.context = context;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Trip trip = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_trip, parent, false);
        }

        // Lookup view for data population
        ConstraintLayout MainContainer = (ConstraintLayout) convertView.findViewById(R.id.trip_container) ;
        TextView directions = (TextView) convertView.findViewById(R.id.directions);
        ImageView Image=(ImageView) convertView.findViewById(R.id.transportTypeIcon);
        TextView TransportType = (TextView) convertView.findViewById(R.id.transportTypeText);
        TextView scheduleTimeText = (TextView) convertView.findViewById(R.id.scheduleTimeText);
        TextView tickerPriceText = (TextView) convertView.findViewById(R.id.tickerPriceText);
        Button moreInfoBtn = (Button) convertView.findViewById(R.id.moreInfoBtn);
        ImageButton Timeicon = (ImageButton) convertView.findViewById(R.id.iconTime);
        ImageButton moneyicon = (ImageButton) convertView.findViewById(R.id.iconMoney);
        TextView noselectedtrip = (TextView) convertView.findViewById(R.id.noselectedtrip);

        //se a trip existe (not null)
        if (trip != null) {

            noselectedtrip.setVisibility(View.GONE);
            Image.setVisibility(View.VISIBLE);
            TransportType.setVisibility(View.VISIBLE);
            scheduleTimeText.setVisibility(View.VISIBLE);
            tickerPriceText.setVisibility(View.VISIBLE);
            moreInfoBtn.setVisibility(View.VISIBLE);
            Timeicon.setVisibility(View.VISIBLE);
            moneyicon.setVisibility(View.VISIBLE);

            MainContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ultimaHoraTemp = CT_SearchResults.ultimaHoraChegada; //variavel temporária
                    if (CT_SearchResults.currentContainer != null && MainContainer != CT_SearchResults.currentContainer) {
                        CT_SearchResults.currentContainer.setBackgroundColor(Color.parseColor("#808BC5"));
                        CT_SearchResults.currentContainer = MainContainer;
                        CT_SearchResults.currentTrip = trip;
                        CT_SearchResults.ultimaHoraChegada = trip.getArrival_time();
                        MainContainer.setBackgroundColor(Color.parseColor("#ff512e"));
                    } else if (MainContainer == CT_SearchResults.currentContainer) {
                        CT_SearchResults.ultimaHoraChegada = ultimaHoraTemp;
                        CT_SearchResults.currentContainer = null;
                        CT_SearchResults.currentTrip = null;
                        MainContainer.setBackgroundColor(Color.parseColor("#808BC5"));
                    } else {
                        ultimaHoraTemp = CT_SearchResults.ultimaHoraChegada;
                        CT_SearchResults.currentContainer = MainContainer;
                        CT_SearchResults.currentTrip = trip;
                        CT_SearchResults.ultimaHoraChegada = trip.getArrival_time();
                        MainContainer.setBackgroundColor(Color.parseColor("#ff512e"));
                    }
                }
            });

            // set do tipo de transporte
            TransportType.setText(trip.getTransport_type());

            //set do preço
            DecimalFormat df2 = new DecimalFormat("#.##");
            tickerPriceText.setText(df2.format(trip.getPrice()) + "€");
            //moreinfo.setClickable(true);

            //set do tempo e demora
            scheduleTimeText.setText(trip.getTrip_time());
            

            //set das directions
            String direction = trip.getOrigin_address() + "  ➝  " + trip.getDestiny_address();
            directions.setText(direction);

            moreInfoBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CT_SearchResults.currentTrip = trip;
                    Intent intent = new Intent(parent.getContext(), CT_TripInfo.class);
                    parent.getContext().startActivity(intent);
                }
            });

            //set da imagem do tipo de transporte
            if (trip.getTransport_type().equals("BUS")) {
                Image.setImageResource(R.drawable.bus250);
            } else if (trip.getTransport_type().equals("TRAIN")) {
                Image.setImageResource(R.drawable.train250);
            } else if (trip.getTransport_type().equals("METRO")) {
                Image.setImageResource(R.drawable.metro250);
            }

        } else {
            //null trip

            ArrayList<String> locations = CT_SearchResults.selected_trips;


            directions.setText(locations.get(position) + "  ➝  " + locations.get(position+1));
            noselectedtrip.setVisibility(View.VISIBLE);
            Image.setVisibility(View.GONE);
            TransportType.setVisibility(View.GONE);
            scheduleTimeText.setVisibility(View.GONE);
            tickerPriceText.setVisibility(View.GONE);
            moreInfoBtn.setVisibility(View.GONE);
            Timeicon.setVisibility(View.GONE);
            moneyicon.setVisibility(View.GONE);

        }
        // Return the completed view to render on screen
        return convertView;
    }
}