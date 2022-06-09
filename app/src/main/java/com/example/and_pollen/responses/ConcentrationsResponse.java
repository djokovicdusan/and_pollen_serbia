package com.example.and_pollen.responses;

public class ConcentrationsResponse {

//    {
//        "id": 490453,
//            "allergen": 3,
//            "value": 207,
//            "pollen": 38736
//    }
    private int id;
    private int allergen;
    private int value;
    private int pollen;


    public ConcentrationsResponse(int id, int allergen, int value, int pollen) {
        this.id = id;
        this.allergen = allergen;
        this.value = value;
        this.pollen = pollen;
    }

    public ConcentrationsResponse() {
    }

    @Override
    public String toString() {
        return "ConcentrationsResponse{" +
                "id=" + id +
                ", allergen=" + allergen +
                ", value=" + value +
                ", pollen=" + pollen +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAllergen() {
        return allergen;
    }

    public void setAllergen(int allergen) {
        this.allergen = allergen;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPollen() {
        return pollen;
    }

    public void setPollen(int pollen) {
        this.pollen = pollen;
    }
}
