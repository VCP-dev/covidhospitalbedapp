package com.example.covidhospitalbedapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidhospitalbedapp.Models.HospitalItem;
import com.example.covidhospitalbedapp.R;
import com.example.covidhospitalbedapp.RequestedValues.HospitalResult;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {


    List<HospitalResult> hospitalist;
    private Context context;


    public HospitalAdapter(List<HospitalResult> hospitalist,Context context){
        this.hospitalist=hospitalist;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospital_item,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HospitalResult hospital = hospitalist.get(position);

        holder.nameofhospital.setText(hospital.getName());
        holder.numberofbeds.setText(hospital.getNumberOfBeds());
        holder.location.setText(hospital.getLocation());

    }

    @Override
    public int getItemCount() {
        return hospitalist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameofhospital;
        TextView location;
        TextView numberofbeds;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            nameofhospital = itemView.findViewById(R.id.hospitalname);
            location = itemView.findViewById(R.id.hospitallocation);
            numberofbeds = itemView.findViewById(R.id.numberofbeds);
        }
    }


}
