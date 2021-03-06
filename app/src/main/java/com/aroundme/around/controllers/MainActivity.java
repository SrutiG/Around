package com.aroundme.around.controllers;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.aroundme.around.R;
import com.aroundme.around.models.MapBridge;

public class MainActivity extends FragmentActivity {

    ImageButton map_button;
    ImageButton feed_button;
    ImageButton profile_button;
    ImageButton settings_button;
    ImageButton message_button;
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
        message_button = (ImageButton) findViewById(R.id.message_button);
        fragment = (FrameLayout) findViewById(R.id.fragment);
        sp = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        MapFragment map = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, map).addToBackStack(null).commit();
        map.setMain(this);
        new MapBridge().execute("read");
        new MapBridge().execute("write");
    }

    public void handleMapClicked(View view) {
        MapFragment map = new MapFragment();
        map.setMain(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, map).addToBackStack(null).commit();

    }

    public void handleFeedClicked(View view) {
        FeedFragment feed = new FeedFragment();
        feed.setMain(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, feed).addToBackStack(null).commit();
    }

    public void handleProfileClicked(View view) {
        ProfileFragment profile = new ProfileFragment();
        profile.setMain(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, profile).addToBackStack(null).commit();
    }

    public void handleSettingsClicked(View view) {
        ProfileSettingsFragment profileSet = new ProfileSettingsFragment();
        profileSet.setMain(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, profileSet).addToBackStack(null).commit();
    }

    public void handleMessageClicked(View view) {
        MessageFragment message = new MessageFragment();
        setFragment(message);

    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        //System.out.println((Holder.last == null) + " + " + (Holder.delay == null));
        if (Holder.delay != null) {
            System.out.println("Hijacking!");
            //setFragment(Holder.delay);
        }
    }



}
