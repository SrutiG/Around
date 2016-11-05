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
import com.aroundme.around.models.User;

import java.util.ArrayList;


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
        User Sruti = new User("Sruti", "Guhathakurta", "sruti@gatech.edu", "pass");
        Sruti.setStatus("Can someone please teach me Javascript?");
        User Bob = new User("Bob", "Smith", "Bob@bob.edu", "bob");
        Bob.setStatus("Looking for people to play football with");
        User Potato = new User("Potato", "Salad", "potato@salad.com", "potato");
        Potato.setStatus("Does someone want to cook potatoes with me?");
        users.add(Potato);
        users.add(Sruti);
        users.add(Bob);
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
