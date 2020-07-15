package com.example.usersync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class NewNurse extends Activity {
    EditText nNIC, nFirstName, nLastName, nAddress, nDOB,
            nPhoneNumber, nHospital, nDepartment, nUnit, nWard;
    Spinner nRole, nGender, nStatus;

    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_nurse);
        nNIC = (EditText) findViewById(R.id.editTextNNIC);
        nFirstName = (EditText) findViewById(R.id.editTextNFirstName);
        nLastName = (EditText) findViewById(R.id.editTextNLastName);
        nAddress = (EditText) findViewById(R.id.editTextNAddress);
        nDOB = (EditText) findViewById(R.id.editTextNDOB);
        nPhoneNumber = (EditText) findViewById(R.id.editTextNPhoneNumber);
        nHospital = (EditText) findViewById(R.id.editTextHospital);
        nDepartment = (EditText) findViewById(R.id.editTextDepartment);
        nUnit = (EditText) findViewById(R.id.editTextUnit);
        nWard = (EditText) findViewById(R.id.editTextWard);
        nGender = (Spinner) findViewById(R.id.spinnerNGender);
        nRole = (Spinner) findViewById(R.id.spinnerNRole);
        nStatus = (Spinner) findViewById(R.id.spinnerNStatus);
    }

    /**
     * Called when Save button is clicked
     * @param view
     */
    public void addNewNurse(View view) {
        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("nNIC", nNIC.getText().toString());
        queryValues.put("nFirstName", nFirstName.getText().toString());
        queryValues.put("nLastName", nLastName.getText().toString());
        queryValues.put("nGender", nGender.getSelectedItem().toString());
        queryValues.put("nAddress", nAddress.getText().toString());
        queryValues.put("nDOB", nDOB.getText().toString());
        queryValues.put("nPhoneNumber", nPhoneNumber.getText().toString());
        queryValues.put("nRole", nRole.getSelectedItem().toString());
        queryValues.put("nStatus", nStatus.getSelectedItem().toString());
        queryValues.put("Hospital", nHospital.getText().toString());
        queryValues.put("Department", nDepartment.getText().toString());
        queryValues.put("Unit", nUnit.getText().toString());
        queryValues.put("Ward", nWard.getText().toString());
        nNIC.getText().toString();
        if (nNIC.getText().toString().trim().length() != 0)
        {

            nFirstName.getText().toString();
            if (nFirstName.getText().toString().trim().length() != 0)
            {

                nLastName.getText().toString();
                if (nLastName.getText().toString().trim().length() != 0)
                {

                    nAddress.getText().toString();
                    if (nAddress.getText().toString().trim().length() != 0)
                    {

                        nDOB.getText().toString();
                        if (nDOB.getText().toString().trim().length() != 0)
                        {

                            nPhoneNumber.getText().toString();
                            if (nPhoneNumber.getText().toString().trim().length() != 0)
                            {

                                nHospital.getText().toString();
                                if (nHospital.getText().toString().trim().length() != 0)
                                {

                                    nDepartment.getText().toString();
                                    if (nDepartment.getText().toString().trim().length() != 0)
                                    {

                                        nUnit.getText().toString();
                                        if (nUnit.getText().toString().trim().length() != 0)
                                        {

                                            nWard.getText().toString();
                                            if (nWard.getText().toString().trim().length() != 0)
                                            {

                                                controller.insertNurse(queryValues);
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
                NurseActivity.class);
        startActivity(objIntent);
    }

    /**
     * Called when Cancel button is clicked
     * @param view
     */
    public void cancelAddNurse(View view) {
        this.callHomeActivity(view);
    }
}

