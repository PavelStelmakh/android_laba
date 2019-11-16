package com.example.lenovo.laba;

//import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
//import android.net;

import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class FindPharmacies extends Fragment implements OnMapReadyCallback
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
                SupportMapFragment map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView));
                map.getMapAsync(this);
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        if(null == googleMap) {
            Toast.makeText(rootView.getContext(), "Error creating map",Toast.LENGTH_SHORT).show();
        }

        if (ContextCompat.checkSelfPermission(MainActivity.instance, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.instance, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            LocationManager locationManager = (LocationManager)MainActivity.instance.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Geocoder geocoder = new Geocoder(MainActivity.instance, Locale.ENGLISH);
                    List<Address> addresses = null;
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    try {
                        addresses = geocoder.getFromLocationName(
                                "apteka",
                                1,
                                lat - 0.003,
                                lon - 0.003,
                                lat + 0.003,
                                lon + 0.003);
                    } catch (IOException exception) {
                        Log.e("mapApp, data Query", exception.toString());
                    }

                    if (addresses.size() > 0) {
                        Address returnedAddress = addresses.get(0);
                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(returnedAddress.getLatitude(), returnedAddress.getLongitude()))
                                .title("pharmacies")
                        );
                    } else {
                        Log.i("mapApp, data not found", "list of addresses are empty");
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
//            final HttpUriRequest request = new HttpGet(builder.build());
//            HttpClient client = new DefaultHttpClient();
//            final HttpResponse execute = client.execute(request);

//            final String response;
//
//            response = EntityUtils.toString(execute.getEntity());
        } else {
            ActivityCompat.requestPermissions(MainActivity.instance, new String[] {
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    1337);
        }
//        googleMap.setMyLocationEnabled(true);
    }

    private String getContent(String path) throws IOException {
        BufferedReader reader=null;
        try {
            URL url=new URL(path);
            HttpsURLConnection c=(HttpsURLConnection)url.openConnection();
            c.setRequestMethod("GET");
            c.setReadTimeout(10000);
            c.connect();
            reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder buf=new StringBuilder();
            String line=null;
            while ((line=reader.readLine()) != null) {
                buf.append(line + "\n");
            }
            return(buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
