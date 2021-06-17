package com.example.projectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;

public class RouteAdapter extends ArrayAdapter<Route> {

    public RouteAdapter(Context context, ArrayList<Route> trips){
        super(context, R.layout.route_row, Route.savedRoutes);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Route route = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.route_row, parent, false);
        }

        Button button = convertView.findViewById(R.id.routeBtn);
        button.setText(route.getTitle());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route.currentRoute = route;
                Intent goRoute = new Intent(parent.getContext(), RouteAllDetails.class);
                parent.getContext().startActivity(goRoute);
            }
        });


        // Return the completed view to render on screen
        return convertView;
    }
}