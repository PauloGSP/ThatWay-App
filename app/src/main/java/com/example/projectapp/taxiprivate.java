package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class taxiprivate extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_taxiprivate);
        TextView location = (TextView) findViewById(R.id.locationShowTransports2);
        location.setText(MainActivity.currentLocation);
        TextView title =(TextView) findViewById(R.id.title);
        title.setText(ShowTransports.typeTransport);

        ArrayList<String> privados = new ArrayList<>();
        for (Ptransport p : MainActivity.allPrivateTransports) {
            boolean valid = true;
            if (!p.getCity().equals(MainActivity.currentLocation)) valid = false;
            if (!p.getType().equals(ShowTransports.typeTransport)) valid = false;
            if (valid) privados.add(p.getName()+"\n"+p.getContact()+"\n");
        }

        ListView listviewprivate = findViewById(R.id.listviewprivate);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,privados);
        listviewprivate.setAdapter(arrayAdapter);

        ImageButton returnBtn = findViewById(R.id.returnBtn6);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}