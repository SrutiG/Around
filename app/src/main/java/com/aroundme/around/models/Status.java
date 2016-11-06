package com.aroundme.around.models;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class Status {

    private String status, img;
    private long timestamp;

    public Status(String status, long timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }

    public Status(String status, long timestamp, String img) {
        this(status, timestamp);
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getImage() {
        return img;
    }
}
