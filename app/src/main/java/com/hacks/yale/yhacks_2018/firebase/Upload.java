package com.hacks.yale.yhacks_2018.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Upload {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "SIGHH";

    //upload new patient
    public void uploadNew ( PatientInfo patient) {
        db.collection("patient").document(patient.getName()).set(patient);
    }

    //update patient info
    public void uploadParts (String name, int age, int weight, String sex, int GFR, ArrayList<String> allergies, ArrayList<String> conditions, ArrayList<String> medications) {
        Map<String, Object> docData = new HashMap<>();

        if (name != null) {
            docData.put("name", name);
        }

        if (age != -1) {
            docData.put("age", age);
        }

        if (weight != -1) {
            docData.put("weight", weight);
        }

        if (sex != null) {
            docData.put("sex", sex);
        }

        if (GFR != -1) {
            docData.put("GFR", GFR);
        }

        if (allergies != null) {
            docData.put("allergies", allergies);
        }

        if (conditions != null) {
            docData.put("conditions", conditions);
        }

        if (medications != null) {
            docData.put("medications", medications);
        }

        db.collection("data").document("one")
                .set(docData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }
}
