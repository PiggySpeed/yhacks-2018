
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

import com.hacks.yale.yhacks_2018.ocr.OCRCaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class DrugActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int TEST_RESPONSE = 1;
    private DrugAdapter adapter;
    private RecyclerView rvMain;


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
    }
    //

    public void renderDrugList(ArrayList<String> drugs) {
        ArrayList<Drug> result = new ArrayList<>();

        for (int i = 0; i < drugs.size(); i++) {
            Drug drug = renderDrugEntry(drugs.get(i));
            if (drug != null) {
                result.add(drug);
            }
        }

        adapter = new DrugAdapter(this, result);
        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
    }

    public Drug renderDrugEntry(String ndc) {
        if (ndc.equals("65862-010")) {
            return new Drug("Metformin", "1000mg", "65862-010", "1", "Oral", "Twice Daily", "", "", "");
        } else if (ndc.equals("65862-010")) {
            return new Drug("Amitriptyline", "25mg", "0378-2625-10", "1", "Oral", "At Bedtime", "BEERS 2015", "Antidepressants", "Highly anticholinergic, sedating, and cause orthostatic hypotension; safety profile of low- dose doxepin (≤6 mg/d) comparable with that of placebo");
        } else if (ndc.equals("00062-910")) {
            return new Drug("Pantoprazole", "40mg", "70518-012", "1", "Oral", "Once Daily", "BEERS 2015", "Proton Pump Inhibitors", "Risk of Clostridium difficile infection and bone loss and fractures");
        } else if (ndc.equals("65099-010")) {
            return new Drug("Diphenhydramine", "25mg", "65862-010", "1", "Oral", "Twice Daily", "STOPP 2015", "Antihistamines (First-Generation)", "First-generation antihistamines in patients with falls.");
        } else if (ndc.equals("65862-310")) {
            return new Drug("Gabapentin", "600mg", "65862-010", "1", "Oral", "Three times a day", "", "", "");
        } else if (ndc.equals("65862-014")) {
            return new Drug("Clonazepam", "0.5mg", "65862-010", "1", "Oral", "Twice Daily", "BEERS 2015", "Benzodiazepines", "Older adults have increased sensitivity to benzodiazepines and decreased metabolism of long-acting agents; in general, all benzodiazepines increase risk of cognitive impairment, delirium, falls, fractures, and motor vehicle crashes in older adults");
        } else {
            return null;
        }
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