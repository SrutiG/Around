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
import com.aroundme.around.models.MapBridge;
import com.aroundme.around.models.Profile;
import com.aroundme.around.models.ProfileLoader;
import com.aroundme.around.models.Status;
import com.aroundme.around.models.StatusFetcher;
import com.aroundme.around.models.User;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
        String s = null;
        try {
            s = new MapBridge().execute("read").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (String ss: s.split("<br/>")) {
            String[] entries = ss.split(" , ");
            int i = Integer.parseInt(entries[0].trim());
            String firstName = entries[1];
            String lastName = entries[2];
            double lat = Double.parseDouble(entries[3].trim());
            double lon = Double.parseDouble(entries[4].trim());
            double distance = Double.parseDouble(entries[5].trim());

            User user = new User(i, firstName, lastName, null, null, null);
            try {
                Profile prof = new ProfileLoader().execute("" + i).get();
                user.setStatus(prof.getStatus());
                user.setInterests(prof.getInterests());
                user.setImage(prof.getImg().split("\t", 2)[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            users.add(user);
        }
        // Inflate the layout for this fragment
        FrameLayout flayout = (FrameLayout) inflater.inflate(R.layout.fragment_nearby, container, false);
        people_list = (RecyclerView) flayout.findViewById(R.id.people_list);
        nearbyManager = new LinearLayoutManager(getActivity());
        people_list.setLayoutManager(nearbyManager);
        nearbyAdapter = new NearbyAdapter(users, getContext());
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
