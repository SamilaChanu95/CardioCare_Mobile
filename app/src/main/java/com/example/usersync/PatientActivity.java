package com.example.usersync;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PatientActivity extends AppCompatActivity {

    // Db class to perform DB related operations by create DBController object
    DBController controller = new DBController(this);

    // Progress Dialog Object
    ProgressDialog progressDialog;

    HashMap<String, String> queryValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        // Get all user records from SQLite DB
        ArrayList<HashMap<String, String>> userList = controller.getAllPatients();

        if (userList.size() != 0)
        {
            // Set the User Array list in ListView
            ListAdapter adapter = new SimpleAdapter(PatientActivity.this, userList, R.layout.view_patient_entry, new String[] { "patientId","pFirstName", "pAddress", "pPhoneNumber"}, new int[] {R.id.patientId, R.id.patientName, R.id.patientAddress, R.id.patientPhone});
            ListView myList=(ListView)findViewById(android.R.id.list);
            myList.setAdapter(adapter);

            //Display Sync status of SQLite DB
            Toast.makeText(getApplicationContext(), controller.getSyncStatusPatient(), Toast.LENGTH_LONG).show();
        }

        //Initialize Progress Dialog properties
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Syncing SQLite Data with Remote MySQL DB. Please wait...");
        progressDialog.setCancelable(false);

        // BroadCase Receiver Intent Object
        Intent alarmIntent = new Intent(getApplicationContext(), SampleBCPatient.class);
        // Pending Intent Object
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Alarm Manager Object
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        // Alarm Manager calls BroadCast for every Ten seconds (10 * 1000), BroadCase further calls service to check if new records are inserted in
        // Remote MySQL DB
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 5000, 10 * 1000, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //When Sync action button is clicked
        if (id == R.id.refresh) {

            //Sync SQLite DB data to remote MySQL DB
            syncSQLiteMySQLDB();

            //Sync SQLite DB data with remote MySQL DB
            syncMySQLDBSQLite();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //AddConsultant method getting called on clicking (+) button
    public void addPatient(View view) {
        Intent objIntent = new Intent(getApplicationContext(), NewPatient.class);
        startActivity(objIntent);
    }

    //backMenu method getting called on clicking (<-) button
    public void backMenu(View view) {
        Intent objIntent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(objIntent);
    }

    public void syncSQLiteMySQLDB(){

        // Create AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        ArrayList<HashMap<String, String>> userList =  controller.getAllPatients();
        if(userList.size()!=0){
            if(controller.dbSyncCountPatient() != 0){
                progressDialog.show();
                params.put("usersJSON", controller.composeJSONfromSQLitePatient());
                client.post("http://192.168.1.101/cardiocare_mobile/patient/addpatient.php",params ,new AsyncHttpResponseHandler() {
                    public void onSuccess(String response) {
                        System.out.println(response);
                        progressDialog.hide();
                        try {
                            JSONArray arr = new JSONArray(response);
                            System.out.println(arr.length());
                            for(int i=0; i<arr.length();i++){
                                JSONObject obj = (JSONObject)arr.get(i);
                                System.out.println(obj.get("id"));
                                System.out.println(obj.get("status"));
                                controller.updateSyncStatusPatient(obj.get("id").toString(),obj.get("status").toString());
                            }
                            Toast.makeText(getApplicationContext(), "SQLite Data updated successfully", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getApplicationContext(), "Error occurred [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                    public void onFailure(int statusCode, Throwable error,
                                          String content) {
                        // TODO Auto-generated method stub
                        progressDialog.hide();
                        if(statusCode == 404){
                            Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                        }else if(statusCode == 500){
                            Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Unexpected Error occurred! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(), "SQLite and Remote MySQL DBs are in Sync!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "No data in SQLite DB, please do enter User name to perform Sync action", Toast.LENGTH_LONG).show();
        }
    }

    // Method to Sync MySQL to SQLite DB
    public void syncMySQLDBSQLite() {
        // Create AsycHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();

        //Log.d(TAG, "Method is connected successfully");

        // Http Request Params Object
        RequestParams params = new RequestParams();
        // Show ProgressBar
        progressDialog.show();
        // Make Http call to getusers.php
        client.post("http://192.168.1.101/cardiocare_mobile/patient/getpatients.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                // Hide ProgressBar
                progressDialog.hide();
                // Update SQLite DB with response sent by getpatients.php
                updateSQLite(response);
            }
            // When error occured
            @Override
            public void onFailure(int statusCode, Throwable error, String content) {
                // TODO Auto-generated method stub
                // Hide ProgressBar
                progressDialog.hide();
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occurred! [Most common Error: Device might not be connected to Internet]",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void updateSQLite(String response){
        ArrayList<HashMap<String, String>> usersynclist;
        usersynclist = new ArrayList<HashMap<String, String>>();
        // Create GSON object
        Gson gson = new GsonBuilder().create();
        try {
            // Extract JSON array from the response
            JSONArray arr = new JSONArray(response);
            System.out.println(arr.length());
            // If no of array elements is not zero
            if(arr.length() != 0){
                // Loop through each array element, get JSON object which has userid and username and userNumber and userSubject
                for (int i = 0; i < arr.length(); i++) {
                    // Get JSON object
                    JSONObject obj = (JSONObject) arr.get(i);
                    //System.out.println(obj.get("patientId"));
                    System.out.println(obj.get("pNIC"));
                    System.out.println(obj.get("pFirstName"));
                    System.out.println(obj.get("pLastName"));
                    System.out.println(obj.get("pGender"));
                    System.out.println(obj.get("pAddress"));
                    System.out.println(obj.get("pDOB"));
                    System.out.println(obj.get("pPhoneNumber"));
                    System.out.println(obj.get("pEmergencyDetails"));
                    System.out.println(obj.get("pMedicalHistroy"));
                    System.out.println(obj.get("pAllergicHistroy"));
                    System.out.println(obj.get("pSurgicalHistroy"));
                    System.out.println(obj.get("pDrugHistroy"));
                    System.out.println(obj.get("pSocialHistroy"));
                    System.out.println(obj.get("pExaminationDetails"));
                    System.out.println(obj.get("pCurrentLocation"));
                    System.out.println(obj.get("pStatus"));
                    System.out.println(obj.get("pVisitingNumber"));
                    System.out.println(obj.get("PatientType"));
                    System.out.println(obj.get("pWeight"));
                    System.out.println(obj.get("pHeight"));
                    // DB QueryValues Object to insert into SQLite
                    queryValues = new HashMap<String, String>();
                    // Add consultantID extracted from Object
                    //queryValues.put("userId", obj.get("userId").toString());
                    // Add cNIC extracted from Object
                    queryValues.put("pNIC", obj.get("pNIC").toString());
                    // Add cFirstName extracted from Object
                    queryValues.put("pFirstName", obj.get("pFirstName").toString());
                    // Add cLastName extracted from Object
                    queryValues.put("pLastName", obj.get("pLastName").toString());
                    queryValues.put("pGender", obj.get("pGender").toString());
                    queryValues.put("pAddress", obj.get("pAddress").toString());
                    queryValues.put("pDOB", obj.get("pDOB").toString());
                    queryValues.put("pPhoneNumber", obj.get("pPhoneNumber").toString());
                    queryValues.put("pEmergencyDetails", obj.get("pEmergencyDetails").toString());
                    queryValues.put("pMedicalHistroy", obj.get("pMedicalHistroy").toString());
                    queryValues.put("pAllergicHistroy", obj.get("pAllergicHistroy").toString());
                    queryValues.put("pSurgicalHistroy", obj.get("pSurgicalHistroy").toString());
                    queryValues.put("pDrugHistroy", obj.get("pDrugHistroy").toString());
                    queryValues.put("pSocialHistroy", obj.get("pSocialHistroy").toString());
                    queryValues.put("pExaminationDetails", obj.get("pExaminationDetails").toString());
                    queryValues.put("pCurrentLocation", obj.get("pCurrentLocation").toString());
                    queryValues.put("pStatus", obj.get("pStatus").toString());
                    queryValues.put("pVisitingNumber", obj.get("pVisitingNumber").toString());
                    queryValues.put("PatientType", obj.get("PatientType").toString());
                    queryValues.put("pWeight", obj.get("pWeight").toString());
                    queryValues.put("pHeight", obj.get("pHeight").toString());
                    // Add userSubject
                    queryValues.put("updateStatus", "1");
                    // Insert User into SQLite DB
                    controller.insertPatients(queryValues);
                    HashMap<String, String> map = new HashMap<String, String>();
                    // Add status for each User in Hashmap
                    map.put("Id", obj.get("patientId").toString());
                    map.put("status", "1");
                    usersynclist.add(map);
                    Toast.makeText(getApplicationContext(),	"MySQL Data updated successfully", Toast.LENGTH_LONG).show();
                }
                // Inform Remote MySQL DB about the completion of Sync activity by passing Sync status of Users
                updateMySQLSyncSts(gson.toJson(usersynclist));
                // Reload the Main Activity
                reloadActivity();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Method to inform remote MySQL DB about completion of Sync activity
    public void updateMySQLSyncSts(String json) {
        System.out.println(json);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("syncsts", json);
        // Make Http call to updatesyncsts.php with JSON parameter which has Sync statuses of Users
        client.post("http://192.168.1.101/cardiocare_mobile/patient/updatesyncsts.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(),	"MySQL DB has been informed about Sync activity", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Throwable error, String content) {
                Toast.makeText(getApplicationContext(), "Error Occurred in Sync Status Update", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Reload MainActivity
    public void reloadActivity() {
        Intent objIntent = new Intent(getApplicationContext(), PatientActivity.class);
        startActivity(objIntent);
    }

}