package com.example.covidhospitalbedapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.covidhospitalbedapp.R;

public class AboutActivity extends AppCompatActivity {

    RelativeLayout adminbutton;
    int activateadminpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        adminbutton = findViewById(R.id.adminbutton);
        activateadminpanel=0;

        adminbutton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                activateadminpanel+=1;
                if(activateadminpanel==4){
                    activateadminpanel=0;
                    Intent adminact = new Intent(AboutActivity.this,AdminActivity.class);
                    startActivity(adminact);
                }
                return true;
            }
        });
    }
}