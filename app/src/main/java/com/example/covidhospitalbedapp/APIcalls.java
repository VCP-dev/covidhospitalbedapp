package com.example.covidhospitalbedapp;

import com.example.covidhospitalbedapp.RequestedValues.AllRegisteredHospitals;
import com.example.covidhospitalbedapp.RequestedValues.HospitalResult;
import com.example.covidhospitalbedapp.RequestedValues.LoginResult;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class APIcalls {

    private static String BASE_URL="http://192.168.43.238:3300";

    public static RetrofitInterface retrofitInterface = null;

    public static RetrofitInterface getRetrofitInterface()
    {
        if(retrofitInterface==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(15,TimeUnit.SECONDS)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;
    }


    public interface RetrofitInterface {

        // to add a hospital
        @POST("/newhospital")
        Call<Void> executenewhospital(@Body HashMap<String,String> Map);

        // to get all registered hospitals
        @GET("/allregisteredhospitals")
        Call<AllRegisteredHospitals> getallhospitals();

        // to update the number of beds available in a hospital
        @PUT("/updatehospital")
        Call<Void> updatehospital(@Body HashMap<String,String> Map);

        // to delete a registered hospital
        @GET("/delete")
        Call<Void> deletehospital(@Body HashMap<String,String> Map);






        //  example calls...
        @POST("/login")
        Call<LoginResult> excuteLogin(@Body HashMap<String,String> Map);

        @POST("/signup")
        Call<Void> excuteSignup(@Body HashMap<String,String> Map);
    }


}
