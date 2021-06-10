package com.example.projectapp;

import java.util.ArrayList;

public class Route {

    private String title;
    private String total_travelling_time;
    private Double total_price;
    private ArrayList<Trip> list_of_trips;

    //recebe o titulo introduzido pelo utilizador
    public Route(String title) {
        this.title = title;
        this.list_of_trips = new ArrayList<Trip>();
    }

    //metodos para calcular o total_travelling_time e o total_price

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal_travelling_time() {
        return total_travelling_time;
    }

    public void setTotal_travelling_time(String total_travelling_time) {
        this.total_travelling_time = total_travelling_time;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public ArrayList<Trip> getList_of_trips() {
        return list_of_trips;
    }

    public void setList_of_trips(ArrayList<Trip> list_of_trips) {
        this.list_of_trips = list_of_trips;
    }
}
