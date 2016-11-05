package com.aroundme.around.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.aroundme.around.R;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileSettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner statusSpinner;
    Button enter_status;
    EditText status;
    LinearLayout status_enter;
    String[] statuses = {"Available", "Do Not Disturb", "Custom...", null};


    public ProfileSettingsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout flayout = (FrameLayout) inflater.inflate(R.layout.fragment_profile_settings, container, false);
        status_enter = (LinearLayout) flayout.findViewById(R.id.status_enter);
        status = (EditText) flayout.findViewById(R.id.status);
        enter_status = (Button) flayout.findViewById(R.id.enter_status);
        enter_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                String userStatus = status.getText().toString();
                statuses[3] = userStatus;
                statusSpinner.setSelection(3);
                status_enter.setVisibility(View.GONE);
            }
        });
        status_enter.setVisibility(View.GONE);
        statusSpinner = (Spinner) flayout.findViewById(R.id.status_spinner);
        int[] images = {R.drawable.ic_available, R.drawable.ic_unavailable};
        StatusAdapter arrayAdapter = new StatusAdapter(super.getActivity(), R.layout.status_item, statuses, images);
        arrayAdapter.setDropDownViewResource(R.layout.status_item);
        statusSpinner.setOnItemSelectedListener(this);
        statusSpinner.setAdapter(arrayAdapter);
        statusSpinner.setSelection(0);
        return flayout;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (item.equals("Custom...")) {
            status_enter.setVisibility(View.VISIBLE);
        } else if ((item.equals("Available") || item.equals("Do Not Disturb"))){
            status_enter.setVisibility(View.GONE);
            statuses[3] = null;
        } else if (position == 3) {
            status_enter.setVisibility(View.GONE);
        }
        statusSpinner.setSelection(position);

    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

}
