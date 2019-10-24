package com.example.lenovo.laba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MedicineAdapter extends ArrayAdapter<Medicine> {
    private LayoutInflater inflater;
    private int layout;
    private List<Medicine> medicines;

    public MedicineAdapter(Context context, int resource, List<Medicine> medicines) {
        super(context, resource, medicines);
        this.medicines = medicines;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);

        TextView title = view.findViewById(R.id.medicine_item_title);
        TextView price = view.findViewById(R.id.medicine_item_price);

        Medicine medicine = medicines.get(position);

        title.setText(medicine.getName());
        price.setText(String.format("%2.2f", medicine.getPrice()));

        return view;
    }
}
