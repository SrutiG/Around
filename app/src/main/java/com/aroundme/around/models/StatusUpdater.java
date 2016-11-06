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

public class StatusUpdater extends AsyncTask<String, Void, Integer>  {


    @Override
    protected Integer doInBackground(String... params) {
        try {
            getHtml("http://bibliotecas.net46.net/updateStatus.php?id=" + params[0] + "&status="
                    + params[1] + "&timestamp=" + params[2]);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getHtml(String url) throws IOException {
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
