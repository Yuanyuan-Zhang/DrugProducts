package com.example.student.drugproducts;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class map extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    String str = ""; //for address
    String str2 = ""; //for the company name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        if(intent.getStringExtra("address")!=null) {
            str = intent.getStringExtra("address"); //get the address from previous activity
        }
        if(intent.getStringExtra("name")!=null) {
            str2 = intent.getStringExtra("name");  //get the company name from previous activity
        }
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //locMgr.request
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play serMy Firstvices APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.getUiSettings().setZoomControlsEnabled(true);
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        List<Address> geocodeMatches = null;
        double latitude = 0;
        double longitude = 0;

        if(str==null){ //if there is no value be passed from the previous activity
            str = "161 Goldenrod Driveway, Ottawa, Ontario K1A 0K9";
            str2 = "Health Canada";
            //Log.i("Jamie","here");
        }

        try {
            geocodeMatches = new Geocoder(this).getFromLocationName(str, 1);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (!geocodeMatches.isEmpty())
        {
            latitude = geocodeMatches.get(0).getLatitude();
            longitude = geocodeMatches.get(0).getLongitude();
        }
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(str2));

        mMap.setMyLocationEnabled(true);
        LatLng latlng = new LatLng(latitude, longitude);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(latlng, 20);
        mMap.animateCamera(location);

        CameraPosition newCameraPosition = new CameraPosition.Builder()
                .target(latlng)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .bearing(0)
                .tilt(25)
                .build();                   // Creates a CameraPosition from the builder

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
    }

}
