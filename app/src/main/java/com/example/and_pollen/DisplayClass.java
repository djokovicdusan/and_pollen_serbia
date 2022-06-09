package com.example.and_pollen;

import java.util.Date;

public class DisplayClass {
    private String allergen_name;
    private int value;
    private String date;

    public DisplayClass() {
    }

    public DisplayClass(String allergen_name, int value) {
        this.allergen_name = allergen_name;
        this.value = value;
    }
    public DisplayClass(String allergen_name, int value, String date){
        this.allergen_name = allergen_name;
        this.value = value;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAllergen_name() {
        return allergen_name;
    }

    public void setAllergen_name(String allergen_name) {
        this.allergen_name = allergen_name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return (date + " " + allergen_name + " " + value +"\n");
    }
}
