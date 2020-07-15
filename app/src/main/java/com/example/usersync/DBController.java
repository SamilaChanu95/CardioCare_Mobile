package com.example.usersync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DBController extends SQLiteOpenHelper {
    public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryDoctor, queryConsultant, queryNurse, queryTechnician, queryPatient, queryICU;
        queryNurse = "CREATE TABLE nurse( nurseId INTEGER PRIMARY KEY AUTOINCREMENT, nNIC TEXT, nFirstName TEXT, nLastName TEXT, nGender TEXT, nAddress TEXT, nDOB TEXT, nPhoneNumber TEXT, nRole TEXT, nStatus TEXT, Hospital INTEGER, Department INTEGER, Unit INTEGER, Ward INTEGER, updateStatus TEXT )";
        queryDoctor = "CREATE TABLE doctor( doctorId INTEGER PRIMARY KEY AUTOINCREMENT, dNIC TEXT, dFirstName TEXT, dLastName TEXT, dGender TEXT, dAddress TEXT, dDOB TEXT, dPhoneNumber TEXT, dRole TEXT, dStatus TEXT, Hospital INTEGER, Department INTEGER, Unit INTEGER, Ward INTEGER, updateStatus TEXT )";
        queryConsultant = "CREATE TABLE consultant( consultantId INTEGER PRIMARY KEY AUTOINCREMENT, cNIC TEXT, cFirstName TEXT, cLastName TEXT, cGender TEXT, cAddress TEXT, cDOB TEXT, cPhoneNumber TEXT, cRole TEXT, cStatus TEXT, Hospital INTEGER, Department INTEGER, Unit INTEGER, Ward INTEGER, updateStatus TEXT )";
        queryTechnician = "CREATE TABLE technician( technicianId INTEGER PRIMARY KEY AUTOINCREMENT, tNIC TEXT, tFirstName TEXT, tLastName TEXT, tGender TEXT, tAddress TEXT, tDOB TEXT, tPhoneNumber TEXT, tRole TEXT, tStatus TEXT, Hospital INTEGER, Department INTEGER, Unit INTEGER, Ward INTEGER, updateStatus TEXT )";
        queryPatient = "CREATE TABLE patient( patientId INTEGER PRIMARY KEY AUTOINCREMENT, pNIC TEXT, pFirstName TEXT, pLastName TEXT, pGender TEXT, pAddress TEXT, pDOB TEXT, pPhoneNumber TEXT, pEmergencyDetails TEXT, pMedicalHistroy TEXT, pAllergicHistroy TEXT, pSurgicalHistroy TEXT, pDrugHistroy TEXT, pSocialHistroy TEXT, pExaminationDetails TEXT, pCurrentLocation TEXT, pStatus TEXT, pVisitingNumber INTEGER,  PatientType INTEGER, pWeight DOUBLE, pHeight DOUBLE, updateStatus TEXT )";
        queryICU = "CREATE TABLE icu( icureportId INTEGER PRIMARY KEY AUTOINCREMENT, AdmitDate TEXT, Room TEXT, Code TEXT, Diagnosis TEXT, Neuro TEXT, Cardiac TEXT, Respiratory TEXT, Ventilator TEXT, GI TEXT, GU TEXT, Skin TEXT, Drains TEXT, Labs TEXT, Meds TEXT, Hemodynamics TEXT, ToDo TEXT, CoreMeasures TEXT,  Patient INTEGER, updateStatus TEXT )";
        db.execSQL(queryDoctor);
        db.execSQL(queryConsultant);
        db.execSQL(queryNurse);
        db.execSQL(queryTechnician);
        db.execSQL(queryPatient);
        db.execSQL(queryICU);
    }

    // Called when the database needs to be upgraded, such as drop tables, add tables, or do anything else it needs to upgrade to the new schema version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryConsultant, queryDoctor, queryNurse, queryTechnician, queryPatient, queryICU;
        queryConsultant = "DROP TABLE IF EXISTS consultant";
        queryDoctor = "DROP TABLE IF EXISTS doctor";
        queryNurse = "DROP TABLE IF EXISTS nurse";
        queryTechnician = "DROP TABLE IF EXISTS technician";
        queryPatient = "DROP TABLE IF EXISTS patient";
        queryICU = "DROP TABLE IF EXISTS icu";
        db.execSQL(queryConsultant);
        db.execSQL(queryDoctor);
        db.execSQL(queryNurse);
        db.execSQL(queryTechnician);
        db.execSQL(queryPatient);
        db.execSQL(queryICU);
        onCreate(db);
    }

    // Insert user into SQLite DB
    public void insertConsultant(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cNIC", queryValues.get("cNIC"));
        contentValues.put("cFirstName", queryValues.get("cFirstName"));
        contentValues.put("cLastName", queryValues.get("cLastName"));
        contentValues.put("cGender", queryValues.get("cGender"));
        contentValues.put("cAddress", queryValues.get("cAddress"));
        contentValues.put("cDOB", queryValues.get("cDOB"));
        contentValues.put("cPhoneNumber", queryValues.get("cPhoneNumber"));
        contentValues.put("cRole", queryValues.get("cRole"));
        contentValues.put("cStatus", queryValues.get("cStatus"));
        contentValues.put("Hospital", queryValues.get("Hospital"));
        contentValues.put("Department", queryValues.get("Department"));
        contentValues.put("Unit", queryValues.get("Unit"));
        contentValues.put("Ward", queryValues.get("Ward"));
        contentValues.put("updateStatus", "no");
        database.insert("consultant", null, contentValues);
        database.close();
    }

    public void insertConsultants(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cNIC", queryValues.get("cNIC"));
        contentValues.put("cFirstName", queryValues.get("cFirstName"));
        contentValues.put("cLastName", queryValues.get("cLastName"));
        contentValues.put("cGender", queryValues.get("cGender"));
        contentValues.put("cAddress", queryValues.get("cAddress"));
        contentValues.put("cDOB", queryValues.get("cDOB"));
        contentValues.put("cPhoneNumber", queryValues.get("cPhoneNumber"));
        contentValues.put("cRole", queryValues.get("cRole"));
        contentValues.put("cStatus", queryValues.get("cStatus"));
        contentValues.put("Hospital", queryValues.get("Hospital"));
        contentValues.put("Department", queryValues.get("Department"));
        contentValues.put("Unit", queryValues.get("Unit"));
        contentValues.put("Ward", queryValues.get("Ward"));
        contentValues.put("updateStatus", "yes");
        database.insert("consultant", null, contentValues);
        database.close();
    }

    // Get the list of all Consultants
    public ArrayList<HashMap<String, String>> getAllConsultants() {

        // Initialize the HashMap inside ArrayList
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        //Query for select the all Consultants
        String selectQuery = "SELECT * FROM consultant";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("cNIC", cursor.getString(1));
                map.put("cFirstName", cursor.getString(2));
                map.put("cLastName", cursor.getString(3));
                map.put("cGender", cursor.getString(4));
                map.put("cAddress", cursor.getString(5));
                map.put("cDOB", cursor.getString(6));
                map.put("cPhoneNumber", cursor.getString(7));
                map.put("cRole", cursor.getString(8));
                map.put("cStatus", cursor.getString(9));
                map.put("Hospital", cursor.getString(10));
                map.put("Department", cursor.getString(11));
                map.put("Unit", cursor.getString(12));
                map.put("Ward", cursor.getString(13));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    // Compose JSON out of SQLite records
    public String composeJSONfromSQLiteConsultant() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM consultant where updateStatus = '" + "no" + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("consultantId", cursor.getString(0));
                map.put("cNIC", cursor.getString(1));
                map.put("cFirstName", cursor.getString(2));
                map.put("cLastName", cursor.getString(3));
                map.put("cGender", cursor.getString(4));
                map.put("cAddress", cursor.getString(5));
                map.put("cDOB", cursor.getString(6));
                map.put("cPhoneNumber", cursor.getString(7));
                map.put("cRole", cursor.getString(8));
                map.put("cStatus", cursor.getString(9));
                map.put("Hospital", cursor.getString(10));
                map.put("Department", cursor.getString(11));
                map.put("Unit", cursor.getString(12));
                map.put("Ward", cursor.getString(13));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(wordList);
    }

    // Get Sync status of SQLite
    public String getSyncStatusConsultant() {
        String msg = null;
        if (this.dbSyncCountConsultant() == 0) {
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        } else {
            msg = "DB Sync needed!";
        }
        return msg;
    }

    // Get SQLite records that are yet to be Synced
    public int dbSyncCountConsultant() {
        int count = 0;
        String selectQuery = "SELECT * FROM consultant where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    // Update Sync status against each User ID
    public void updateSyncStatusConsultant(String id, String status)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE consultant SET updateStatus = '"+ status +"' where consultantId="+"'"+ id +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    // Insert doctor into SQLite DB
    public void insertDoctor(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dNIC", queryValues.get("dNIC"));
        contentValues.put("dFirstName", queryValues.get("dFirstName"));
        contentValues.put("dLastName", queryValues.get("dLastName"));
        contentValues.put("dGender", queryValues.get("dGender"));
        contentValues.put("dAddress", queryValues.get("dAddress"));
        contentValues.put("dDOB", queryValues.get("dDOB"));
        contentValues.put("dPhoneNumber", queryValues.get("dPhoneNumber"));
        contentValues.put("dRole", queryValues.get("dRole"));
        contentValues.put("dStatus", queryValues.get("dStatus"));
        contentValues.put("Hospital", queryValues.get("Hospital"));
        contentValues.put("Department", queryValues.get("Department"));
        contentValues.put("Unit", queryValues.get("Unit"));
        contentValues.put("Ward", queryValues.get("Ward"));
        contentValues.put("updateStatus", "no");
        database.insert("doctor", null, contentValues);
        database.close();
    }

    public void insertDoctors(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dNIC", queryValues.get("dNIC"));
        contentValues.put("dFirstName", queryValues.get("dFirstName"));
        contentValues.put("dLastName", queryValues.get("dLastName"));
        contentValues.put("dGender", queryValues.get("dGender"));
        contentValues.put("dAddress", queryValues.get("dAddress"));
        contentValues.put("dDOB", queryValues.get("dDOB"));
        contentValues.put("dPhoneNumber", queryValues.get("dPhoneNumber"));
        contentValues.put("dRole", queryValues.get("dRole"));
        contentValues.put("dStatus", queryValues.get("dStatus"));
        contentValues.put("Hospital", queryValues.get("Hospital"));
        contentValues.put("Department", queryValues.get("Department"));
        contentValues.put("Unit", queryValues.get("Unit"));
        contentValues.put("Ward", queryValues.get("Ward"));
        contentValues.put("updateStatus", "yes");
        database.insert("doctor", null, contentValues);
        database.close();
    }

    // Get the list of all doctors
    public ArrayList<HashMap<String, String>> getAllDoctors() {

        // Initialize the HashMap inside ArrayList
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        //Query for select the all Consultants
        String selectQuery = "SELECT * FROM doctor";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("dNIC", cursor.getString(1));
                map.put("dFirstName", cursor.getString(2));
                map.put("dLastName", cursor.getString(3));
                map.put("dGender", cursor.getString(4));
                map.put("dAddress", cursor.getString(5));
                map.put("dDOB", cursor.getString(6));
                map.put("dPhoneNumber", cursor.getString(7));
                map.put("dRole", cursor.getString(8));
                map.put("dStatus", cursor.getString(9));
                map.put("Hospital", cursor.getString(10));
                map.put("Department", cursor.getString(11));
                map.put("Unit", cursor.getString(12));
                map.put("Ward", cursor.getString(13));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    // Compose JSON out of SQLite records
    public String composeJSONfromSQLiteDoctor() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM doctor where updateStatus = '" + "no" + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("doctorId", cursor.getString(0));
                map.put("dNIC", cursor.getString(1));
                map.put("dFirstName", cursor.getString(2));
                map.put("dLastName", cursor.getString(3));
                map.put("dGender", cursor.getString(4));
                map.put("dAddress", cursor.getString(5));
                map.put("dDOB", cursor.getString(6));
                map.put("dPhoneNumber", cursor.getString(7));
                map.put("dRole", cursor.getString(8));
                map.put("dStatus", cursor.getString(9));
                map.put("Hospital", cursor.getString(10));
                map.put("Department", cursor.getString(11));
                map.put("Unit", cursor.getString(12));
                map.put("Ward", cursor.getString(13));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(wordList);
    }

    // Get Sync status of SQLite
    public String getSyncStatusDoctor() {
        String msg = null;
        if (this.dbSyncCountDoctor() == 0) {
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        } else {
            msg = "DB Sync needed!";
        }
        return msg;
    }

    // Get SQLite records that are yet to be Synced
    public int dbSyncCountDoctor() {
        int count = 0;
        String selectQuery = "SELECT * FROM doctor where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    // Update Sync status against each User ID
    public void updateSyncStatusDoctor(String id, String status)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE doctor SET updateStatus = '"+ status +"' where doctorId="+"'"+ id +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    // Insert nurse into SQLite DB
    public void insertNurse(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nNIC", queryValues.get("nNIC"));
        contentValues.put("nFirstName", queryValues.get("nFirstName"));
        contentValues.put("nLastName", queryValues.get("nLastName"));
        contentValues.put("nGender", queryValues.get("nGender"));
        contentValues.put("nAddress", queryValues.get("nAddress"));
        contentValues.put("nDOB", queryValues.get("nDOB"));
        contentValues.put("nPhoneNumber", queryValues.get("nPhoneNumber"));
        contentValues.put("nRole", queryValues.get("nRole"));
        contentValues.put("nStatus", queryValues.get("nStatus"));
        contentValues.put("Hospital", queryValues.get("Hospital"));
        contentValues.put("Department", queryValues.get("Department"));
        contentValues.put("Unit", queryValues.get("Unit"));
        contentValues.put("Ward", queryValues.get("Ward"));
        contentValues.put("updateStatus", "no");
        database.insert("nurse", null, contentValues);
        database.close();
    }

    public void insertNurses(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nNIC", queryValues.get("nNIC"));
        contentValues.put("nFirstName", queryValues.get("nFirstName"));
        contentValues.put("nLastName", queryValues.get("nLastName"));
        contentValues.put("nGender", queryValues.get("nGender"));
        contentValues.put("nAddress", queryValues.get("nAddress"));
        contentValues.put("nDOB", queryValues.get("nDOB"));
        contentValues.put("nPhoneNumber", queryValues.get("nPhoneNumber"));
        contentValues.put("nRole", queryValues.get("nRole"));
        contentValues.put("nStatus", queryValues.get("nStatus"));
        contentValues.put("Hospital", queryValues.get("Hospital"));
        contentValues.put("Department", queryValues.get("Department"));
        contentValues.put("Unit", queryValues.get("Unit"));
        contentValues.put("Ward", queryValues.get("Ward"));
        contentValues.put("updateStatus", "yes");
        database.insert("nurse", null, contentValues);
        database.close();
    }

    // Get the list of all nurses
    public ArrayList<HashMap<String, String>> getAllNurses() {

        // Initialize the HashMap inside ArrayList
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        //Query for select the all Consultants
        String selectQuery = "SELECT * FROM nurse";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("nNIC", cursor.getString(1));
                map.put("nFirstName", cursor.getString(2));
                map.put("nLastName", cursor.getString(3));
                map.put("nGender", cursor.getString(4));
                map.put("nAddress", cursor.getString(5));
                map.put("nDOB", cursor.getString(6));
                map.put("nPhoneNumber", cursor.getString(7));
                map.put("nRole", cursor.getString(8));
                map.put("nStatus", cursor.getString(9));
                map.put("Hospital", cursor.getString(10));
                map.put("Department", cursor.getString(11));
                map.put("Unit", cursor.getString(12));
                map.put("Ward", cursor.getString(13));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    // Compose JSON out of SQLite records
    public String composeJSONfromSQLiteNurse() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM nurse where updateStatus = '" + "no" + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("nurseId", cursor.getString(0));
                map.put("nNIC", cursor.getString(1));
                map.put("nFirstName", cursor.getString(2));
                map.put("nLastName", cursor.getString(3));
                map.put("nGender", cursor.getString(4));
                map.put("nAddress", cursor.getString(5));
                map.put("nDOB", cursor.getString(6));
                map.put("nPhoneNumber", cursor.getString(7));
                map.put("nRole", cursor.getString(8));
                map.put("nStatus", cursor.getString(9));
                map.put("Hospital", cursor.getString(10));
                map.put("Department", cursor.getString(11));
                map.put("Unit", cursor.getString(12));
                map.put("Ward", cursor.getString(13));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(wordList);
    }

    // Get Sync status of SQLite
    public String getSyncStatusNurse() {
        String msg = null;
        if (this.dbSyncCountNurse() == 0) {
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        } else {
            msg = "DB Sync needed!";
        }
        return msg;
    }

    // Get SQLite records that are yet to be Synced
    public int dbSyncCountNurse() {
        int count = 0;
        String selectQuery = "SELECT * FROM nurse where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    // Update Sync status against each nurse ID
    public void updateSyncStatusNurse(String id, String status)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE nurse SET updateStatus = '"+ status +"' where nurseId="+"'"+ id +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    // Insert nurse into SQLite DB
    public void insertTechnician(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tNIC", queryValues.get("tNIC"));
        contentValues.put("tFirstName", queryValues.get("tFirstName"));
        contentValues.put("tLastName", queryValues.get("tLastName"));
        contentValues.put("tGender", queryValues.get("tGender"));
        contentValues.put("tAddress", queryValues.get("tAddress"));
        contentValues.put("tDOB", queryValues.get("tDOB"));
        contentValues.put("tPhoneNumber", queryValues.get("tPhoneNumber"));
        contentValues.put("tRole", queryValues.get("tRole"));
        contentValues.put("tStatus", queryValues.get("tStatus"));
        contentValues.put("Hospital", queryValues.get("Hospital"));
        contentValues.put("Department", queryValues.get("Department"));
        contentValues.put("Unit", queryValues.get("Unit"));
        contentValues.put("Ward", queryValues.get("Ward"));
        contentValues.put("updateStatus", "no");
        database.insert("technician", null, contentValues);
        database.close();
    }

    public void insertTechnicians(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tNIC", queryValues.get("tNIC"));
        contentValues.put("tFirstName", queryValues.get("tFirstName"));
        contentValues.put("tLastName", queryValues.get("tLastName"));
        contentValues.put("tGender", queryValues.get("tGender"));
        contentValues.put("tAddress", queryValues.get("tAddress"));
        contentValues.put("tDOB", queryValues.get("tDOB"));
        contentValues.put("tPhoneNumber", queryValues.get("tPhoneNumber"));
        contentValues.put("tRole", queryValues.get("tRole"));
        contentValues.put("tStatus", queryValues.get("tStatus"));
        contentValues.put("Hospital", queryValues.get("Hospital"));
        contentValues.put("Department", queryValues.get("Department"));
        contentValues.put("Unit", queryValues.get("Unit"));
        contentValues.put("Ward", queryValues.get("Ward"));
        contentValues.put("updateStatus", "yes");
        database.insert("technician", null, contentValues);
        database.close();
    }

    // Get the list of all technicians
    public ArrayList<HashMap<String, String>> getAllTechnicians() {

        // Initialize the HashMap inside ArrayList
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        //Query for select the all Consultants
        String selectQuery = "SELECT * FROM technician";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("tNIC", cursor.getString(1));
                map.put("tFirstName", cursor.getString(2));
                map.put("tLastName", cursor.getString(3));
                map.put("tGender", cursor.getString(4));
                map.put("tAddress", cursor.getString(5));
                map.put("tDOB", cursor.getString(6));
                map.put("tPhoneNumber", cursor.getString(7));
                map.put("tRole", cursor.getString(8));
                map.put("tStatus", cursor.getString(9));
                map.put("Hospital", cursor.getString(10));
                map.put("Department", cursor.getString(11));
                map.put("Unit", cursor.getString(12));
                map.put("Ward", cursor.getString(13));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    // Compose JSON out of SQLite records
    public String composeJSONfromSQLiteTechnician() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM technician where updateStatus = '" + "no" + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("technicianId", cursor.getString(0));
                map.put("tNIC", cursor.getString(1));
                map.put("tFirstName", cursor.getString(2));
                map.put("tLastName", cursor.getString(3));
                map.put("tGender", cursor.getString(4));
                map.put("tAddress", cursor.getString(5));
                map.put("tDOB", cursor.getString(6));
                map.put("tPhoneNumber", cursor.getString(7));
                map.put("tRole", cursor.getString(8));
                map.put("tStatus", cursor.getString(9));
                map.put("Hospital", cursor.getString(10));
                map.put("Department", cursor.getString(11));
                map.put("Unit", cursor.getString(12));
                map.put("Ward", cursor.getString(13));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(wordList);
    }

    // Get Sync status of SQLite
    public String getSyncStatusTechnician() {
        String msg = null;
        if (this.dbSyncCountTechnician() == 0) {
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        } else {
            msg = "DB Sync needed!";
        }
        return msg;
    }

    // Get SQLite records that are yet to be Synced
    public int dbSyncCountTechnician() {
        int count = 0;
        String selectQuery = "SELECT * FROM technician where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    // Update Sync status against each technician ID
    public void updateSyncStatusTechnician(String id, String status)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE technician SET updateStatus = '"+ status +"' where technicianId="+"'"+ id +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    // Insert icu report into SQLite DB
    public void insertICU(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AdmitDate", queryValues.get("AdmitDate"));
        contentValues.put("Room", queryValues.get("Room"));
        contentValues.put("Code", queryValues.get("Code"));
        contentValues.put("Diagnosis", queryValues.get("Diagnosis"));
        contentValues.put("Neuro", queryValues.get("Neuro"));
        contentValues.put("Cardiac", queryValues.get("Cardiac"));
        contentValues.put("Respiratory", queryValues.get("Respiratory"));
        contentValues.put("Ventilator", queryValues.get("Ventilator"));
        contentValues.put("GI", queryValues.get("GI"));
        contentValues.put("GU", queryValues.get("GU"));
        contentValues.put("Skin", queryValues.get("Skin"));
        contentValues.put("Drains", queryValues.get("Drains"));
        contentValues.put("Labs", queryValues.get("Labs"));
        contentValues.put("Meds", queryValues.get("Meds"));
        contentValues.put("Hemodynamics", queryValues.get("Hemodynamics"));
        contentValues.put("ToDo", queryValues.get("ToDo"));
        contentValues.put("CoreMeasures", queryValues.get("CoreMeasures"));
        contentValues.put("Patient", queryValues.get("Patient"));
        contentValues.put("updateStatus", "no");
        database.insert("icu", null, contentValues);
        database.close();
    }

    public void insertICUs(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AdmitDate", queryValues.get("AdmitDate"));
        contentValues.put("Room", queryValues.get("Room"));
        contentValues.put("Code", queryValues.get("Code"));
        contentValues.put("Diagnosis", queryValues.get("Diagnosis"));
        contentValues.put("Neuro", queryValues.get("Neuro"));
        contentValues.put("Cardiac", queryValues.get("Cardiac"));
        contentValues.put("Respiratory", queryValues.get("Respiratory"));
        contentValues.put("Ventilator", queryValues.get("Ventilator"));
        contentValues.put("GI", queryValues.get("GI"));
        contentValues.put("GU", queryValues.get("GU"));
        contentValues.put("Skin", queryValues.get("Skin"));
        contentValues.put("Drains", queryValues.get("Drains"));
        contentValues.put("Labs", queryValues.get("Labs"));
        contentValues.put("Meds", queryValues.get("Meds"));
        contentValues.put("Hemodynamics", queryValues.get("Hemodynamics"));
        contentValues.put("ToDo", queryValues.get("ToDo"));
        contentValues.put("CoreMeasures", queryValues.get("CoreMeasures"));
        contentValues.put("Patient", queryValues.get("Patient"));
        contentValues.put("updateStatus", "yes");
        database.insert("icu", null, contentValues);
        database.close();
    }

    // Get the list of all icu reports
    public ArrayList<HashMap<String, String>> getAllICUs() {

        // Initialize the HashMap inside ArrayList
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        //Query for select the all Consultants
        String selectQuery = "SELECT * FROM icu";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("AdmitDate", cursor.getString(1));
                map.put("Room", cursor.getString(2));
                map.put("Code", cursor.getString(3));
                map.put("Diagnosis", cursor.getString(4));
                map.put("Neuro", cursor.getString(5));
                map.put("Cardiac", cursor.getString(6));
                map.put("Respiratory", cursor.getString(7));
                map.put("Ventilator", cursor.getString(8));
                map.put("GI", cursor.getString(9));
                map.put("GU", cursor.getString(10));
                map.put("Skin", cursor.getString(11));
                map.put("Drains", cursor.getString(12));
                map.put("Labs", cursor.getString(13));
                map.put("Meds", cursor.getString(14));
                map.put("Hemodynamics", cursor.getString(15));
                map.put("ToDo", cursor.getString(16));
                map.put("CoreMeasures", cursor.getString(17));
                map.put("Patient", cursor.getString(18));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    // Compose JSON out of SQLite records
    public String composeJSONfromSQLiteICU() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM icu where updateStatus = '" + "no" + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("icureportId", cursor.getString(0));
                map.put("AdmitDate", cursor.getString(1));
                map.put("Room", cursor.getString(2));
                map.put("Code", cursor.getString(3));
                map.put("Diagnosis", cursor.getString(4));
                map.put("Neuro", cursor.getString(5));
                map.put("Cardiac", cursor.getString(6));
                map.put("Respiratory", cursor.getString(7));
                map.put("Ventilator", cursor.getString(8));
                map.put("GI", cursor.getString(9));
                map.put("GU", cursor.getString(10));
                map.put("Skin", cursor.getString(11));
                map.put("Drains", cursor.getString(12));
                map.put("Labs", cursor.getString(13));
                map.put("Meds", cursor.getString(14));
                map.put("Hemodynamics", cursor.getString(15));
                map.put("ToDo", cursor.getString(16));
                map.put("CoreMeasures", cursor.getString(17));
                map.put("Patient", cursor.getString(18));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(wordList);
    }

    // Get Sync status of SQLite
    public String getSyncStatusICU() {
        String msg = null;
        if (this.dbSyncCountICU() == 0) {
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        } else {
            msg = "DB Sync needed!";
        }
        return msg;
    }

    // Get SQLite records that are yet to be Synced
    public int dbSyncCountICU() {
        int count = 0;
        String selectQuery = "SELECT * FROM icu where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    // Update Sync status against each User ID
    public void updateSyncStatusICU(String id, String status)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE icu SET updateStatus = '"+ status +"' where icureportId="+"'"+ id +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    // Insert patient into SQLite DB
    public void insertPatient(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pNIC", queryValues.get("pNIC"));
        contentValues.put("pFirstName", queryValues.get("pFirstName"));
        contentValues.put("pLastName", queryValues.get("pLastName"));
        contentValues.put("pGender", queryValues.get("pGender"));
        contentValues.put("pAddress", queryValues.get("pAddress"));
        contentValues.put("pDOB", queryValues.get("pDOB"));
        contentValues.put("pPhoneNumber", queryValues.get("pPhoneNumber"));
        contentValues.put("pEmergencyDetails", queryValues.get("pEmergencyDetails"));
        contentValues.put("pMedicalHistroy", queryValues.get("pMedicalHistroy"));
        contentValues.put("pAllergicHistroy", queryValues.get("pAllergicHistroy"));
        contentValues.put("pSurgicalHistroy", queryValues.get("pSurgicalHistroy"));
        contentValues.put("pDrugHistroy", queryValues.get("pDrugHistroy"));
        contentValues.put("pSocialHistroy", queryValues.get("pSocialHistroy"));
        contentValues.put("pExaminationDetails", queryValues.get("pExaminationDetails"));
        contentValues.put("pCurrentLocation", queryValues.get("pCurrentLocation"));
        contentValues.put("pStatus", queryValues.get("pStatus"));
        contentValues.put("pVisitingNumber", queryValues.get("pVisitingNumber"));
        contentValues.put("PatientType", queryValues.get("PatientType"));
        contentValues.put("pWeight", queryValues.get("pWeight"));
        contentValues.put("pHeight", queryValues.get("pHeight"));
        contentValues.put("updateStatus", "no");
        database.insert("patient", null, contentValues);
        database.close();
    }

    public void insertPatients(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pNIC", queryValues.get("pNIC"));
        contentValues.put("pFirstName", queryValues.get("pFirstName"));
        contentValues.put("pLastName", queryValues.get("pLastName"));
        contentValues.put("pGender", queryValues.get("pGender"));
        contentValues.put("pAddress", queryValues.get("pAddress"));
        contentValues.put("pDOB", queryValues.get("pDOB"));
        contentValues.put("pPhoneNumber", queryValues.get("pPhoneNumber"));
        contentValues.put("pEmergencyDetails", queryValues.get("pEmergencyDetails"));
        contentValues.put("pMedicalHistroy", queryValues.get("pMedicalHistroy"));
        contentValues.put("pAllergicHistroy", queryValues.get("pAllergicHistroy"));
        contentValues.put("pSurgicalHistroy", queryValues.get("pSurgicalHistroy"));
        contentValues.put("pDrugHistroy", queryValues.get("pDrugHistroy"));
        contentValues.put("pSocialHistroy", queryValues.get("pSocialHistroy"));
        contentValues.put("pExaminationDetails", queryValues.get("pExaminationDetails"));
        contentValues.put("pCurrentLocation", queryValues.get("pCurrentLocation"));
        contentValues.put("pStatus", queryValues.get("pStatus"));
        contentValues.put("pVisitingNumber", queryValues.get("pVisitingNumber"));
        contentValues.put("PatientType", queryValues.get("PatientType"));
        contentValues.put("pWeight", queryValues.get("pWeight"));
        contentValues.put("pHeight", queryValues.get("pHeight"));
        contentValues.put("updateStatus", "yes");
        database.insert("patient", null, contentValues);
        database.close();
    }

    // Get the list of all Patients
    public ArrayList<HashMap<String, String>> getAllPatients() {

        // Initialize the HashMap inside ArrayList
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        //Query for select the all Consultants
        String selectQuery = "SELECT * FROM patient";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("pNIC", cursor.getString(1));
                map.put("pFirstName", cursor.getString(2));
                map.put("pLastName", cursor.getString(3));
                map.put("pGender", cursor.getString(4));
                map.put("pAddress", cursor.getString(5));
                map.put("pDOB", cursor.getString(6));
                map.put("pPhoneNumber", cursor.getString(7));
                map.put("pEmergencyDetails", cursor.getString(8));
                map.put("pMedicalHistroy", cursor.getString(9));
                map.put("pAllergicHistroy", cursor.getString(10));
                map.put("pSurgicalHistroy", cursor.getString(11));
                map.put("pDrugHistroy", cursor.getString(12));
                map.put("pSocialHistroy", cursor.getString(13));
                map.put("pExaminationDetails", cursor.getString(14));
                map.put("pCurrentLocation", cursor.getString(15));
                map.put("pStatus", cursor.getString(16));
                map.put("pVisitingNumber", cursor.getString(17));
                map.put("PatientType", cursor.getString(18));
                map.put("pWeight", cursor.getString(19));
                map.put("pHeight", cursor.getString(20));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    // Compose JSON out of SQLite records
    public String composeJSONfromSQLitePatient() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM patient where updateStatus = '" + "no" + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("patientId", cursor.getString(0));
                map.put("pNIC", cursor.getString(1));
                map.put("pFirstName", cursor.getString(2));
                map.put("pLastName", cursor.getString(3));
                map.put("pGender", cursor.getString(4));
                map.put("pAddress", cursor.getString(5));
                map.put("pDOB", cursor.getString(6));
                map.put("pPhoneNumber", cursor.getString(7));
                map.put("pEmergencyDetails", cursor.getString(8));
                map.put("pMedicalHistroy", cursor.getString(9));
                map.put("pAllergicHistroy", cursor.getString(10));
                map.put("pSurgicalHistroy", cursor.getString(11));
                map.put("pDrugHistroy", cursor.getString(12));
                map.put("pSocialHistroy", cursor.getString(13));
                map.put("pExaminationDetails", cursor.getString(14));
                map.put("pCurrentLocation", cursor.getString(15));
                map.put("pStatus", cursor.getString(16));
                map.put("pVisitingNumber", cursor.getString(17));
                map.put("PatientType", cursor.getString(18));
                map.put("pWeight", cursor.getString(19));
                map.put("pHeight", cursor.getString(20));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(wordList);
    }

    // Get Sync status of SQLite
    public String getSyncStatusPatient() {
        String msg = null;
        if (this.dbSyncCountPatient() == 0) {
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        } else {
            msg = "DB Sync needed!";
        }
        return msg;
    }

    // Get SQLite records that are yet to be Synced
    public int dbSyncCountPatient() {
        int count = 0;
        String selectQuery = "SELECT * FROM patient where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    // Update Sync status against each patient ID
    public void updateSyncStatusPatient(String id, String status)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE patient SET updateStatus = '"+ status +"' where patientId="+"'"+ id +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

}

