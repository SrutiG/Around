package com.aroundme.around.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aroundme.around.R;
import com.aroundme.around.models.Database;
import com.aroundme.around.models.MapBridge;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends FragmentActivity {

    ImageButton map_button;
    ImageButton feed_button;
    ImageButton profile_button;
    ImageButton settings_button;
    SharedPreferences sp;
    FrameLayout fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Holder.tracker = new LocationTracker(getBaseContext(), getSystemService(Context.LOCATION_SERVICE), this);
        map_button = (ImageButton) findViewById(R.id.map_button);
        feed_button = (ImageButton) findViewById(R.id.feed_button);
        profile_button = (ImageButton) findViewById(R.id.profile_button);
        settings_button = (ImageButton) findViewById(R.id.settings_button);
        fragment = (FrameLayout) findViewById(R.id.fragment);
        sp = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        MapFragment map = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, map).commit();

        new MapBridge().execute("read");
        new MapBridge().execute("write");
    }

    public void handleMapClicked(View view) {
        MapFragment map = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, map).commit();
    }

    public void handleFeedClicked(View view) {
        FeedFragment feed = new FeedFragment();
        feed.setMain(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, feed).commit();
    }

    public void handleProfileClicked(View view) {
        ProfileFragment profile = new ProfileFragment();
         getSupportFragmentManager().beginTransaction().replace(R.id.fragment, profile).commit();



    }

    public void handleSettingsClicked(View view) {
        ProfileSettingsFragment profileSet = new ProfileSettingsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, profileSet).commit();
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
    }


}
