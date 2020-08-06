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

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    Button newhospital;
    Button updatehospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        newhospital = findViewById(R.id.add_new_hospital);
        updatehospital = findViewById(R.id.update_hospital);

        newhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlenewhospitaldialog();
            }
        });

        updatehospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleupdatehospitaldialog();
            }
        });
    }


    private void handlenewhospitaldialog() {

        View view = getLayoutInflater().inflate(R.layout.newhospital_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
        builder.setView(view).show();

        Button addhospital = view.findViewById(R.id.addhospitalbutton);
        addhospital.setText("Add hospital");
        final EditText hospitalname = view.findViewById(R.id.hospnameedit);
        final EditText hospitallocation = view.findViewById(R.id.locofhospedit);
        final EditText numberofbeds = view.findViewById(R.id.numofhospbededit);
        final EditText desofhosp = view.findViewById(R.id.desofhospedit);

        addhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AdminActivity.this, "Registering hospital....", Toast.LENGTH_SHORT).show();

                HashMap<String,String> map = new HashMap<>();

                map.put("name",hospitalname.getText().toString());
                map.put("numberOfBeds",numberofbeds.getText().toString());
                map.put("location",hospitallocation.getText().toString());
                map.put("description",desofhosp.getText().toString());

                //  call for post request to server
                //Call<Void> call = retrofitInterface.excuteSignup(map);

                Call<Void> call = APIcalls.getRetrofitInterface().executenewhospital(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.code()==200){
                            Toast.makeText(AdminActivity.this, "Added hospital successfully", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code()==400){
                            Toast.makeText(AdminActivity.this, "This hospital is already registered", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AdminActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.e("Error",t.getMessage());
                    }
                });

            }
        });

    }



    private void handleupdatehospitaldialog() {
        /*
        View view = getLayoutInflater().inflate(R.layout.newhospital_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button addhospital = view.findViewById(R.id.addhospitalbutton);
        addhospital.setText("Update hospital details");
        final EditText hospitalname = view.findViewById(R.id.hospnameedit);
        final EditText hospitallocation = view.findViewById(R.id.locofhospedit);
        final EditText numberofbeds = view.findViewById(R.id.numofhospbededit);
        final EditText desofhosp = view.findViewById(R.id.desofhospedit);
        */

    }
}