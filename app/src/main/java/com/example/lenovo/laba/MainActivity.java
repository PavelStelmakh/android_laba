package com.example.lenovo.laba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static DrawerLayout drawer;
    public static ActionBarDrawerToggle toggle;
    public static Toolbar toolbar;
    public static MainActivity instance;

    public static DatabaseHelper databaseHelper;
    public static SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    static int _id = -1;
    static int pillId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode());
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
        db = databaseHelper.open();

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Medicine and pharmacies");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        instance = this;
        toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        toggle.setDrawerIndicatorEnabled(false);
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//        toggle.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
//        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new Home());
        ft.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("_id", _id);
        outState.putInt("pillId", pillId);

        super.onSaveInstanceState(outState);
    }
    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        _id = savedInstanceState.getInt("_id");

        Fragment fragment = null;

        switch (_id) {
            case R.id.nav_home: {
                fragment = new Home();
            } break;
            case R.id.nav_find_medicine: {
                fragment = new FindMedicine();
            } break;
            case R.id.nav_find_pharmacies: {
                fragment = new FindPharmacies();
            } break;
            case R.id.nav_support: {
                fragment = new Support();
            } break;
            case R.id.nav_add_pill:{
                pillId = savedInstanceState.getInt( "pillId");
                Bundle bundle = new Bundle();
                bundle.putInt("medicine_id", pillId);

                fragment = new AddPill();
                fragment.setArguments(bundle);
            } break;
            case R.id.action_web_site:
            case R.id.nav_web_site: {
                fragment = new WebSite();
            } break;
            case R.id.medicine_about: {
                pillId = savedInstanceState.getInt("pillId");
                Bundle bundle = new Bundle();
                bundle.putInt("medicine_id", pillId);

                fragment = new MedicineAbout();
                fragment.setArguments(bundle);
            } break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (db == null || !db.isOpen()) {
//            databaseHelper = new DatabaseHelper(getApplicationContext());
//            databaseHelper.create_db();
//            db = databaseHelper.open();
//        }
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        _id = item.getItemId();

        if (_id == R.id.action_web_site) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new WebSite()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        _id = item.getItemId();
        Fragment fragment = null;

        switch (_id) {
            case R.id.nav_home: {
                fragment = new Home();
            } break;
            case R.id.nav_find_medicine: {
                fragment = new FindMedicine();
            } break;
            case R.id.nav_find_pharmacies: {
                fragment = new FindPharmacies();
            } break;
            case R.id.nav_support: {
                fragment = new Support();
            } break;
            case R.id.nav_add_pill:{
                fragment = new AddPill();
            } break;
            case R.id.nav_web_site: {
                fragment = new WebSite();
//                getFragmentManager().beginTransaction()
//                        .add(R.id.content_frame, new WebSite())
//                        .commit();
            } break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
