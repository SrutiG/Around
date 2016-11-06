package com.aroundme.around.controllers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aroundme.around.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    private MainActivity main;

    public MapFragment() {
        // Required empty public constructor
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Setting here");
        MapsActivity activity = new MapsActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMyLocationEnabled(false);
                loadMarkers(googleMap, mMapView);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(Holder.tracker.getLatitude(), Holder.tracker.getLongitude());
                //googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        FloatingActionButton action = (FloatingActionButton) rootView.findViewById(R.id.fab);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, new NearbyFragment(), "NearbyFragment");
                ft.commit();
            }
        });


        return rootView;
    }

    private void loadMarkers(GoogleMap map, MapView mapView) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.image);
        LatLngBounds bounds = null; // get a bounds

        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(Holder.tracker.getLatitude(), Holder.tracker.getLongitude()))
                .title("Emory University")
                .snippet("2 people here")
                .icon(icon).zIndex(2000);

        googleMap.addMarker(markerOptions);

        icon = BitmapDescriptorFactory.fromResource(R.drawable.image2);
        MarkerOptions marker2 = new MarkerOptions().position(new LatLng(33.7756178,-84.396285))
                .title("Georgia Institute of Technology")
                .snippet("8 people here")
                .icon(icon);

        googleMap.addMarker(marker2);

        icon = BitmapDescriptorFactory.fromResource(R.drawable.image3);
        MarkerOptions marker3 = new MarkerOptions().position(new LatLng(33.770088, -84.390973))
                .title("North Avenue Apartments")
                .snippet("1 person here")
                .icon(icon);

        googleMap.addMarker(marker3);

        icon = BitmapDescriptorFactory.fromResource(R.drawable.image4);
        MarkerOptions marker4 = new MarkerOptions().position(new LatLng(33.786269, -84.375945))
                .title("Piedmont Park")
                .snippet("23 people here")
                .icon(icon);

        googleMap.addMarker(marker4);

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                LatLng loc = arg0.getPosition();
                String place = arg0.getTitle();
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", loc.latitude);
                bundle.putDouble("lon", loc.longitude);
                bundle.putString("title", place);

                Fragment frag = new FeedFragment();
                System.out.println(" : " + bundle.getDouble("lat"));
                frag.setArguments(bundle);

                System.out.println("YEP: " + main == null);
                main.setFragment(frag);
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
