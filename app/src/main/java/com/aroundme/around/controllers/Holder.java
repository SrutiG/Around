package com.aroundme.around.controllers;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class Holder {

    public static Bitmap holder;
    public static LocationTracker tracker;
    public static int distance = 999999;

    public static String user;
    public static int id;

    public static Fragment last = null;
    public static Fragment delay = null;
}
