package com.example.covidhospitalbedapp;

import com.example.covidhospitalbedapp.RequestedValues.LoginResult;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

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

        @POST("/login")
        Call<LoginResult> excuteLogin(@Body HashMap<String,String> Map);

        @POST("/signup")
        Call<Void> excuteSignup(@Body HashMap<String,String> Map);
    }


}
