package com.aroundme.around.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.aroundme.around.controllers.Holder;
import com.aroundme.around.models.Status;

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

public class StatusFetcher extends AsyncTask<String, Void, Status >  {

    @Override
    protected com.aroundme.around.models.Status doInBackground(String... params) {
        try {
            String s = getHtml("http://bibliotecas.net46.net/getStatus.php?id=" + params[0]);

            System.out.println("GOT" + s );
            String[] parts = s.split(" , ", 3);
            if (parts[1] != null && parts[1].length() > 0) {
                return new com.aroundme.around.models.Status(parts[0], Long.parseLong(parts[1]), parts[2]);
            } else {
                return new com.aroundme.around.models.Status(parts[0], 0L, parts[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getHtml(String url) throws IOException {
        System.out.println("Asking " + url);
        URLConnection connection = (new URL(url)).openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();

        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder html = new StringBuilder();
        for (String line; (line = reader.readLine()) != null; ) {
            html.append(line);
        }
        in.close();

        return html.toString();
    }

}
