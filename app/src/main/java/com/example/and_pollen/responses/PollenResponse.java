package com.example.and_pollen.responses;
import java.util.ArrayList;

public class PollenResponse {
//    private int id;
    private int location;
    private String date;
    private ArrayList<Integer> concentrations = new ArrayList<Integer>();

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Integer> getConcentrations() {
        return concentrations;
    }

    public void setConcentrations(ArrayList<Integer> concentrations) {
        this.concentrations = concentrations;
    }

    @Override
    public String toString() {
        return "PollenResponse{" +
                "location=" + location +
                ", date='" + date + '\'' +
                ", concentrations=" + concentrations +
                '}';
    }
}
