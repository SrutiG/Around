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

public class UserAdder extends AsyncTask<String, Void, String>  {

    @Override
    protected String doInBackground(String... params) {
        try {
            getHtml("http://bibliotecas.net46.net/addUser.php?first=" + URLEncoder.encode(params[0], "UTF-8") + "&last=" + URLEncoder.encode(params[1], "UTF-8") + "&email=" + URLEncoder.encode(params[2], "UTF-8") + "&pass=" + URLEncoder.encode(params[3], "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getHtml(String url) throws IOException {
        System.out.println("Adding at: " + url);
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
