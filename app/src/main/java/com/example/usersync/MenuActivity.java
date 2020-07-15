package com.example.usersync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private ImageButton imageButtonConsultant, imageButtonDoctor ,imageButtonNurse,
            imageButtonICUReport, imageButtonTechnician, imageButtonPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imageButtonConsultant = findViewById(R.id.imageButtonConsultant);
        imageButtonDoctor = findViewById(R.id.imageButtonDoctor);
        imageButtonNurse = findViewById(R.id.imageButtonNurse);
        imageButtonPatient = findViewById(R.id.imageButtonPatient);
        imageButtonTechnician = findViewById(R.id.imageButtonTechnician);
        imageButtonICUReport = findViewById(R.id.imageButtonICUReport);

        imageButtonConsultant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MenuActivity.this, ConsultantActivity.class);
                startActivity(mainIntent);
            }
        });

        imageButtonDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MenuActivity.this, DoctorActivity.class);
                startActivity(mainIntent);
            }
        });

        imageButtonNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MenuActivity.this, NurseActivity.class);
                startActivity(mainIntent);
            }
        });

        imageButtonTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MenuActivity.this, TechnicianActivity.class);
                startActivity(mainIntent);
            }
        });

        imageButtonPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MenuActivity.this, PatientActivity.class);
                startActivity(mainIntent);
            }
        });

        imageButtonICUReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MenuActivity.this, ICUReportActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
