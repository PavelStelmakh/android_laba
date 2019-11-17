package com.example.lenovo.laba;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.lenovo.laba.MainActivity.db;

public class FindMedicine extends Fragment implements AdapterView.OnItemLongClickListener, MedicineDialog.NoticeDialogListener
{
    ListView medicineList;
    SimpleCursorAdapter  userAdapter;
    View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        setRetainInstance(true);

        rootView = inflater.inflate(R.layout.activity_find_medicine, container,
                false);

        medicineList = rootView.findViewById(R.id.medicine_list);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        init();

        new GetDataTask().execute();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
        Cursor cursor = (Cursor)parent.getItemAtPosition(position);
        int _id = cursor.getInt(0);
        Bundle bundle = new Bundle();
        bundle.putInt("medicine_id", _id);
        MedicineDialog fragment = new MedicineDialog();
        fragment.setDialogListener(this);
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "missiles");

        return true;
    }

    @Override
    public void onDialogUpdateClick(int _id) {
        Bundle bundle = new Bundle();
        bundle.putInt("medicine_id", _id);

        Fragment fragment = new AddPill();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        MainActivity.pillId = _id;
        MainActivity._id = R.id.nav_add_pill;
    }

    @Override
    public void onDialogDeleteClick(int _id) {
        db.delete(DatabaseHelper.TABLE, "_id = ?", new String[]{String.valueOf(_id)});

        Cursor userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        String[] headers = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PRICE};
        userAdapter = new SimpleCursorAdapter(rootView.getContext(), R.layout.medicine_item,
                userCursor, headers, new int[]{R.id.medicine_item_id, R.id.medicine_item_title, R.id.medicine_item_price}, 0);

        medicineList.setAdapter(userAdapter);
        medicineList.deferNotifyDataSetChanged();
        MainActivity._id = R.id.nav_find_medicine;
    }

    public void init() {
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor)parent.getItemAtPosition(position);
                int _id = cursor.getInt(0);
                Bundle bundle = new Bundle();
                bundle.putInt("medicine_id", _id);
                MainActivity.pillId = _id;
                MainActivity._id = R.id.medicine_about;

                Fragment fragment = new MedicineAbout();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();
            }
        };

        AdapterView.OnItemLongClickListener itemLongClickListener = this;

//        Cursor userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
//        String[] headers = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PRICE};
//        userAdapter = new SimpleCursorAdapter(rootView.getContext(), R.layout.medicine_item,
//                userCursor, headers, new int[]{R.id.medicine_item_id, R.id.medicine_item_title, R.id.medicine_item_price}, 0);

        medicineList.setOnItemClickListener(itemClickListener);
        medicineList.setOnItemLongClickListener(itemLongClickListener);

        medicineList.setAdapter(userAdapter);

        MainActivity.toggle.setDrawerIndicatorEnabled(true);
        MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        MainActivity.toggle = new ActionBarDrawerToggle(
                MainActivity.instance, MainActivity.drawer, MainActivity.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        MainActivity.drawer.addDrawerListener(MainActivity.toggle);
        MainActivity.toggle.syncState();
        MainActivity._id = R.id.nav_find_medicine;
    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            Toast toast = Toast.makeText(getActivity(), "Loading of data", Toast.LENGTH_SHORT);
            toast.show();
        }
        @Override
        protected Void doInBackground(Void... unused) {
            SystemClock.sleep(2000);
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            Cursor userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
            String[] headers = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PRICE};
            userAdapter = new SimpleCursorAdapter(rootView.getContext(), R.layout.medicine_item,
                    userCursor, headers, new int[]{R.id.medicine_item_id, R.id.medicine_item_title, R.id.medicine_item_price}, 0);

            medicineList.setAdapter(userAdapter);
            medicineList.deferNotifyDataSetChanged();
        }
    }
}
