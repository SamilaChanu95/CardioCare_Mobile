package com.example.usersync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class NewDoctor extends Activity {
    EditText dNIC, dFirstName, dLastName, dAddress, dDOB,
            dPhoneNumber, dHospital, dDepartment, dUnit, dWard;
    Spinner dRole, dGender, dStatus;

    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_doctor);
        dNIC = (EditText) findViewById(R.id.editTextDNIC);
        dFirstName = (EditText) findViewById(R.id.editTextDFirstName);
        dLastName = (EditText) findViewById(R.id.editTextDLastName);
        dAddress = (EditText) findViewById(R.id.editTextDAddress);
        dDOB = (EditText) findViewById(R.id.editTextDDOB);
        dPhoneNumber = (EditText) findViewById(R.id.editTextDPhoneNumber);
        dHospital = (EditText) findViewById(R.id.editTextHospital);
        dDepartment = (EditText) findViewById(R.id.editTextDepartment);
        dUnit = (EditText) findViewById(R.id.editTextUnit);
        dWard = (EditText) findViewById(R.id.editTextWard);
        dGender = (Spinner) findViewById(R.id.spinnerDGender);
        dRole = (Spinner) findViewById(R.id.spinnerDRole);
        dStatus = (Spinner) findViewById(R.id.spinnerDStatus);
    }

    /**
     * Called when Save button is clicked
     * @param view
     */
    public void addNewDoctor(View view) {
        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("dNIC", dNIC.getText().toString());
        queryValues.put("dFirstName", dFirstName.getText().toString());
        queryValues.put("dLastName", dLastName.getText().toString());
        queryValues.put("dGender", dGender.getSelectedItem().toString());
        queryValues.put("dAddress", dAddress.getText().toString());
        queryValues.put("dDOB", dDOB.getText().toString());
        queryValues.put("dPhoneNumber", dPhoneNumber.getText().toString());
        queryValues.put("dRole", dRole.getSelectedItem().toString());
        queryValues.put("dStatus", dStatus.getSelectedItem().toString());
        queryValues.put("Hospital", dHospital.getText().toString());
        queryValues.put("Department", dDepartment.getText().toString());
        queryValues.put("Unit", dUnit.getText().toString());
        queryValues.put("Ward", dWard.getText().toString());
        dNIC.getText().toString();
        if (dNIC.getText().toString().trim().length() != 0)
        {

            dFirstName.getText().toString();
            if (dFirstName.getText().toString().trim().length() != 0)
            {

                dLastName.getText().toString();
                if (dLastName.getText().toString().trim().length() != 0)
                {

                    dAddress.getText().toString();
                    if (dAddress.getText().toString().trim().length() != 0)
                    {

                        dDOB.getText().toString();
                        if (dDOB.getText().toString().trim().length() != 0)
                        {

                            dPhoneNumber.getText().toString();
                            if (dPhoneNumber.getText().toString().trim().length() != 0)
                            {

                                dHospital.getText().toString();
                                if (dHospital.getText().toString().trim().length() != 0)
                                {

                                    dDepartment.getText().toString();
                                    if (dDepartment.getText().toString().trim().length() != 0)
                                    {

                                        dUnit.getText().toString();
                                        if (dUnit.getText().toString().trim().length() != 0)
                                        {

                                            dWard.getText().toString();
                                            if (dWard.getText().toString().trim().length() != 0)
                                            {

                                                controller.insertDoctor(queryValues);
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
                DoctorActivity.class);
        startActivity(objIntent);
    }

    /**
     * Called when Cancel button is clicked
     * @param view
     */
    public void cancelAddDoctor(View view) {
        this.callHomeActivity(view);
    }
}
