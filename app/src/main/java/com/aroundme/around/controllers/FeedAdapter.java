package com.aroundme.around.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aroundme.around.R;
import com.aroundme.around.models.User;

import java.util.ArrayList;

import static android.view.View.GONE;
import static com.aroundme.around.R.id.status;
import static com.aroundme.around.R.id.status_enter;

/**
 * Created by Sruti on 11/5/16.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private ArrayList<User> users;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, status_message;
        public ImageView profile_pic;
        public LinearLayout feed_options;
        public Button message, view_profile, comment;

        public MyViewHolder(View view) {
            super(view);
            profile_pic = (ImageView) view.findViewById(R.id.profile_pic);
            name = (TextView) view.findViewById(R.id.name);
            status_message = (TextView) view.findViewById(R.id.status_message);
            feed_options = (LinearLayout) view.findViewById(R.id.feed_options);
            message = (Button) view.findViewById(R.id.messageBT);
            view_profile = (Button) view.findViewById(R.id.profileBT);
            comment = (Button) view.findViewById(R.id.commentBT);
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            view_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
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

    public FeedAdapter(ArrayList<User> users) {
        this.users = users;
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
        holder.profile_pic.setImageResource(R.drawable.ic_headshot);
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
        holder.status_message.setText(user.getStatus());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return users.size();
    }
}
