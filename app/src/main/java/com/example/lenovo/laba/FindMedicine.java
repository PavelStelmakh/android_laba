package com.example.lenovo.laba;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FindMedicine extends Fragment
{
    private Medicine[] medicines = new Medicine[] {
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland"),
            new Medicine("Analgin", 2, "Poland")
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_find_medicine, container,
                false);

        return rootView;
    }
}
