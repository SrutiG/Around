package com.aroundme.around.models;

import android.os.AsyncTask;

import com.aroundme.around.controllers.Holder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class MapBridge extends AsyncTask<String, String,String> {

    private LinkedList<User> nearbyUsers;

    public MapBridge() {
        nearbyUsers = new LinkedList<User>();
    }

    @Override
    protected String doInBackground(String... params) {
        updateNearbyUsers(Holder.distance, Holder.tracker.getLatitude(), Holder.tracker.getLongitude());
        return null;
    }


    public void updateNearbyUsers(int distance, double latitude, double longitude) {
        try {
            System.out.println(getHtml("http://mulegame3.comxa.com/query.php?latitude=" + latitude + "&longitude=" + longitude + "&distance=" + distance));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //check for distance
        //populate nearbyUsers
    }

public static String getHtml(String url) throws IOException {
        // Build and set timeout values for the request.
        URLConnection connection = (new URL(url)).openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();

        // Read and store the result line by line then return the entire string.
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder html = new StringBuilder();
        for (String line; (line = reader.readLine()) != null; ) {
        html.append(line);
        }
        in.close();

        return html.toString();
        }

    public int getNearbyProfiles() {
        return nearbyUsers.size();
    }
}