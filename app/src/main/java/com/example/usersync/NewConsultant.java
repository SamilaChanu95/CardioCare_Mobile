package com.example.usersync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class NewConsultant extends Activity {
    EditText cNIC, cFirstName, cLastName, cAddress, cDOB,
            cPhoneNumber, cHospital, cDepartment, cUnit, cWard;
    Spinner cRole, cGender, cStatus;

    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_consultant);
        cNIC = (EditText) findViewById(R.id.editTextCNIC);
        cFirstName = (EditText) findViewById(R.id.editTextCFirstName);
        cLastName = (EditText) findViewById(R.id.editTextCLastName);
        cAddress = (EditText) findViewById(R.id.editTextCAddress);
        cDOB = (EditText) findViewById(R.id.editTextCDOB);
        cPhoneNumber = (EditText) findViewById(R.id.editTextCPhoneNumber);
        cHospital = (EditText) findViewById(R.id.editTextHospital);
        cDepartment = (EditText) findViewById(R.id.editTextDepartment);
        cUnit = (EditText) findViewById(R.id.editTextUnit);
        cWard = (EditText) findViewById(R.id.editTextWard);
        cGender = (Spinner) findViewById(R.id.spinnerCGender);
        cRole = (Spinner) findViewById(R.id.spinnerCRole);
        cStatus = (Spinner) findViewById(R.id.spinnerCStatus);
    }

    /**
     * Called when Save button is clicked
     * @param view
     */
    public void addNewConsultant(View view) {
        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("cNIC", cNIC.getText().toString());
        queryValues.put("cFirstName", cFirstName.getText().toString());
        queryValues.put("cLastName", cLastName.getText().toString());
        queryValues.put("cGender", cGender.getSelectedItem().toString());
        queryValues.put("cAddress", cAddress.getText().toString());
        queryValues.put("cDOB", cDOB.getText().toString());
        queryValues.put("cPhoneNumber", cPhoneNumber.getText().toString());
        queryValues.put("cRole", cRole.getSelectedItem().toString());
        queryValues.put("cStatus", cStatus.getSelectedItem().toString());
        queryValues.put("Hospital", cHospital.getText().toString());
        queryValues.put("Department", cDepartment.getText().toString());
        queryValues.put("Unit", cUnit.getText().toString());
        queryValues.put("Ward", cWard.getText().toString());
        cNIC.getText().toString();
        if (cNIC.getText().toString().trim().length() != 0)
        {

            cFirstName.getText().toString();
            if (cFirstName.getText().toString().trim().length() != 0)
            {

                cLastName.getText().toString();
                if (cLastName.getText().toString().trim().length() != 0)
                {

                    cAddress.getText().toString();
                    if (cAddress.getText().toString().trim().length() != 0)
                    {

                        cDOB.getText().toString();
                        if (cDOB.getText().toString().trim().length() != 0)
                        {

                            cPhoneNumber.getText().toString();
                            if (cPhoneNumber.getText().toString().trim().length() != 0)
                            {

                                cHospital.getText().toString();
                                if (cHospital.getText().toString().trim().length() != 0)
                                {

                                    cDepartment.getText().toString();
                                    if (cDepartment.getText().toString().trim().length() != 0)
                                    {

                                        cUnit.getText().toString();
                                        if (cUnit.getText().toString().trim().length() != 0)
                                        {

                                            cWard.getText().toString();
                                            if (cWard.getText().toString().trim().length() != 0)
                                            {

                                                controller.insertConsultant(queryValues);
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
                ConsultantActivity.class);
        startActivity(objIntent);
    }

    /**
     * Called when Cancel button is clicked
     * @param view
     */
    public void cancelAddConsultant(View view) {
        this.callHomeActivity(view);
    }
}
