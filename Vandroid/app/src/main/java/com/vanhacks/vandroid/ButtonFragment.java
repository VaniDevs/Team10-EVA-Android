package com.vanhacks.vandroid;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.plus.PlusOneButton;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class ButtonFragment extends Fragment {
    private ImageButton mMainButton;


    public ButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button, container, false);

        //Find the +1 button
        mMainButton = (ImageButton) view.findViewById(R.id.main_button);

        mMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnMainButtonClick();

            }
        });

        return view;
    }

    private void OnMainButtonClick() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseObject eventObject = new ParseObject("HelpEvent");
            eventObject.put("user", currentUser);
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
            SmsManager.getDefault().sendTextMessage("16042391416", null, "test" , null, null);
        } catch (Exception e) {

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
