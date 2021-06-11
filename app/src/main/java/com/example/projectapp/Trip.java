package com.example.projectapp;

import java.time.LocalTime;
import java.util.Date;

    public class Trip {

        private String origin;
        private String destiny;
        private String origin_address;
        private String destiny_address;
        private LocalTime departure_time;
        private LocalTime arrival_time;
        private String transport_type;
        private Double price;
        private long travelling_time;
        private String origin_coords;
        private String destiny_coords;

        //construtor com data única
        public Trip(String origin, String destiny, String origin_address, String destiny_address, LocalTime departure_time, LocalTime arrival_time, String transport_type, Double price,
                    String origin_coords, String destiny_coords, long travelling_time) {
            this.origin = origin;
            this.destiny = destiny;
            this.origin_address = origin_address;
            this.destiny_address = destiny_address;
            this.departure_time = departure_time;
            this.arrival_time = arrival_time;
            this.transport_type = transport_type;
            this.price = price;
            this.origin_coords = origin_coords;
            this.destiny_coords = destiny_coords;
            this.travelling_time = travelling_time;
        }

        // GETTERS AND SETTERS

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

        public String getOrigin_address() {
            return origin_address;
        }

        public void setOrigin_address(String origin_address) {
            this.origin_address = origin_address;
        }

        public String getDestiny_address() {
            return destiny_address;
        }

        public void setDestiny_address(String destiny_address) {
            this.destiny_address = destiny_address;
        }

        public LocalTime getDeparture_time() {
            return departure_time;
        }

        public void setDeparture_time(LocalTime departure_time) {
            this.departure_time = departure_time;
        }

        public LocalTime getArrival_time() {
            return arrival_time;
        }

        public void setArrival_time(LocalTime arrival_time) {
            this.arrival_time = arrival_time;
        }

        public String getTransport_type() {
            return transport_type;
        }

        public void setTransport_type(String transport_type) { this.transport_type = transport_type; }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public long getTravelling_time() {
            return travelling_time;
        }

        public void setTravelling_time(long travelling_time) { this.travelling_time = travelling_time; }

        public String getOrigin_coords() {
            return origin_coords;
        }

        public void setOrigin_coords(String origin_coords) {
            this.origin_coords = origin_coords;
        }

        public String getDestiny_coords() {
            return destiny_coords;
        }

        public void setDestiny_coords(String destiny_coords) {
            this.destiny_coords = destiny_coords;
        }

        //função para retornar string do tipo "14:00 - 14:45"
        public String getTripTime() {
            return this.departure_time.toString() + " - " + this.arrival_time.toString();
        }

        // TO STRING
        @Override
        public String toString() {
            return "Trip{" +
                    "origin='" + origin + '\'' +
                    ", destiny='" + destiny + '\'' +
                    ", origin_address='" + origin_address + '\'' +
                    ", destiny_address='" + destiny_address + '\'' +
                    ", departure_time=" + departure_time +
                    ", arrival_time=" + arrival_time +
                    ", transport_type='" + transport_type + '\'' +
                    ", price=" + price +
                    ", travelling_time=" + travelling_time +
                    ", origin_coords='" + origin_coords + '\'' +
                    ", destiny_coords='" + destiny_coords + '\'' +
                    '}';
        }
    }