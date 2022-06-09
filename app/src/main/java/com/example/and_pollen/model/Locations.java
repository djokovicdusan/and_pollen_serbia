package com.example.and_pollen.model;

public class Locations {
    private int id;
    private String name;
    private String description;
    // koristis serialized name ako se razlikuje polje u json-u i ova promenljiva


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
