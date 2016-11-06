package com.aroundme.around.models;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class UserUpdater extends AsyncTask<String, Void, Integer>  {


    @Override
    protected Integer doInBackground(String... params) {
        try {
            getHtml("http://bibliotecas.net46.net/updateProfile.php?id=" + URLEncoder.encode(params[0].trim(), "UTF-8") + "&first_name=" + URLEncoder.encode(params[1].trim(), "UTF-8")  + "&last_name=" + URLEncoder.encode(params[2].trim(), "UTF-8")  + "&interests=" + URLEncoder.encode(params[3].trim(), "UTF-8") + "&status=" + URLEncoder.encode(params[4].trim(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getHtml(String url) throws IOException {
        System.out.println(url);
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
