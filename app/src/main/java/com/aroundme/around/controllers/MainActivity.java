package com.aroundme.around.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.aroundme.around.R;

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
        FeedFragment profile = new FeedFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, profile).commit();

    }

    public void handleMapClicked(View view) {
        MapFragment map = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, map).commit();
    }

    public void handleFeedClicked(View view) {
        FeedFragment feed = new FeedFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, feed).commit();
    }

    public void handleProfileClicked(View view) {
        ProfileFragment profile = new ProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, profile).commit();
    }

    public void handleSettingsClicked(View view) {
    }
}
