package com.example.usersync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class NewPatient extends Activity {
    EditText pNIC, pFirstName, pLastName, pAddress, pDOB,
            pPhoneNumber, pEmergencyDetails, pMedicalHistroy,
            pAllergicHistroy, pSurgicalHistroy, pDrugHistroy,
            pSocialHistroy, pExaminationDetails, pVisitingNumber,
            pWeight, pHeight;
    Spinner pCurrentLocation, pStatus, PatientType, pGender;

    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_patient);
        pNIC = (EditText) findViewById(R.id.editTextPNIC);
        pFirstName = (EditText) findViewById(R.id.editTextPFirstName);
        pLastName = (EditText) findViewById(R.id.editTextPLastName);
        pAddress = (EditText) findViewById(R.id.editTextPAddress);
        pDOB = (EditText) findViewById(R.id.editTextPDOB);
        pPhoneNumber = (EditText) findViewById(R.id.editTextPPhoneNumber);
        pEmergencyDetails = (EditText) findViewById(R.id.editTextEmergencyDetails);
        pMedicalHistroy = (EditText) findViewById(R.id.editTextMedicalHistry);
        pAllergicHistroy = (EditText) findViewById(R.id.editTextAllergicHistry);
        pSurgicalHistroy = (EditText) findViewById(R.id.editTextSurgicalHistry);
        pDrugHistroy = (EditText) findViewById(R.id.editTextDrugHistry);
        pSocialHistroy = (EditText) findViewById(R.id.editTextSocialHistry);
        pExaminationDetails = (EditText) findViewById(R.id.editTextExamDetails);
        pVisitingNumber = (EditText) findViewById(R.id.editTextVisitingNumber);
        pWeight = (EditText) findViewById(R.id.editTextWeight);
        pHeight = (EditText) findViewById(R.id.editTextHeight);
        pGender = (Spinner) findViewById(R.id.spinnerGender);
        pCurrentLocation = (Spinner) findViewById(R.id.spinnerCurrentLocation);
        pStatus = (Spinner) findViewById(R.id.spinnerStatus);
        PatientType = (Spinner) findViewById(R.id.spinnerPatientType);
    }

    /**
     * Called when Save button is clicked
     * @param view
     */
    public void addNewPatient(View view) {
        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("pNIC", pNIC.getText().toString());
        queryValues.put("pFirstName", pFirstName.getText().toString());
        queryValues.put("pLastName", pLastName.getText().toString());
        queryValues.put("pGender", pGender.getSelectedItem().toString());
        queryValues.put("pAddress", pAddress.getText().toString());
        queryValues.put("pDOB", pDOB.getText().toString());
        queryValues.put("pPhoneNumber", pPhoneNumber.getText().toString());
        queryValues.put("pEmergencyDetails", pEmergencyDetails.getText().toString());
        queryValues.put("pMedicalHistroy", pMedicalHistroy.getText().toString());
        queryValues.put("pAllergicHistroy", pAllergicHistroy.getText().toString());
        queryValues.put("pSurgicalHistroy", pSurgicalHistroy.getText().toString());
        queryValues.put("pDrugHistroy", pDrugHistroy.getText().toString());
        queryValues.put("pSocialHistroy", pSocialHistroy.getText().toString());
        queryValues.put("pExaminationDetails", pExaminationDetails.getText().toString());
        queryValues.put("pCurrentLocation", pCurrentLocation.getSelectedItem().toString());
        queryValues.put("pStatus", pStatus.getSelectedItem().toString());
        queryValues.put("pVisitingNumber", pVisitingNumber.getText().toString());
        queryValues.put("PatientType", PatientType.getSelectedItem().toString());
        queryValues.put("pWeight", pWeight.getText().toString());
        queryValues.put("pHeight", pHeight.getText().toString());
        pNIC.getText().toString();
        if (pNIC.getText().toString().trim().length() != 0)
        {

            pFirstName.getText().toString();
            if (pFirstName.getText().toString().trim().length() != 0)
            {

                pLastName.getText().toString();
                if (pLastName.getText().toString().trim().length() != 0)
                {

                    pAddress.getText().toString();
                    if (pAddress.getText().toString().trim().length() != 0)
                    {

                        pDOB.getText().toString();
                        if (pDOB.getText().toString().trim().length() != 0)
                        {

                            pPhoneNumber.getText().toString();
                            if (pPhoneNumber.getText().toString().trim().length() != 0)
                            {

                                pEmergencyDetails.getText().toString();
                                if (pEmergencyDetails.getText().toString().trim().length() != 0)
                                {

                                    pVisitingNumber.getText().toString();
                                    if (pVisitingNumber.getText().toString().trim().length() != 0)
                                    {

                                        pWeight.getText().toString();
                                        if (pWeight.getText().toString().trim().length() != 0)
                                        {

                                            pHeight.getText().toString();
                                            if (pHeight.getText().toString().trim().length() != 0)
                                            {

                                                pExaminationDetails.getText().toString();
                                                if (pExaminationDetails.getText().toString().trim().length() != 0)
                                                {

                                                    controller.insertPatient(queryValues);
                                                    this.callHomeActivity(view);

                                                }
                                                else
                                                {

                                                    Toast.makeText(getApplicationContext(), "Please enter examination details", Toast.LENGTH_LONG).show();

                                                }

                                            }
                                            else
                                            {

                                                Toast.makeText(getApplicationContext(), "Please enter height", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                        else
                                        {

                                            Toast.makeText(getApplicationContext(), "Please enter weight", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                    else
                                    {

                                        Toast.makeText(getApplicationContext(), "Please enter visiting number", Toast.LENGTH_LONG).show();

                                    }
                                }
                                else
                                {

                                    Toast.makeText(getApplicationContext(), "Please enter Emergency Details", Toast.LENGTH_LONG).show();

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
                PatientActivity.class);
        startActivity(objIntent);
    }

    /**
     * Called when Cancel button is clicked
     * @param view
     */
    public void cancelAddPatient(View view) {
        this.callHomeActivity(view);
    }
}
