package com.example.covidhospitalbedapp.RequestedValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllRegisteredHospitals {

    @SerializedName("hospitals")
    @Expose
    private List<HospitalResult> hospitals = null;

    public List<HospitalResult> getHospitals() {
        return hospitals;
    }
}
