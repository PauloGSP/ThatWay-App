package com.example.projectapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TimePicker;
import java.util.Calendar;

public class CT_Filters extends AppCompatActivity {

    int mHour, mMin;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ct_filters);

        Button departureTime = (Button) findViewById(R.id.departureTime);
        departureTime.setText(CT_SearchResults.horaSaida_str);
        departureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMin = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(CT_Filters.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time;
                        boolean hourZero = false;
                        boolean minuteZero = false;
                        if (hourOfDay < 10) {
                            hourZero = true;
                        }
                        if (minute < 10) {
                            minuteZero = true;
                        }
                        if (hourZero) {
                            time = "0" + hourOfDay +":";
                        } else {
                            time = hourOfDay + ":";
                        }
                        if (minuteZero) {
                            time += "0" + minute;
                        } else {
                            time += minute;
                        }
                        departureTime.setText(time);
                        CT_SearchResults.horaSaida_str = time;
                        System.out.println(time);
                    }
                }, mHour, mMin, true);
                timePickerDialog.show();

            }
        });

        Button arrivalTime = (Button) findViewById(R.id.arrivalTime);
        arrivalTime.setText(CT_SearchResults.horaChegada_str);
        arrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMin = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(CT_Filters.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time;
                        boolean hourZero = false;
                        boolean minuteZero = false;
                        if (hourOfDay < 10) {
                            hourZero = true;
                        }
                        if (minute < 10) {
                            minuteZero = true;
                        }
                        if (hourZero) {
                            time = "0" + hourOfDay +":";
                        } else {
                            time = hourOfDay + ":";
                        }
                        if (minuteZero) {
                            time += "0" + minute;
                        } else {
                            time += minute;
                        }
                        System.out.println(time);
                        arrivalTime.setText(time);
                        CT_SearchResults.horaChegada_str = time;
                    }
                }, mHour, mMin, true);
                timePickerDialog.show();
            }
        });

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

                System.out.println(CT_SearchResults.horaSaida_str);
                System.out.println(CT_SearchResults.horaChegada_str);

                String depTime = CT_SearchResults.horaSaida_str;
                String arrTime = CT_SearchResults.horaChegada_str;

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

                //System.out.println(depDate);
                System.out.println(depTime);
                System.out.println(arrTime);
                for (Boolean b : checked_transports) System.out.println(b);
                System.out.println(ORDER_BY);


                Intent sendFilters = new Intent();
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

