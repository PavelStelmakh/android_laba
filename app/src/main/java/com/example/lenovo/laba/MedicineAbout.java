package com.example.lenovo.laba;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MedicineAbout extends Fragment {
    Medicine medicine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        medicine = (Medicine) getArguments().getSerializable("medicine");

        View rootView = inflater.inflate(R.layout.medicine_about_activity, container,
                false);

        MainActivity.toggle.setDrawerIndicatorEnabled(false);
        MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        MainActivity.toggle.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        MainActivity.toggle.syncState();

        MainActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FindMedicine();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();
            }
        });

        TextView name = rootView.findViewById(R.id.name_pill);
        TextView producer = rootView.findViewById(R.id.producer_pill);
        TextView price = rootView.findViewById(R.id.price_pill);
        TextView description = rootView.findViewById(R.id.decription_pill);
        name.setText(medicine.getName());
        producer.setText(medicine.getProducer());
        price.setText(String.format("%2.2s", medicine.getPrice()));
        description.setText(medicine.getDescription());

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MainActivity.toolbar.setNavigationOnClickListener(null);
    }
}
