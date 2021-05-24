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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public Date getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Date departure_time) {
        this.departure_time = departure_time;
    }

    public Date getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getTransport_type() {
        return transport_type;
    }

    public void setTransport_type(String transport_type) {
        this.transport_type = transport_type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTravelling_time() {
        return travelling_time;
    }

    public void setTravelling_time(Date travelling_time) {
        this.travelling_time = travelling_time;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public String getPlace_of_departure() {
        return place_of_departure;
    }

    public void setPlace_of_departure(String place_of_departure) {
        this.place_of_departure = place_of_departure;
    }

    public String getPlace_of_arrival() {
        return place_of_arrival;
    }

    public void setPlace_of_arrival(String place_of_arrival) {
        this.place_of_arrival = place_of_arrival;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "origin='" + origin + '\'' +
                ", destiny='" + destiny + '\'' +
                ", departure_time=" + departure_time +
                ", arrival_time=" + arrival_time +
                ", transport_type='" + transport_type + '\'' +
                ", price=" + price +
                ", travelling_time=" + travelling_time +
                ", departure_routine='" + departure_routine + '\'' +
                ", departure_date=" + departure_date +
                ", place_of_departure='" + place_of_departure + '\'' +
                ", place_of_arrival='" + place_of_arrival + '\'' +
                '}';
    }
}
