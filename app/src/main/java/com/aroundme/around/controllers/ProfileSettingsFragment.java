package com.aroundme.around.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.aroundme.around.R;
import com.aroundme.around.models.Profile;
import com.aroundme.around.models.ProfileLoader;
import com.aroundme.around.models.UserUpdater;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static android.R.id.edit;
import static android.app.Activity.RESULT_OK;
import static com.aroundme.around.R.id.edit_pic;
import static com.aroundme.around.R.id.status_spinner;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileSettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner statusSpinner;
    Button enter_status, edit_pic;
    EditText status;
    ImageView editPic;
    TextView logoutBT;
    LinearLayout status_enter;
    MainActivity main;
    String[] statuses = {"Available", "Do Not Disturb", "Custom...", null};


    public ProfileSettingsFragment() {
        // Required empty public constructor
    }

    public void setMain(MainActivity main) { this.main = main; }

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
        edit_pic = (Button) flayout.findViewById(R.id.edit_pic);
        logoutBT = (TextView) flayout.findViewById(R.id.logoutBT);
        editPic = (ImageView) flayout.findViewById(R.id.editPic);

//        edit_pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openGallery(SELECT_FILE1);
//            }
//        });
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
        logoutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getActivity(), LoginActivity.class);
                SharedPreferences sp = getActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
                Editor editor = sp.edit();
                editor.remove("login");
                startActivity(login);

            }
        });
        status_enter.setVisibility(View.GONE);
        statusSpinner = (Spinner) flayout.findViewById(status_spinner);
        int[] images = {R.drawable.ic_available, R.drawable.ic_unavailable};
        StatusAdapter arrayAdapter = new StatusAdapter(super.getActivity(), R.layout.status_item, statuses, images);
        arrayAdapter.setDropDownViewResource(R.layout.status_item);
        statusSpinner.setOnItemSelectedListener(this);
        statusSpinner.setAdapter(arrayAdapter);
        statusSpinner.setSelection(0);


        try {
            Profile p = new ProfileLoader().execute("" + Holder.id).get();

            ((EditText) flayout.findViewById(R.id.full_name_text)).setText(p.getFirstName() + p.getLastName());
            if ((p.getStatus().trim() != "") && (!(p.getStatus().trim().equals("Available")) && (!(p.getStatus().trim().equals("Do Not Disturb"))))) {
                statuses[3] = p.getStatus().trim().toString();
                statusSpinner.setSelection(3);
            } else if (p.getStatus().trim().equals("Available")) {
                statusSpinner.setSelection(0);
            } else if (p.getStatus().trim().equals("Do Not Disturb")) {
                statusSpinner.setSelection(1);
            }
            //((EditText) flayout.findViewById(R.id.descriptionET)).setText(p.getDescription());
            ((EditText) flayout.findViewById(R.id.interestsET)).setText(p.getInterests());

            ImageView finder = (ImageView) flayout.findViewById(R.id.editPic);
            Picasso.with(getContext()).load(p.getImg().split("\t<", 2)[0]).into(finder);
            if(!p.getImg().equals("")) {
                try {
                    editPic.setImageDrawable(grabImageFromUrl(p.getImg()));
                } catch(Exception e) {

                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }

        final View flayout2 = flayout;
        final ProfileFragment pf = new ProfileFragment();

        ((Button) flayout.findViewById(R.id.submitBT)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new StatusUpdater().execute("" + Holder.id, p.getStatus(), "" + System.currentTimeMillis());
                String s = ((EditText) flayout2.findViewById(R.id.full_name_text)).getText().toString();
                if (s.indexOf(" ") > 0) {
                    new UserUpdater().execute("" + Holder.id, s.split(" ", 2)[0], s.split(" ", 2)[1],
                            ((EditText) flayout2.findViewById(R.id.interestsET)).getText().toString(), ((Spinner) flayout2.findViewById(R.id.status_spinner)).getSelectedItem().toString());
                } else {
                    new UserUpdater().execute("" + Holder.id, s, "",
                            ((EditText) flayout2.findViewById(R.id.interestsET)).getText().toString(), ((Spinner) flayout2.findViewById(R.id.status_spinner)).getSelectedItem().toString());
                }
                main.setFragment(pf);
            }
        });


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

//    public void openGallery(int req_code) {
//
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,
//                "Select file to upload "), req_code);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (resultCode == RESULT_OK) {
//            Uri selectedImageUri = data.getData();
//
//            if (requestCode == SELECT_FILE1) {
//                selectedPath1 = getPath(selectedImageUri);
//                System.out.println("selectedPath1 : " + selectedPath1);
//            }
//
//            if (requestCode == SELECT_FILE2) {
//                selectedPath2 = getPath(selectedImageUri);
//                System.out.println("selectedPath2 : " + selectedPath2);
//            }
//
//            tv.setText("Selected File paths : " + selectedPath1 + "," + selectedPath2);
//        }
//    }
//
//    public String getPath(Uri uri) {
//
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//
//        return cursor.getString(column_index);
//    }



    private Drawable grabImageFromUrl(String url) throws Exception {
        return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
    }


}
