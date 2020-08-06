package com.example.covidhospitalbedapp.RequestedValues;

import com.google.gson.annotations.SerializedName;

public class HospitalResult {

    @SerializedName("name")
    private String name;

    @SerializedName("numberOfBeds")
    private String numberOfBeds;

    @SerializedName("location")
    private String location;

    @SerializedName("description")
    private String description;

    public String getName() {
        return name;
    }

    public String getNumberOfBeds() {
        return numberOfBeds;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
