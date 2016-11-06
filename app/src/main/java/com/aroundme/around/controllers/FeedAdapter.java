package com.aroundme.around.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aroundme.around.R;
import com.aroundme.around.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;

/**
 * Created by Sruti on 11/5/16.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private ArrayList<User> users;
    private MainActivity main;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, status_message;
        public ImageView profile_pic;
        public LinearLayout feed_options;
        public ImageButton message, view_profile, comment;

        public MyViewHolder(View view) {
            super(view);
            profile_pic = (ImageView) view.findViewById(R.id.profile_pic);
            name = (TextView) view.findViewById(R.id.name);
            status_message = (TextView) view.findViewById(R.id.status_message);
            feed_options = (LinearLayout) view.findViewById(R.id.feed_options);
            message = (ImageButton) view.findViewById(R.id.messageBT);
            view_profile = (ImageButton) view.findViewById(R.id.profileBT);
            comment = (ImageButton) view.findViewById(R.id.commentBT);

            feed_options.setVisibility(GONE);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (feed_options.getVisibility() == GONE) {
                feed_options.setVisibility(View.VISIBLE);
            } else {
                feed_options.setVisibility(View.GONE);
            }

        }
    }

    public FeedAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);


        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = users.get(position);
        final String email = user.getEmail();
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
        holder.status_message.setText(user.getStatus());
        Picasso.with(context).load(user.getImage().split("<br", 2)[0].trim()).into(holder.profile_pic);
        //
        //System.out.println(holder.profile_pic.getDrawingCache().getWidth());


        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileSettingsFragment profile = new ProfileSettingsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                profile.setArguments(bundle);
                //main.setFragment(profile);
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return users.size();
    }
}
