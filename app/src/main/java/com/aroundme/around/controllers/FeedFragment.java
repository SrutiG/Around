package com.aroundme.around.controllers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
 * {@link FeedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    private RecyclerView feed_list;
    private FeedAdapter feedAdapter;
    private RecyclerView.LayoutManager feedManager;
    private ArrayList<User> users = new ArrayList<>();
    private MainActivity main;

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
        FrameLayout flayout = (FrameLayout) inflater.inflate(R.layout.fragment_feed, container, false);

        try {
            String s = new MapBridge().execute("read").get();

            String[] splits = s.split("<br/>");
            System.out.println(splits != null ?  splits.length :  0);
            for (int i = 0; i < splits.length; i++) {
                String[] subsplit = splits[i].split(" , ");

                String first = subsplit[1];
                String last = subsplit[2];
                int id = Integer.parseInt(subsplit[0]);

                Status status = new StatusFetcher().execute("" + id).get();

                User user = new User(first, last, "", "");
                user.setStatus(status.getStatus() + " @ " + status.getTimestamp());
                //user.setImage(status.getImage());
                users.add(user);
            }
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        feed_list = (RecyclerView) flayout.findViewById(R.id.feed_list);
        feedManager = new LinearLayoutManager(getActivity());
        feed_list.setLayoutManager(feedManager);
        feed_list.setItemAnimator(new DefaultItemAnimator());
        feedAdapter = new FeedAdapter(users);
        feedAdapter.setMain(main);
        feed_list.setAdapter(feedAdapter);



        return flayout;

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
