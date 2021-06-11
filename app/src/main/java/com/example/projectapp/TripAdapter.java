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

import java.util.ArrayList;

public class TripAdapter extends ArrayAdapter<Trip> {
    public TripAdapter(Context context, ArrayList<Trip> trips){

        super(context,0, trips);
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
        ConstraintLayout MainContainer = (ConstraintLayout) convertView.findViewById(R.id.constraintLayout3) ;
        ImageView Image=(ImageView) convertView.findViewById(R.id.transportTypeIcon);
        TextView TransportType = (TextView) convertView.findViewById(R.id.transportTypeText);
        TextView scheduleTimeText = (TextView) convertView.findViewById(R.id.scheduleTimeText);
        TextView tickerPriceText = (TextView) convertView.findViewById(R.id.tickerPriceText);
        Button addtrip = (Button) convertView.findViewById(R.id.addTripBtn);
        Button moreinfo = (Button) convertView.findViewById(R.id.moreInfoBtn);
        ImageButton Timeicon =(ImageButton) convertView.findViewById(R.id.iconTime);
        ImageButton moneyicon =(ImageButton) convertView.findViewById(R.id.iconMoney);

        // Populate the data into the template view using the data object
        TransportType.setText(trip.getTransport_type());
        tickerPriceText.setText( trip.getPrice().toString());
        addtrip.setClickable(true);
        moreinfo.setClickable(true);

        // Return the completed view to render on screen
        return convertView;
    }
}