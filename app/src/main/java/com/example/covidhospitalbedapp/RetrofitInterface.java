package com.example.covidhospitalbedapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> excuteLogin(@Body HashMap<String,String> Map);

    @POST("/signup")
    Call<Void> excuteSignup(@Body HashMap<String,String> Map);
}
