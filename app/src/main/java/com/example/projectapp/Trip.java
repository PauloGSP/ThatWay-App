package com.example.projectapp;

import java.time.LocalTime;
import java.util.Date;

public class Trip {

    private String origin;
    private String destiny;
    private LocalTime departure_time;
    private LocalTime arrival_time;
    private String transport_type;
    private Double price;
    private long travelling_time;
    private String departure_routine;
    private Date departure_date;
    private String place_of_departure;
    private String place_of_arrival;

    //construtor com data única
    public Trip(String origin, String destiny, LocalTime departure_time, LocalTime arrival_time, String transport_type, Double price,
                long travelling_time, Date departure_date, String place_of_departure, String place_of_arrival) {
        this.origin = origin;
        this.destiny = destiny;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.transport_type = transport_type;
        this.price = price;
        this.travelling_time = travelling_time;
        this.departure_date = departure_date;
        this.place_of_departure = place_of_departure;
        this.place_of_arrival = place_of_arrival;
    }

    //construtor sem data unica (é rotineiro)
    public Trip(String origin, String destiny, LocalTime departure_time, LocalTime arrival_time, String transport_type, Double price,
                long travelling_time, String departure_routine, String place_of_departure, String place_of_arrival) {
        this.origin = origin;
        this.destiny = destiny;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.transport_type = transport_type;
        this.price = price;
        this.travelling_time = travelling_time;
        this.departure_routine = departure_routine;
        this.place_of_departure = place_of_departure;
        this.place_of_arrival = place_of_arrival;
    }

    // GETTERS AND SETTERS

    // TO STRING
}
