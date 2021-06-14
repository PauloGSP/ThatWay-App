package com.example.projectapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends ArrayAdapter<Trip> {

    public static Trip currentTrip;
    public Context context;

    public TripAdapter(Context context, ArrayList<Trip> trips){
        super(context, R.layout.row_trip, trips);
        this.context = context;
    }

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

        // set do tipo de transporte
        TransportType.setText(trip.getTransport_type());

        //set do preço
        DecimalFormat df2 = new DecimalFormat("#.##");
        tickerPriceText.setText(df2.format(trip.getPrice()) + "€");
        //moreinfo.setClickable(true);

        //set do tempo e demora
        scheduleTimeText.setText(trip.getTripTime());

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

        // Return the completed view to render on screen
        return convertView;
    }
}