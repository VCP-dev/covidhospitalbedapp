package com.example.covidhospitalbedapp;

import com.example.covidhospitalbedapp.RequestedValues.HospitalResult;

import java.util.Collections;
import java.util.List;

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

}
