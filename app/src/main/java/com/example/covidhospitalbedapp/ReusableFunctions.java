package com.example.covidhospitalbedapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.example.covidhospitalbedapp.Activities.AdminActivity;
import com.example.covidhospitalbedapp.RequestedValues.HospitalResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReusableFunctions {

    public static List<HospitalResult> sorthospitals(List<HospitalResult> hospitals)
    {
        List<HospitalResult> hospitallist = hospitals;

        int n = hospitals.size();

        for(int i=0;i<n;i++){
            int min_idx=i;
            for(int j=i+1;j<n;j++){
                if(Integer.valueOf(hospitallist.get(j).getNumberOfBeds()) > Integer.valueOf(hospitallist.get(min_idx).getNumberOfBeds())){
                    min_idx=j;
                }
            }
            HospitalResult temp = hospitallist.get(min_idx);
            hospitallist.set(min_idx,hospitallist.get(i));
            hospitallist.set(i,temp);

        }

        return hospitallist;
    }





    ///   functions used by dialogs in admin activity

        public static void handlenewhospitaldialog(final Context context) {

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.newhospital_dialog,null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
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

                    Toast.makeText(context, "Registering hospital....", Toast.LENGTH_SHORT).show();

                    HashMap<String,String> map = new HashMap<>();

                    map.put("name",hospitalname.getText().toString().trim());
                    map.put("numberOfBeds",numberofbeds.getText().toString().trim());
                    map.put("location",hospitallocation.getText().toString().trim());
                    map.put("description",desofhosp.getText().toString().trim());

                    //  call for post request to server
                    //Call<Void> call = retrofitInterface.excuteSignup(map);

                    Call<Void> call = APIcalls.getRetrofitInterface().executenewhospital(map);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            if(response.code()==200){
                                Toast.makeText(context, "Added hospital successfully", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.code()==400){
                                Toast.makeText(context, "This hospital is already registered", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                            Log.e("Error",t.getMessage());
                        }
                    });

                }
            });

        }



    public static void handleupdatehospitaldialog(final Context context) {

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.updatehospital_dialog,null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(view).show();

            final EditText hospitalname = view.findViewById(R.id.hospnameupdateedit);
            final EditText hospitalnewnumofbeds = view.findViewById(R.id.numofbedsupdateedit);
            Button updatehospitalbutton = view.findViewById(R.id.updatehospitalbutton);

            updatehospitalbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, "updating "+hospitalname.getText().toString(), Toast.LENGTH_SHORT).show();

                    HashMap<String,String> map = new HashMap<>();

                    map.put("name",hospitalname.getText().toString().trim());
                    map.put("number",hospitalnewnumofbeds.getText().toString().trim());

                    Call<Void> call = APIcalls.getRetrofitInterface().updatehospital(map);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code()==200){
                                Toast.makeText(context, "Hospital updated successfully", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.code()==400){
                                Toast.makeText(context, "This hospital does not exist on record....", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                            Log.e("Error",t.getMessage());
                        }
                    });


                }
            });

        }



    public static void deletedialog(final Context context){

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.delete_dialog,null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(view).show();

            final EditText name = view.findViewById(R.id.delhospname);
            Button delbutton = view.findViewById(R.id.deletehospitalbutton);

            delbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, "deleting "+name.getText().toString(), Toast.LENGTH_SHORT).show();

                    HashMap<String,String> map = new HashMap<>();

                    map.put("name",name.getText().toString().trim());

                    //Toast.makeText(AdminActivity.this, map.toString(), Toast.LENGTH_SHORT).show();
                    Call<Void> delcall = APIcalls.getRetrofitInterface().deletehospital(map);

                    delcall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code()==200){
                                Toast.makeText(context, "Hospital deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.code()==400){
                                Toast.makeText(context, "This hospital does not exist on record....", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                            Log.e("Error",t.getMessage());
                        }
                    });

                }
            });
        }




}
