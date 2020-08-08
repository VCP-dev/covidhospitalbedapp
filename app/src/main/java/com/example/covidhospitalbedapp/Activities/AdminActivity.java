package com.example.covidhospitalbedapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidhospitalbedapp.APIcalls;
import com.example.covidhospitalbedapp.R;
import com.example.covidhospitalbedapp.RequestedValues.HospitalResult;
import com.example.covidhospitalbedapp.RequestedValues.LoginResult;
import com.example.covidhospitalbedapp.ReusableFunctions;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    Button newhospital;
    Button updatehospital;
    Button deletehospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        newhospital = findViewById(R.id.add_new_hospital);
        updatehospital = findViewById(R.id.update_hospital);
        deletehospital = findViewById(R.id.delete_hospital);

        newhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReusableFunctions.handlenewhospitaldialog(AdminActivity.this);

            }
        });

        updatehospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReusableFunctions.handleupdatehospitaldialog(AdminActivity.this);

            }
        });

        deletehospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReusableFunctions.deletedialog(AdminActivity.this);

            }
        });
    }



}