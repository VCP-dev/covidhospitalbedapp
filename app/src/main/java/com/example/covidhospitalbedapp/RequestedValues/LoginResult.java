package com.example.covidhospitalbedapp.RequestedValues;

import com.google.gson.annotations.SerializedName;

public class LoginResult {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

}
