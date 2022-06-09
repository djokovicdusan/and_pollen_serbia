package com.example.and_pollen.model;
public class Pollen {
    private String date_after;
    private String date_before;
    private int location_ids;

    public Pollen(String date_after, String date_before, int location_ids) {
        this.date_after = date_after;
        this.date_before = date_before;
        this.location_ids = location_ids;
    }

    public String getDate_after() {
        return date_after;
    }

    public void setDate_after(String date_after) {
        this.date_after = date_after;
    }

    public String getDate_before() {
        return date_before;
    }

    public void setDate_before(String date_before) {
        this.date_before = date_before;
    }

    public int getLocation_ids() {
        return location_ids;
    }

    public void setLocation_ids(int location_ids) {
        this.location_ids = location_ids;
    }
}
