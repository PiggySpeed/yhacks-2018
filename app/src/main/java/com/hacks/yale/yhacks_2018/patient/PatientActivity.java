package com.hacks.yale.yhacks_2018.patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hacks.yale.yhacks_2018.R;
import com.hacks.yale.yhacks_2018.firebase.Upload;

import java.util.ArrayList;

public class PatientProfile extends AppCompatActivity {
    Upload uploadManager;
    String name;
    int age;
    int weight;
    String sex;
    int GFR;
    ArrayList<String> allergies;
    ArrayList<String> conditions;
    ArrayList<String> medications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        uploadManager = new Upload();

        name = null;
        age = -1;
        weight = -1;
        sex = null;
        GFR = -1;
        allergies = null;
        conditions = null;
        medications = null;
    }

    public void updateAge(int age) {
        uploadManager.uploadParts(null, age, -1, null, -1, null, null, null);
    }

    public void updateWeight(int weight) {
        uploadManager.uploadParts(null, -1, 55, null, -1, null, null, null);
    }

    public void updateGFR(int GFR) {
        uploadManager.uploadParts(null, -1, -1,null, GFR, null, null, null);
    }

    public void updateAllergies(ArrayList<String> allergies) {
        uploadManager.uploadParts(null, -1, -1,null, -1, allergies, null, null);
    }

    public void updateConditions(ArrayList<String> conditions) {
        uploadManager.uploadParts(null, -1, -1,null, -1, null, conditions, null);
    }

    public void updateMedications(ArrayList<String> medications) {
        uploadManager.uploadParts(null, -1, -1,null, -1, null, null, medications);
    }

    public String getName()                     { return name; }
    public int getAge()                         { return age; }
    public String getSex()                      { return sex; }
    public int getGFR()                         { return GFR; }
    public ArrayList<String> getAllergies()     { return allergies; }
    public ArrayList<String> getConditions()    { return conditions; }
    public ArrayList<String> getMedications()   { return medications; }
}
