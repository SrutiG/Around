package com.aroundme.around.models;

import android.os.AsyncTask;

import com.aroundme.around.controllers.Holder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class UserLoader extends AsyncTask<String, Void, Integer>  {


    @Override
    protected Integer doInBackground(String... params) {
        try {
            Holder.id = Integer.parseInt(getHtml("http://bibliotecas.net46.net/profileID.php?user=" + params[0] + "&pass=" + params[1]).split("\t", 2)[0]);
            System.out.println("The id is ... " + Holder.id);
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
