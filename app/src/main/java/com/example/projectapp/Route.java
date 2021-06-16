package com.example.projectapp;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable {

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

    public ArrayList<Trip> getList_of_trips() {
        return list_of_trips;
    }

    public void setList_of_trips(ArrayList<Trip> list_of_trips) {
        this.list_of_trips = list_of_trips;
    }

    //métodos para guardar rotas em "saved_routes" e para fazer load do mesmo
    public static void storeRoutes() {
        try{
            FileOutputStream file = new FileOutputStream("saved_routes");
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(Route.savedRoutes);
            obj.close();
            file.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void loadRoutes() throws IOException, ClassNotFoundException
    {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        ArrayList<Route> routes = null;
        try
        {
            fileIn = new FileInputStream( "saved_routes" );
            in = new ObjectInputStream( fileIn );
            routes = (ArrayList<Route>)in.readObject();

        }
        finally
        {
            if( fileIn != null )
                fileIn.close();
            if( in != null )
                in.close();
        }
        savedRoutes = routes;
    }

}
