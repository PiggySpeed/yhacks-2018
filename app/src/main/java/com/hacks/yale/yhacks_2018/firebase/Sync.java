package com.hacks.yale.yhacks_2018.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

public class Sync {
    public FirebaseFirestore db;

    private static final String TAG = "SIGHH";

    JSONObject data = new JSONObject();

    public Sync(FirebaseFirestore mdb){
        db = mdb;
    }

    public JSONObject loadData () {
        Log.d("sig", "loading data");

        db.collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d("sig", "listener is completed");
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                for (QuerySnapshot field : DocumentSnapshot()) {
//
//                                }
                                Log.d("sig", document.getId() + " => " + document.getData());
                                try {
                                    data.put("result", document.getData());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return data;
    }


//        db.collection("cities")
//                .whereEqualTo("state", "CA")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value,
//                                        @Nullable FirebaseFirestoreException e) {
//                        if (e != null) {
//                            Log.w(TAG, "Listen failed.", e);
//                            return;
//                        }
//
//                        List<String> cities = new ArrayList<>();
//                        for (QueryDocumentSnapshot doc : value) {
//                            if (doc.get("name") != null) {
//                                cities.add(doc.getString("name"));
//                            }
//                        }
//                        Log.d(TAG, "Current cites in CA: " + cities);
//                    }
//                });


    public void checkFirebase () {
        final DocumentReference docRef = db.collection("users").document("h0JDdVGWRnWJLXum3muDI0Kiw183");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    public void checkFirebaseChanges () {
        db.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "listen:error", e);
                            return;
                        }

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                    Log.d(TAG, "New drugs: " + dc.getDocument().getData());
                                    break;
                                case MODIFIED:
                                    Log.d(TAG, "Modified drugs: " + dc.getDocument().getData());
                                    break;
                                case REMOVED:
                                    Log.d(TAG, "Removed drugs: " + dc.getDocument().getData());
                                    break;
                            }
                        }

                    }
                });
    }

    public void stopListen() {
        Query query = db.collection("cities");
        ListenerRegistration registration = query.addSnapshotListener(
                new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                    }
                });
        // Stop listening to changes
        registration.remove();

    }

}
