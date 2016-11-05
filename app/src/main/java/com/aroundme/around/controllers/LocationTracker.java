package com.aroundme.around.controllers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class LocationTracker implements LocationListener {


    LocationManager locationManager;
    String mprovider;

    double latitude, longitude;

    public LocationTracker(Context context, Object a, Context con) {
        locationManager = (android.location.LocationManager) a;
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 20000, 1, this);
            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(context, "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistance(double lon, double lat) {
        double kilometers = 6371000;
        double rad1 = Math.toRadians(lat);
        double rad2 = Math.toRadians(getLatitude());

        double van = Math.toRadians(getLatitude() - lat);
        double can = Math.toRadians(getLongitude() - lon);

        double a = Math.sin(van / 2) * Math.sin(van / 2) +
                Math.cos(rad1) * Math.cos(rad2) *
                Math.sin(can / 2) * Math.sin(can / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return kilometers * c;
    }
}
