package com.aroundme.around.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aroundme.around.R;
import com.aroundme.around.models.Profile;
import com.aroundme.around.models.ProfileLoader;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static com.aroundme.around.R.id.status_ic;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    ImageView status_ic;
    int person_id;
    ImageButton message_user, star_ic;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(int person_id) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("person_id", person_id);
        profileFragment.setArguments(bundle);
        return profileFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            person_id = getArguments().getInt("person_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if (getArguments() != null) {
            person_id = getArguments().getInt("person_id");
        }
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        status_ic = (ImageView) v.findViewById(R.id.status_ic);
        message_user = (ImageButton) v.findViewById(R.id.message_user);
        star_ic = (ImageButton) v.findViewById(R.id.star_ic);
        message_user.setVisibility(View.GONE);
        star_ic.setVisibility(View.GONE);
        if (person_id != 0) {
            message_user.setVisibility(View.VISIBLE);
            star_ic.setVisibility(View.VISIBLE);
        }

        URL url;
        try {
            Profile p = new ProfileLoader().execute("" + Holder.id).get();
            ImageView finder = (ImageView) v.findViewById(R.id.picture);
            Picasso.with(getContext()).load(p.getImg().split("\t<", 2)[0]).into(finder);

            ((TextView) v.findViewById(R.id.full_name)).setText(p.getFirstName() + p.getLastName());
            ((TextView) v.findViewById(R.id.interests)).setText(p.getInterests());
            ((TextView) v.findViewById(R.id.status_text)).setText(p.getStatus());
            if (p.getStatus().trim().equals("Do Not Disturb")) {
                status_ic.setImageResource(R.drawable.ic_unavailable);
            };

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return v;
    }

    private Bitmap getclip(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        // paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2,
                bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
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
