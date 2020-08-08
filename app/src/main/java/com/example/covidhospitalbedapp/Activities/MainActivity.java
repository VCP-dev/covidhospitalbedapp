package com.example.covidhospitalbedapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.covidhospitalbedapp.APIcalls;
import com.example.covidhospitalbedapp.Adapters.HospitalAdapter;
import com.example.covidhospitalbedapp.Models.HospitalItem;
import com.example.covidhospitalbedapp.R;
import com.example.covidhospitalbedapp.RequestedValues.AllRegisteredHospitals;
import com.example.covidhospitalbedapp.RequestedValues.HospitalResult;
import com.example.covidhospitalbedapp.RequestedValues.LoginResult;
import com.example.covidhospitalbedapp.ReusableFunctions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HospitalAdapter.OnHospitalListener{


    ImageView aboutbutton;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FloatingActionButton refreshbutton;



    private List<HospitalResult> hospitals;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutbutton = findViewById(R.id.about_button);
        refreshbutton = findViewById(R.id.refreshbutton);

        getallhospitals(this);

        aboutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent about = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(about);
            }
        });

        refreshbutton.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0BA8DA")));
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getallhospitals(MainActivity.this);

            }
        });

    }


    // for all hospitals
    private void getallhospitals(final Context context){

        Toast.makeText(MainActivity.this,"Refreshing...",Toast.LENGTH_SHORT).show();

        Call<AllRegisteredHospitals> allRegisteredHospitals = APIcalls.getRetrofitInterface().getallhospitals();

        allRegisteredHospitals.enqueue(new Callback<AllRegisteredHospitals>() {
            @Override
            public void onResponse(Call<AllRegisteredHospitals> call, Response<AllRegisteredHospitals> response) {

                if(response.code()==200){

                    AllRegisteredHospitals registeredHospitals = response.body();

                    recyclerView = findViewById(R.id.hospitalrecycler);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    List<HospitalResult> sortedhospitallist = ReusableFunctions.sorthospitals(registeredHospitals.getHospitals());

                    setryclerview(sortedhospitallist,context);


                }
                else if(response.code()==400){

                    Toast.makeText(MainActivity.this,"No hospitals have been registered...",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<AllRegisteredHospitals> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("Error",t.getMessage());

            }
        });

    }



    void setryclerview(List<HospitalResult> sortedhospitallist,Context context){

        hospitals=sortedhospitallist;
        adapter = new HospitalAdapter(sortedhospitallist,context,this);
        recyclerView.setAdapter(adapter);

    }



    @Override
    public void OnHospitalClick(int position) {

        Bundle extras = new Bundle();
        extras.putString("name",hospitals.get(position).getName());
        extras.putString("location",hospitals.get(position).getLocation());
        extras.putString("number_of_beds",hospitals.get(position).getNumberOfBeds());
        extras.putString("description",hospitals.get(position).getDescription());
        Intent intent = new Intent(this,Description.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}