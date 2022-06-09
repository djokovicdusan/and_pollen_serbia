package com.example.and_pollen.responses;

public class AllergensResponse {
    private int id;
    private String name;
    private String localized_name;
    private int margine_top;
    private int margine_bottom;
    private int type;
    private int allergenicity;
    private String allergenicity_display;

    public AllergensResponse() {
    }

    public AllergensResponse(int id, String name, String localized_name, int margine_top, int margine_bottom, int type, int allergenicity, String allergenicity_display) {
        this.id = id;
        this.name = name;
        this.localized_name = localized_name;
        this.margine_top = margine_top;
        this.margine_bottom = margine_bottom;
        this.type = type;
        this.allergenicity = allergenicity;
        this.allergenicity_display = allergenicity_display;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalized_name() {
        return localized_name;
    }

    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }

    public int getMargine_top() {
        return margine_top;
    }

    public void setMargine_top(int margine_top) {
        this.margine_top = margine_top;
    }

    public int getMargine_bottom() {
        return margine_bottom;
    }

    public void setMargine_bottom(int margine_bottom) {
        this.margine_bottom = margine_bottom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAllergenicity() {
        return allergenicity;
    }

    public void setAllergenicity(int allergenicity) {
        this.allergenicity = allergenicity;
    }

    public String getAllergenicity_display() {
        return allergenicity_display;
    }

    public void setAllergenicity_display(String allergenicity_display) {
        this.allergenicity_display = allergenicity_display;
    }
    @Override
    public String toString() {
        return "AllergensResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", localized_name='" + localized_name + '\'' +
                ", margine_top=" + margine_top +
                ", margine_bottom=" + margine_bottom +
                ", type=" + type +
                ", allergenicity=" + allergenicity +
                ", allergenicity_display='" + allergenicity_display + '\'' +
                '}';
    }
}
