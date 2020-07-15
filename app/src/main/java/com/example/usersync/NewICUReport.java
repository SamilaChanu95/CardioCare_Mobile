package com.example.usersync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class NewICUReport extends Activity {
    EditText admitDate, room, code, diagnosis, neuro,
            cardiac, respiratory, ventilator, gi, gu,
            skin, drains, labs, meds, hemodynamics,
            todo, coremeasures, Patient;

    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_icureport);
        admitDate = (EditText) findViewById(R.id.editTextAdmitDate);
        room = (EditText) findViewById(R.id.editTextRoom);
        code = (EditText) findViewById(R.id.editTextCode);
        diagnosis = (EditText) findViewById(R.id.editTextDiagnosis);
        neuro = (EditText) findViewById(R.id.editTextNeuro);
        cardiac = (EditText) findViewById(R.id.editTextCardiac);
        respiratory = (EditText) findViewById(R.id.editTextRespiratory);
        ventilator = (EditText) findViewById(R.id.editTextVentilator);
        gi = (EditText) findViewById(R.id.editTextGI);
        gu = (EditText) findViewById(R.id.editTextGU);
        skin = (EditText) findViewById(R.id.editTextSkin);
        drains = (EditText) findViewById(R.id.editTextDrains);
        labs = (EditText) findViewById(R.id.editTextLabs);
        meds = (EditText) findViewById(R.id.editTextMeds);
        hemodynamics = (EditText) findViewById(R.id.editTextHemodynamics);
        todo = (EditText) findViewById(R.id.editTextToDo);
        coremeasures = (EditText) findViewById(R.id.editTextCoreMeasures);
        Patient = (EditText) findViewById(R.id.editTextPatient);
    }

    /**
     * Called when Save button is clicked
     * @param view
     */
    public void addNewICU(View view) {
        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("AdmitDate", admitDate.getText().toString());
        queryValues.put("Room", room.getText().toString());
        queryValues.put("Code", code.getText().toString());
        queryValues.put("Diagnosis", diagnosis.getText().toString());
        queryValues.put("Neuro", neuro.getText().toString());
        queryValues.put("Cardiac", cardiac.getText().toString());
        queryValues.put("Respiratory", respiratory.getText().toString());
        queryValues.put("Ventilator", ventilator.getText().toString());
        queryValues.put("GI", gi.getText().toString());
        queryValues.put("GU", gu.getText().toString());
        queryValues.put("Skin", skin.getText().toString());
        queryValues.put("Drains", drains.getText().toString());
        queryValues.put("Labs", labs.getText().toString());
        queryValues.put("Meds", meds.getText().toString());
        queryValues.put("Hemodynamics", hemodynamics.getText().toString());
        queryValues.put("ToDo", todo.getText().toString());
        queryValues.put("CoreMeasures", coremeasures.getText().toString());
        queryValues.put("Patient", Patient.getText().toString());
        admitDate.getText().toString();
        if (admitDate.getText().toString().trim().length() != 0)
        {

            room.getText().toString();
            if (room.getText().toString().trim().length() != 0)
            {

                code.getText().toString();
                if (code.getText().toString().trim().length() != 0)
                {

                    Patient.getText().toString();
                    if (Patient.getText().toString().trim().length() != 0)
                    {

                        controller.insertICU(queryValues);
                        this.callHomeActivity(view);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please enter Patient number", Toast.LENGTH_LONG).show();

                    }

                }
                else
                {

                    Toast.makeText(getApplicationContext(), "Please enter code number", Toast.LENGTH_LONG).show();

                }

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please enter room number", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please enter Admit Date", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Navigate to Home Screen
     * @param view
     */
    public void callHomeActivity(View view) {
        Intent objIntent = new Intent(getApplicationContext(),
                ICUReportActivity.class);
        startActivity(objIntent);
    }

    /**
     * Called when Cancel button is clicked
     * @param view
     */
    public void cancelAddICU(View view) {
        this.callHomeActivity(view);
    }
}
