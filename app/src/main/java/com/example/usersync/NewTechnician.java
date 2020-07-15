package com.example.usersync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class NewTechnician extends Activity {
    EditText tNIC, tFirstName, tLastName, tAddress, tDOB,
            tPhoneNumber, tHospital, tDepartment, tUnit, tWard;
    Spinner tRole, tGender, tStatus;

    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_technician);
        tNIC = (EditText) findViewById(R.id.editTextTNIC);
        tFirstName = (EditText) findViewById(R.id.editTextTFirstName);
        tLastName = (EditText) findViewById(R.id.editTextTLastName);
        tAddress = (EditText) findViewById(R.id.editTextTAddress);
        tDOB = (EditText) findViewById(R.id.editTextTDOB);
        tPhoneNumber = (EditText) findViewById(R.id.editTextTPhoneNumber);
        tHospital = (EditText) findViewById(R.id.editTextHospital);
        tDepartment = (EditText) findViewById(R.id.editTextDepartment);
        tUnit = (EditText) findViewById(R.id.editTextUnit);
        tWard = (EditText) findViewById(R.id.editTextWard);
        tGender = (Spinner) findViewById(R.id.spinnerTGender);
        tRole = (Spinner) findViewById(R.id.spinnerTRole);
        tStatus = (Spinner) findViewById(R.id.spinnerTStatus);
    }

    /**
     * Called when Save button is clicked
     * @param view
     */
    public void addNewTechnician(View view) {
        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("tNIC", tNIC.getText().toString());
        queryValues.put("tFirstName", tFirstName.getText().toString());
        queryValues.put("tLastName", tLastName.getText().toString());
        queryValues.put("tGender", tGender.getSelectedItem().toString());
        queryValues.put("tAddress", tAddress.getText().toString());
        queryValues.put("tDOB", tDOB.getText().toString());
        queryValues.put("tPhoneNumber", tPhoneNumber.getText().toString());
        queryValues.put("tRole", tRole.getSelectedItem().toString());
        queryValues.put("tStatus", tStatus.getSelectedItem().toString());
        queryValues.put("Hospital", tHospital.getText().toString());
        queryValues.put("Department", tDepartment.getText().toString());
        queryValues.put("Unit", tUnit.getText().toString());
        queryValues.put("Ward", tWard.getText().toString());
        tNIC.getText().toString();
        if (tNIC.getText().toString().trim().length() != 0)
        {

            tFirstName.getText().toString();
            if (tFirstName.getText().toString().trim().length() != 0)
            {

                tLastName.getText().toString();
                if (tLastName.getText().toString().trim().length() != 0)
                {

                    tAddress.getText().toString();
                    if (tAddress.getText().toString().trim().length() != 0)
                    {

                        tDOB.getText().toString();
                        if (tDOB.getText().toString().trim().length() != 0)
                        {

                            tPhoneNumber.getText().toString();
                            if (tPhoneNumber.getText().toString().trim().length() != 0)
                            {

                                tHospital.getText().toString();
                                if (tHospital.getText().toString().trim().length() != 0)
                                {

                                    tDepartment.getText().toString();
                                    if (tDepartment.getText().toString().trim().length() != 0)
                                    {

                                        tUnit.getText().toString();
                                        if (tUnit.getText().toString().trim().length() != 0)
                                        {

                                            tWard.getText().toString();
                                            if (tWard.getText().toString().trim().length() != 0)
                                            {

                                                controller.insertTechnician(queryValues);
                                                this.callHomeActivity(view);

                                            }
                                            else
                                            {

                                                Toast.makeText(getApplicationContext(), "Please enter Ward number", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                        else
                                        {

                                            Toast.makeText(getApplicationContext(), "Please enter Unit number", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                    else
                                    {

                                        Toast.makeText(getApplicationContext(), "Please enter Department number", Toast.LENGTH_LONG).show();

                                    }
                                }
                                else
                                {

                                    Toast.makeText(getApplicationContext(), "Please enter Hospital number", Toast.LENGTH_LONG).show();

                                }
                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(), "Please enter Phone number", Toast.LENGTH_LONG).show();

                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Please enter Date of Birth", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_LONG).show();

                    }
                }
                else
                {

                    Toast.makeText(getApplicationContext(), "Please enter Last Name", Toast.LENGTH_LONG).show();

                }

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please enter First Name", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please enter NIC", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Navigate to Home Screen
     * @param view
     */
    public void callHomeActivity(View view) {
        Intent objIntent = new Intent(getApplicationContext(),
                TechnicianActivity.class);
        startActivity(objIntent);
    }

    /**
     * Called when Cancel button is clicked
     * @param view
     */
    public void cancelAddTechnician(View view) {
        this.callHomeActivity(view);
    }
}
