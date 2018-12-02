package com.hacks.yale.yhacks_2018.patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.hacks.yale.yhacks_2018.R;
import com.hacks.yale.yhacks_2018.firebase.PatientInfo;
import com.hacks.yale.yhacks_2018.firebase.Upload;

import java.util.ArrayList;

public class PatientActivity extends AppCompatActivity {
    Upload uploadManager;
    String name;
    int age;
    int weight;
    String sex;
    int GFR;

    ArrayList<String> allergies;
    ArrayList<String> conditions;
    ArrayList<String> medications;

    public PatientActivity() {}

    public void loadPatient(PatientInfo patient) {
        name = patient.getName();
        age = patient.getAge();
        weight = patient.getWeight();
        sex = patient.getSex();
        GFR = patient.getGFR();
        allergies = patient.getAllergies();
        conditions = patient.getConditions();
        medications = patient.getMedications();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
//        uploadManager = new Upload();

//        EditText inputAge = findViewById(R.id.input_name);
//        inputAge.setText(age);
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
}
