package com.example.and_pollen;

import com.example.and_pollen.responses.ConcentrationsResponse;

import java.util.ArrayList;

public class ConcentrationsSuperclass {
    private ArrayList<ConcentrationsResponse> results = new ArrayList<>();

    public ConcentrationsSuperclass(ArrayList<ConcentrationsResponse> results) {
        this.results = results;
    }

    public ConcentrationsSuperclass() {
    }

    public ArrayList<ConcentrationsResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<ConcentrationsResponse> results) {
        this.results = results;
    }

}
