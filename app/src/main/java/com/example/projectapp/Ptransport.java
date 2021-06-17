package com.example.projectapp;

public class Ptransport {
    private String city ;
    private String type;
    private String name;
    private String contact;
    public Ptransport(String city, String type, String name,String contact){
        this.city=city;
        this.type=type;
        this.name=name;
        this.contact=contact;



    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getType() {
        return type;
    }

    public void setType(String city) {
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String city) {
        this.name = name;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String city) {
        this.contact = contact;
    }


}
