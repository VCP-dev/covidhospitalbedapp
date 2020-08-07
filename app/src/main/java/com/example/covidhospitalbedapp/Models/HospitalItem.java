package com.example.covidhospitalbedapp.Models;

public class HospitalItem {

    private String nameofhospital;
    private String numberofbeds;
    private String location;
    private String description;

    public HospitalItem(String nameofhospital, String numberofbeds, String location, String description) {
        this.nameofhospital = nameofhospital;
        this.numberofbeds = numberofbeds;
        this.location = location;
        this.description = description;
    }

    public String getNameofhospital() {
        return nameofhospital;
    }

    public String getNumberofbeds() {
        return numberofbeds;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
