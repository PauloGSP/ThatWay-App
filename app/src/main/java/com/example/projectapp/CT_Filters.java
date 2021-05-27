package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class CT_Filters extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_filters);

        EditText departureDate = (EditText) findViewById(R.id.departureDate);

        EditText departureTime = (EditText) findViewById(R.id.departureTime);

        EditText arrivalTime = (EditText) findViewById(R.id.arrivalTime);

        CheckBox busCheckBox = (CheckBox) findViewById(R.id.busCheckBox);
        busCheckBox.setChecked(CT_SearchResults.bus);

        CheckBox trainCheckBox = (CheckBox) findViewById(R.id.trainCheckBox);
        trainCheckBox.setChecked(CT_SearchResults.train);

        CheckBox metroCheckBox = (CheckBox) findViewById(R.id.metroCheckBox);
        metroCheckBox.setChecked(CT_SearchResults.metro);

        Switch orderBySwitch = (Switch) findViewById(R.id.orderBySwitch);

        Button filtersDoneBtn = (Button) findViewById(R.id.filtersDoneBtn);

        filtersDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passar todos os parametros para a página anterior

                //falta ver os estados do dep date / dep time / arr time
                //por default estes valores estão com o timestamp atual no CT_LocationFilters e só dá para fazer set a estes valores
                //o departure date n permite inserir o "/" nem o "-"

                String depDate = departureDate.getText().toString();
                String depTime = departureTime.getText().toString();
                String arrTime = arrivalTime.getText().toString();
                depDate = ((depDate.matches("")) ? "DEFAULT" : depDate);
                depTime = ((depTime.matches("")) ? "DEFAULT" : depTime);
                arrTime = ((arrTime.matches("")) ? "DEFAULT" : arrTime);

                // ver os estados das checkBox's
                Boolean[] checked_transports = new Boolean[3];  //inicializa um array de tamanho 3 com valores 0
                checked_transports[0] = ((busCheckBox.isChecked()) ? true : false);
                checked_transports[1] = ((trainCheckBox.isChecked()) ? true : false);
                checked_transports[2] = ((metroCheckBox.isChecked()) ? true : false);

                //ver qual é o estado do switch para saber se a ordenação é por travelling time ou por price
                String ORDER_BY;
                if (orderBySwitch.isChecked()) {
                    ORDER_BY = "TRAVELLING_TIME";
                } else {
                    ORDER_BY = "PRICE";
                }

                System.out.println(depDate);
                System.out.println(depTime);
                System.out.println(arrTime);
                for (Boolean b : checked_transports) System.out.println(b);
                System.out.println(ORDER_BY);


                Intent sendFilters = new Intent();
                sendFilters.putExtra("depDate",depDate);
                sendFilters.putExtra("depTime",depTime);
                sendFilters.putExtra("arrTime",arrTime);
                sendFilters.putExtra("bus",checked_transports[0]);
                sendFilters.putExtra("train",checked_transports[1]);
                sendFilters.putExtra("metro",checked_transports[2]);
                sendFilters.putExtra("order",ORDER_BY);
                setResult(RESULT_OK, sendFilters);
                finish();

            }
        });

    }
}