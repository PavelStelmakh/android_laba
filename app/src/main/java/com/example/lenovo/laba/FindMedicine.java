package com.example.lenovo.laba;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FindMedicine extends Fragment
{
    private List<Medicine> medicines = new ArrayList();
    ListView medicineList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_find_medicine, container,
                false);

        setInitialMedicine();

        medicineList = rootView.findViewById(R.id.medicine_list);
        MedicineAdapter adapter = new MedicineAdapter(rootView.getContext(), R.layout.medicine_item, medicines);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medicine selectedMedicine = (Medicine)parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
//                bundle.putString("medicine", selectedMedicine.toString());
                bundle.putSerializable("medicine", selectedMedicine);

                Fragment fragment = new MedicineAbout();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();
            }
        };

        medicineList.setOnItemClickListener(itemClickListener);

        medicineList.setAdapter(adapter);

        MainActivity.toggle.setDrawerIndicatorEnabled(true);
        MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        MainActivity.toggle = new ActionBarDrawerToggle(
                MainActivity.instance, MainActivity.drawer, MainActivity.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        MainActivity.drawer.addDrawerListener(MainActivity.toggle);
        MainActivity.toggle.syncState();

        return rootView;
    }

    private void setInitialMedicine() {
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
        medicines.add(new Medicine("Analgin", 2, "Poland", "For ill person"));
    }
}
