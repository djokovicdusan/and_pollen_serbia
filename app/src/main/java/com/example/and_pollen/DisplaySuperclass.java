package com.example.and_pollen;

import java.util.ArrayList;

public class DisplaySuperclass {
    private String date;
    private ArrayList<DisplayClass> data = new ArrayList<>();

    public DisplaySuperclass() {
    }

    public DisplaySuperclass(String date, ArrayList<DisplayClass> data) {
        this.date = date;
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<DisplayClass> getData() {
        return data;
    }

    public void setData(ArrayList<DisplayClass> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return
                "date='" + date + '\'' +
                ", data=" + data +
                '}';
    }
}
