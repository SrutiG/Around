package com.aroundme.around.controllers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aroundme.around.R;
import com.aroundme.around.models.MapBridge;
import com.aroundme.around.models.Status;
import com.aroundme.around.models.StatusFetcher;
import com.aroundme.around.models.User;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    private RecyclerView feed_list;
    private FeedAdapter feedAdapter;
    private RecyclerView.LayoutManager feedManager;
    private ArrayList<User> users = new ArrayList<>();
    private MainActivity main;
    private FrameLayout flayout;

    public FeedFragment() {
        // Required empty public constructor
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (flayout == null) {
            flayout = (FrameLayout) inflater.inflate(R.layout.fragment_feed, container, false);


            if (getArguments() != null && getArguments().getString("title") != null
                    && getArguments().getString("title").length() > 0) {
                System.out.println(getArguments().getDouble("lat"));
                ((TextView) flayout.findViewById(R.id.toptext)).setText(getArguments().getString("title"));
                ((EditText) flayout.findViewById(R.id.fledit)).setVisibility(View.INVISIBLE);
                ((ImageView) flayout.findViewById(R.id.pencil)).setVisibility(View.INVISIBLE);

                try {
                    String s = new MapBridge().execute("custom", "100", "" + getArguments().getDouble("lat"), "" + getArguments().getDouble("lon")).get();
                    String[] splits = s.split("<br/>");
                    System.out.println(splits != null ? splits.length : 0);
                    for (int i = 0; i < splits.length; i++) {
                        String[] subsplit = splits[i].split(" , ");
                        if (subsplit.length > 1) {
                            String first = subsplit[1];
                            String last = subsplit[2];
                            int id = Integer.parseInt(subsplit[0]);

                            Status status = new StatusFetcher().execute("" + id).get();

                            User user = new User(id, first, last, "", "", status.getImage());
                            user.setStatus(status.getStatus() + " @ " + status.getTimestamp());
                            //user.setImage(status.getImage());
                            users.add(user);
                        }
                    }
                    System.out.println(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ((EditText) flayout.findViewById(R.id.fledit)).setVisibility(View.VISIBLE);
                    ((ImageView) flayout.findViewById(R.id.pencil)).setVisibility(View.VISIBLE);

                    String s = new MapBridge().execute("read").get();
                    String[] splits = s.split("<br/>");
                    System.out.println(splits != null ? splits.length : 0);
                    for (int i = 0; i < splits.length; i++) {
                        String[] subsplit = splits[i].split(" , ");

                        String first = subsplit[1];
                        String last = subsplit[2];
                        int id = Integer.parseInt(subsplit[0]);

                        Status status = new StatusFetcher().execute("" + id).get();

                        User user = new User(id, first, last, "", "", status.getImage());
                        user.setStatus(status.getStatus());
                        //user.setImage(status.getImage());
                        users.add(user);
                    }
                    System.out.println(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            feed_list = (RecyclerView) flayout.findViewById(R.id.feed_list);
            feedManager = new LinearLayoutManager(getActivity());
            feed_list.setLayoutManager(feedManager);
            feed_list.setItemAnimator(new DefaultItemAnimator());
            feedAdapter = new FeedAdapter(users, getContext());
            feedAdapter.setMain(main);
            feed_list.setAdapter(feedAdapter);

        }
        return flayout;


    }
}
