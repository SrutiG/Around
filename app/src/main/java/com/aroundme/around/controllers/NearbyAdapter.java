package com.aroundme.around.controllers;

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

import java.util.ArrayList;

import static android.view.View.GONE;
import static com.aroundme.around.R.id.feed_options;
import static com.aroundme.around.R.id.name_nearby;
import static com.aroundme.around.R.id.profile_pic;

/**
 * Created by Sruti on 11/5/16.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.MyViewHolder> {

    private ArrayList<User> users;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView propic_nearby;
        private TextView name_nearby;
        private ImageView status_ic;
        private TextView status_nearby;
        private TextView other_nearby;

        public MyViewHolder(View view) {
            super(view);
            propic_nearby = (ImageView) view.findViewById(R.id.propic_nearby);
            name_nearby = (TextView) view.findViewById(R.id.name_nearby);
            status_ic = (ImageView) view.findViewById(R.id.status_ic);
            status_nearby = (TextView) view.findViewById(R.id.status_nearby);
            other_nearby = (TextView) view.findViewById(R.id.other_nearby);
        }

        @Override
        public void onClick(View view) {
            String name = name_nearby.getText().toString();
        }

    }

    public NearbyAdapter(ArrayList<User> users) { this.users = users; }

    @Override
    public NearbyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nearby_item, parent, false);

        return new NearbyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NearbyAdapter.MyViewHolder holder, int position) {
        User user = users.get(position);
        final String email = user.getEmail();
        holder.name_nearby.setText(user.getFirstName() + " " + user.getLastName());
        holder.status_nearby.setText(user.getStatus());
        holder.other_nearby.setText(user.getInterests());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
