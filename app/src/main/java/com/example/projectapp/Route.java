package com.example.projectapp;

import java.util.ArrayList;

public class Route {

    public static ArrayList<Route> savedRoutes = new ArrayList<Route>();
    public static Route currentRoute;
    private String title;
    private long total_travelling_time;
    private Double total_price;
    private ArrayList<Trip> list_of_trips;

    //recebe o titulo introduzido pelo utilizador
    public Route(String title,ArrayList<Trip> routeTrips) {
        this.title = title;
        this.list_of_trips = routeTrips;
        getTotal_travelling_time();
        getTotal_price();
    }

    //metodos para calcular o total_travelling_time e o total_price

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTotal_travelling_time() {
        long travellingTime = 0;
        for (Trip t : list_of_trips) {
            if (t != null) {
                travellingTime += t.getTravelling_time();
            }
        }
        this.total_travelling_time = travellingTime;
        return travellingTime;
    }

    public void setTotal_travelling_time(long total_travelling_time) {
        this.total_travelling_time = total_travelling_time;
    }

    public Double getTotal_price() {
        double total_price = 0;
        for (Trip t : list_of_trips) {
            if (t != null) {
                total_price += t.getPrice();
            }
        }
        this.total_price = total_price;
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
