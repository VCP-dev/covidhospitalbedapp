package com.example.covidhospitalbedapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidhospitalbedapp.R;

public class Description extends AppCompatActivity {

    TextView hospitalname;
    TextView location;
    TextView numberofbeds;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Bundle extras = getIntent().getExtras();

        hospitalname = findViewById(R.id.title);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);
        numberofbeds = findViewById(R.id.numberofbedsavailable);

        hospitalname.setText(extras.get("name").toString());
        location.setText(extras.get("location").toString());
        description.setText(extras.get("description").toString());
        numberofbeds.setText(extras.get("number_of_beds").toString()+" currently available");

        if(Integer.valueOf(extras.get("number_of_beds").toString())<=10){
            numberofbeds.setTextColor(Color.parseColor("#FF0000"));
        }



    }
}