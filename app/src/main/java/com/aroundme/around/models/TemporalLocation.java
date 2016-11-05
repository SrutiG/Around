package com.aroundme.around.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class TemporalLocation {

    private long unixTimestamp;
    private LatLng location;

    public TemporalLocation(LatLng location, long unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
        this.location = location;
    }

    public long getUnixTimestamp() {
        return unixTimestamp;
    }

    public LatLng getLocation() {
        return location;
    }
}
