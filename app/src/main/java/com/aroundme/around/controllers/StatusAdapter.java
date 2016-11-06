package com.aroundme.around.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aroundme.around.R;

/**
 * Created by Sruti on 11/5/16.
 */

public class StatusAdapter extends ArrayAdapter {

    Context context;
    int images[];
    String[] objects;

    public StatusAdapter(Context ctx, int txtViewResourceId, String[] objects, int[] images)
    {
        super(ctx, txtViewResourceId, objects);
        this.context=ctx;
        this.images = images;
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.status_item, parent, false);
        TextView status = (TextView) convertView.findViewById(R.id.status_text);
        status.setText(objects[position]);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.status_ic);
        if (objects[position].equals("Do Not Disturb")) {
            imageView.setImageResource(images[1]);
        } else {
            imageView.setImageResource(images[0]);
        }
        return convertView;

    }

    //Override the getCount() because we are passing object as null in constructor
    //getCount() determines how many times the getView should b called
    @Override
    public int getCount() {
        if (objects[3] != null) {
            return objects.length;
        } else {
            return 3;
        }
    }
}
