package com.aroundme.around;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

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
        map_button = (ImageButton) findViewById(R.id.map_button);
        feed_button = (ImageButton) findViewById(R.id.feed_button);
        profile_button = (ImageButton) findViewById(R.id.profile_button);
        settings_button = (ImageButton) findViewById(R.id.settings_button);
        fragment = (FrameLayout) findViewById(R.id.fragment);
        sp = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        ProfileFragment profile = new ProfileFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, profile).commit();

    }

    public void handleMapClicked(View view) {

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
