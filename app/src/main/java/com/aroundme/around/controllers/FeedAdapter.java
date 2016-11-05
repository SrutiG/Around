package com.aroundme.around.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aroundme.around.R;
import com.aroundme.around.models.User;

import java.util.ArrayList;

/**
 * Created by Sruti on 11/5/16.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private ArrayList<User> users;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, status_message;
        public ImageView profile_pic;

        public MyViewHolder(View view) {
            super(view);
            profile_pic = (ImageView) view.findViewById(R.id.profile_pic);
            name = (TextView) view.findViewById(R.id.name);
            status_message = (TextView) view.findViewById(R.id.status_message);
        }
    }

    public FeedAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
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
