package com.hacks.yale.yhacks_2018.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacks.yale.yhacks_2018.R;

import java.util.ArrayList;

public class RuleFunctions extends AppCompatActivity {

    private static final String TAG = "SIGHH";
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] ydb = {"0003-0215", "0007-4641", "0009-0306"};// initialize array here


        //DUMMY RULES
        Rule[] rules = {new Rule(1, "0002-0800", "1", "greater", "age", 65)};

        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("hackathons");

        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("deadness");
        conditions.add ("fatigue");

        ArrayList<String> medications = new ArrayList<>();
        medications.add("0002-0800");
        medications.add("0002-3229");

        // DUMMY PATIENT
        PatientInfo patient1 = new PatientInfo("John Lee", 89, 65, "Male", 60, allergies , conditions, medications);

        // HERE HERE HERE - WARNING/FLAGS!!! warnings[0] is index of drug in ydb, warnings[1] is code
        int[][] warnings = new int[ydb.length * rules.length][2];
        Boolean beware = false;
        for (int i = 0; i < ydb.length; i++) {
            int k = i;
            for (Rule rule : rules) {
                beware = false;
                if (ydb[i].equals(rule.NCD)) {
                    if (rule.type == 1) {
                        if (rule.field.equals("age")) {
                            beware = evalType1(rule.predicate, patient1.getAge(), (int)rule.value);
                        } else if (rule.field.equals("gfr")) {
                            beware = evalType1(rule.predicate, patient1.getGFR(), (int)rule.value);
                        } else {
                            beware = evalType3(rule.predicate, patient1.getSex(), (String)rule.value);
                        }
                    } else {
                        if (rule.field.equals("meds")) {
                            beware = evalType2(patient1.getMedications(), (int)rule.value);
                        } else if (rule.field.equals("conditions")) {
                            beware = evalType2(patient1.getConditions(), (int)rule.value);
                        } else {
                            beware = evalType2(patient1.getAllergies(), (int)rule.value);
                        }
                    }
                }

                // HERE HERE CAN JUST UPDATE WARNINGS HERE INSIDE IF STATEMENT
                if (beware) {
                    warnings[k] = new int[]{i, Integer.parseInt(rule.code)};
                    k++;
                }
            }
        }
    }

    public Boolean evalType1(String predicate, int fieldVal, int value) {
        if (predicate.equals("eq")) {
            return ((fieldVal-value)==0);
        } else if (predicate.equals("lesser")) {
            return ((fieldVal-value)<0);
        } else {
            return ((fieldVal-value)>0);
        }
    }

    public Boolean evalType2(ArrayList<String> val, int value) {
        for (int i = 0; i< val.size(); i++) {
            if (Integer.parseInt(val.get(i))==value) {
                return true;
            }
        }

        return false;
    }

    public Boolean evalType3(String predicate, String fieldVal, String value) {
        if (predicate.equals("eq")) {
            return fieldVal.equals(value);
        }
        return null;
    }

    public String inPatientList(String field, String name) {
        return "sigh";
    }

}
