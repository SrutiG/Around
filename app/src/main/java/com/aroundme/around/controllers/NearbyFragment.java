package com.aroundme.around.controllers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.aroundme.around.R;
import com.aroundme.around.models.User;

import java.util.ArrayList;

import static com.aroundme.around.controllers.Holder.user;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NearbyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class NearbyFragment extends Fragment {

    private RecyclerView people_list;
    private NearbyAdapter nearbyAdapter;
    private RecyclerView.LayoutManager nearbyManager;
    private ArrayList<User> users = new ArrayList<>();

    public NearbyFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout flayout = (FrameLayout) inflater.inflate(R.layout.fragment_nearby, container, false);
        User Sruti = new User("Sruti", "Guhathakurta", "sruti@gatech.edu", "pass");
        Sruti.setStatus("Can someone please teach me Javascript?");
        Sruti.setInterests("Coding Running Traveling Food");
        User Bob = new User("Bob", "Smith", "Bob@bob.edu", "bob");
        Bob.setStatus("Looking for people to play football with");
        Bob.setInterests("Math Philosophy");
        User Potato = new User("Potato", "Salad", "potato@salad.com", "potato");
        Potato.setStatus("Does someone want to cook potatoes with me?");
        Potato.setInterests("Potato Sweet Potato Yam");
        User Pablo = new User ("Pablo", "Picasso", "pablo.picasso@gmail.com", "pablo");
        Pablo.setStatus("Available");
        Pablo.setInterests("Art Painting History");
        users.add(Potato);
        users.add(Sruti);
        users.add(Bob);
        users.add(Pablo);
        people_list = (RecyclerView) flayout.findViewById(R.id.people_list);
        nearbyManager = new LinearLayoutManager(getActivity());
        people_list.setLayoutManager(nearbyManager);
        nearbyAdapter = new NearbyAdapter(users);
        people_list.setAdapter(nearbyAdapter);
        FloatingActionButton action = (FloatingActionButton) flayout.findViewById(R.id.fab);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, new MapFragment(), "MapFragment");
                ft.commit();
            }
        });
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
