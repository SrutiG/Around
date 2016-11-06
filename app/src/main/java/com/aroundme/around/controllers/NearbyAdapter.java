package com.aroundme.around.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.aroundme.around.models.UserFromID;
import com.aroundme.around.models.UserLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;
import static com.aroundme.around.R.id.feed_options;
import static com.aroundme.around.R.id.name_nearby;
import static com.aroundme.around.R.id.profile_pic;
import static com.aroundme.around.R.id.status_ic;

/**
 * Created by Sruti on 11/5/16.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.MyViewHolder> {

    private ArrayList<User> users;
    private Context context;
    private MainActivity main;

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
            view.setOnClickListener(this);
        }

        final ProfileFragment profile = new ProfileFragment();


        @Override
        public void onClick(View view) {
            String name = name_nearby.getText().toString();
            try {
                Integer id = new UserFromID().execute("" + name).get();
                Bundle bundle = new Bundle();
                bundle.putInt("profile_id", id);
                profile.setArguments(bundle);
                profile.setMain(main);
                main.setFragment(profile);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public NearbyAdapter(ArrayList<User> users, Context context) { this.users = users;
        this.context = context;}

    @Override
    public NearbyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nearby_item, parent, false);

        return new NearbyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NearbyAdapter.MyViewHolder holder, int position) {
        User user = users.get(position);
        holder.name_nearby.setText(user.getFirstName() + " " + user.getLastName());
        holder.status_nearby.setText(user.getStatus());
        holder.other_nearby.setText(user.getInterests());
        System.out.println("Nearby Adapter: " + user.getStatus().trim());
        if ((user.getStatus().trim()).equals("Do Not Disturb")) {
            holder.status_ic.setImageResource(R.drawable.ic_unavailable);
        }
        String url = user.getImage().split("<br", 2)[0].trim();
        System.out.println(url);
        Picasso.with(context).load(url).into(holder.propic_nearby);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
