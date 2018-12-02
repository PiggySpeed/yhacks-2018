package com.hacks.yale.yhacks_2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hacks.yale.yhacks_2018.firebase.Sync;
import com.hacks.yale.yhacks_2018.ocr.OCRCaptureActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DrugActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int TEST_RESPONSE = 1;
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mContext = getApplicationContext();
        setContentView(R.layout.activity_drug);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rvMain = (RecyclerView) findViewById(R.id.rvDrug);
        populateRecyclerView();
    }

    public void populateRecyclerView() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Sync mSync = new Sync(db);
        //JSONArray jData = mSync.loadData();
        ArrayList<Object> arr1 = new ArrayList<>();
        //Log.d("sigh", String.valueOf(mData));
        //Log.d("sighhh", String.valueOf(mData.get(0)));


        JSONObject jData = mSync.loadData();
        String str1 = String.valueOf(jData);
        Log.d("sighh", str1);


        ArrayList<Drug> drugs = new ArrayList<Drug>();

        // split up the JSON array into JSON objects

        for (int i = 0; i < jData.length(); i++) {
            Log.d("sighh", "got to populate");

//            arr1.add(jData.toJSONObject(jData));
//            JSONObject n = jData.get(i);

//            ArrayList<Object> arr1 = new ArrayList<>();

            //Drug drug = (Drug) obj;
            //
            //Drug drug = new Drug(mData.get(i)[1])
            //drugs.add(drug);

            //drugs.add(new Drug("name", 100, "dummy"));
        }
        //ArrayList<Drug> drugs = mSync.loadData();


//        for (int i = 1; i <= 20; i++) {
//            drugs.add(new Drug("Amoxicillin", 500, "dummy"));
//        }

        List<Drug> parentListItems = new ArrayList<>();
        for (Drug drug : drugs) {
            List<DrugDetailed> childListItems = new ArrayList<>();
            childListItems.add(new DrugDetailed("detail 1"));
            childListItems.add(new DrugDetailed("detail 2"));
            childListItems.add(new DrugDetailed("detail 3"));
            childListItems.add(new DrugDetailed("detail 4"));
            childListItems.add(new DrugDetailed("detail 5"));

            drug.setmChildItemList(childListItems);
            parentListItems.add(drug);
        }

        DrugAdapter adapter = new DrugAdapter(this, drugs);
        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Log.i("RESULT OK", "-----------------------");
            if (requestCode == TEST_RESPONSE) {
                Log.i("TEST RESPONSE", "-----------------------");
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(DrugActivity.this, OCRCaptureActivity.class);
            startActivityForResult(intent, TEST_RESPONSE);
        } else if (id == R.id.nav_alerts) {
            Intent intent = new Intent(DrugActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
