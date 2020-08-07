package com.example.covidhospitalbedapp.Adapters;

import android.content.Context;
import android.graphics.Color;
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


    private List<HospitalResult> hospitalist;
    private Context context;
    private OnHospitalListener mOnhospitalListener;


    public HospitalAdapter(List<HospitalResult> hospitalist,Context context,OnHospitalListener onHospitalListener){
        this.hospitalist=hospitalist;
        this.context=context;
        this.mOnhospitalListener=onHospitalListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospital_item,parent,false);

        return new ViewHolder(view,mOnhospitalListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HospitalResult hospital = hospitalist.get(position);

        holder.nameofhospital.setText(hospital.getName());
        holder.numberofbeds.setText(hospital.getNumberOfBeds());
        if(Integer.valueOf(hospital.getNumberOfBeds())<=10){
            holder.numberofbeds.setTextColor(Color.parseColor("#FF0000"));
        }
        holder.location.setText(hospital.getLocation());

    }

    @Override
    public int getItemCount() {
        return hospitalist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nameofhospital;
        TextView location;
        TextView numberofbeds;
        OnHospitalListener onHospitalListener;


        public ViewHolder(@NonNull View itemView,OnHospitalListener onHospitalListener)
        {
            super(itemView);

            nameofhospital = itemView.findViewById(R.id.hospitalname);
            location = itemView.findViewById(R.id.hospitallocation);
            numberofbeds = itemView.findViewById(R.id.numberofbeds);
            this.onHospitalListener = onHospitalListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onHospitalListener.OnHospitalClick(getAdapterPosition());
        }
    }



    public interface OnHospitalListener{
        void OnHospitalClick(int position);
    }


}
