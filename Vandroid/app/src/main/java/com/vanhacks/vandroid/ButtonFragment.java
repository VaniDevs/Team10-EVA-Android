package com.vanhacks.vandroid;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import org.json.JSONObject;


public class ButtonFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private OnFragmentChangeListener mOnFragmentChangeListener;
    private ImageButton mMainButton;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private ImageButton mOkayButton;
    private Object mHelpEventObject;

    public ButtonFragment() {
        // Required empty public constructor
    }

    public static ButtonFragment newInstance() {
        ButtonFragment fragment = new ButtonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnFragmentChangeListener = (OnFragmentChangeListener) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button, container, false);

        mMainButton = (ImageButton) view.findViewById(R.id.main_button);
        mMainButton.setVisibility(View.VISIBLE);
        mOkayButton = (ImageButton) view.findViewById(R.id.good_button);
        mOkayButton.setVisibility(View.GONE);

        mMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMainButtonClick();
            }
        });
        mOkayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOkayButtonClick();
            }
        });

        return view;
    }

    private void onMainButtonClick() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (mHelpEventObject != null) {
            return;
        }

        if (currentUser != null) {
            ParseObject eventObject = new ParseObject("HelpEvent");
            eventObject.put("user", currentUser);
            if (!(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
                if (mLastLocation != null) {
                    eventObject.put("lon", mLastLocation.getLongitude());
                    eventObject.put("lat", mLastLocation.getLatitude());

                }
            }
            eventObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        // Hooray! Let them use the app now.
                        Log.d("parse", "things worked!");
                    } else {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                        Log.d("parse", e.getMessage(), e);
                    }
                }
            });
        }

        try {
            Log.d("parse", "sending SMS");
            JSONObject object = new JSONObject();
            object.put("email", currentUser.getEmail()) ;
            if (!(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
                if (mLastLocation != null) {
                    object.put("lat",mLastLocation.getLatitude());
                    object.put("long",mLastLocation.getLongitude());
                }
            }
            mHelpEventObject = object;
            SmsManager.getDefault().sendTextMessage("+16042391416", null, object.toString(), null, null);
            mOnFragmentChangeListener.showAlertSentMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMainButton.setVisibility(View.GONE);
        mOkayButton.setVisibility(View.VISIBLE);
    }

    private void onOkayButtonClick() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseObject eventObject = new ParseObject("SafeEvent");
            eventObject.put("user", currentUser);
            if (!(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
                if (mLastLocation != null) {
                    eventObject.put("lon", mLastLocation.getLongitude());
                    eventObject.put("lat", mLastLocation.getLatitude());
                }
            }
            eventObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        // Hooray! Let them use the app now.
                        Log.d("parse", "things worked!");
                    } else {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                        Log.d("parse", e.getMessage(), e);
                    }
                }
            });
        }

        try {
            Log.d("parse", "sending SMS");
            JSONObject object = new JSONObject();
            object.put("email", currentUser.getEmail()) ;
            if (!(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
                if (mLastLocation != null) {
                    object.put("lat",mLastLocation.getLatitude());
                    object.put("long",mLastLocation.getLongitude());
                }
            }
            SmsManager.getDefault().sendTextMessage("+16042391416", null, mHelpEventObject.toString(), null, null);
            mOnFragmentChangeListener.showOkaySentMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onConnected(Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
