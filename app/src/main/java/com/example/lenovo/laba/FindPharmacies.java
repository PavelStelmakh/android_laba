package com.example.lenovo.laba;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class FindPharmacies extends Fragment
{
    GoogleMap googleMap;
    View rootView;

    public FindPharmacies() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_find_pharmacies, container,
                false);

        createMapView();

        return rootView;
    }

    private void createMapView(){
        try {
            if(null == googleMap) {
//                MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
//                MapFragment.newInstance().setTargetFragment(getFragmentManager().findFragmentById(R.id.mapView), R.id.mapView);

                ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap map) {
                        googleMap = map;

                        if(null == googleMap) {
                            Toast.makeText(rootView.getContext(), "Error creating map",Toast.LENGTH_SHORT).show();
                        }
                        googleMap.setMyLocationEnabled(true);
                    }
                });
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }
}
