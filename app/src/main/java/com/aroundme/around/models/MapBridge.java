package com.aroundme.around.models;

import android.os.AsyncTask;

import com.aroundme.around.controllers.Holder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

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
        if (params[0].equals("read")) {
            return updateNearbyUsers(Holder.distance, Holder.tracker.getLatitude(), Holder.tracker.getLongitude());
        } else if (params[0].equals("write")) {
            pingLocation();
        } else if (params[0].equals("custom")) {
            return updateNearbyUsers(Integer.parseInt(params[1]), Double.parseDouble(params[2]), Double.parseDouble(params[3]));
        }
        return null;
    }

    public void pingLocation() {
        //todo fix this
        Timer timer = new Timer();
        timer.schedule( new TimerTask()
        {
            public void run() {
                try {
                    System.out.println("Pinging " + Holder.tracker.getLatitude() + " - " + Holder.tracker.getLongitude());
                    getHtml("http://bibliotecas.net46.net/ping.php?latitude=" + Holder.tracker.getLatitude() + "&longitude=" + Holder.tracker.getLongitude() + "&user=" + Holder.user);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, 0, 15*(1000*1));
       }


    public String updateNearbyUsers(int distance, double latitude, double longitude) {
        try {
            System.out.println("pulling near " + latitude + " , " + longitude);
            return getHtml("http://bibliotecas.net46.net/query.php?latitude=" + latitude + "&longitude=" + longitude + "&distance=" + distance).split("<!", 2)[0];
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getHtml(String url) throws IOException {
        System.out.println("Pinging " + url);
        // Build and set timeout values for the request.
        URLConnection connection = (new URL(url)).openConnection();
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.connect();

        // Read and store the result line by line then return the entire string.
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder html = new StringBuilder();
        for (String line; (line = reader.readLine()) != null; ) {
        html.append(line);
        }
        in.close();

        System.out.println("GOT: " + html.toString());
        return html.toString();
        }

    public int getNearbyProfiles() {
        return nearbyUsers.size();
    }
}
