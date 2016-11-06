package com.aroundme.around.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.aroundme.around.controllers.Holder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class ProfileLoader extends AsyncTask<String, Void, Profile>  {


    @Override
    protected Profile doInBackground(String... params) {
        try {
            return loadUser(Integer.parseInt(params[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Profile loadUser(int id) throws IOException {
        String[] vals = getHtml("http://bibliotecas.net46.net/profile.php?id=" + id).split("<br/>");
        return new Profile(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
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

    public static Bitmap getImage(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
