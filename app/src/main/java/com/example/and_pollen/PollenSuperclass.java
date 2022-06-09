package com.example.and_pollen;

import com.example.and_pollen.responses.PollenResponse;

import java.util.ArrayList;

public class PollenSuperclass {
    private ArrayList<PollenResponse> results = new ArrayList<>();

    public PollenSuperclass(ArrayList<PollenResponse> results) {
        this.results = results;
    }

    public ArrayList<PollenResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<PollenResponse> results) {
        this.results = results;
    }
}
