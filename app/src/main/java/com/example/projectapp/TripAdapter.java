package com.example.projectapp;

import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TripAdapter extends ArrayAdapter<Trip> {

    public TripAdapter(Context context, ArrayList<Trip> trips){
        super(context, R.layout.row_trip, trips);
    }

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
        Button moreinfo = (Button) convertView.findViewById(R.id.moreInfoBtn);
        ImageButton Timeicon = (ImageButton) convertView.findViewById(R.id.iconTime);
        ImageButton moneyicon = (ImageButton) convertView.findViewById(R.id.iconMoney);

        // set do tipo de transporte
        TransportType.setText(trip.getTransport_type());

        //set do preço
        DecimalFormat df2 = new DecimalFormat("#.##");
        tickerPriceText.setText(df2.format(trip.getPrice()) + "€");
        moreinfo.setClickable(true);

        //set do tempo e demora
        scheduleTimeText.setText(trip.getTripTime());

        //set das directions
        String direction = trip.getOrigin_address() + "  ➝  " + trip.getDestiny_address();
        directions.setText(direction);

        //set da imagem do tipo de transporte

        // Return the completed view to render on screen
        return convertView;
    }
}