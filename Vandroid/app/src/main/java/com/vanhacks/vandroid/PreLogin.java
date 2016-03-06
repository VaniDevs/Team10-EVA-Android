package com.vanhacks.vandroid;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class PreLogin extends android.app.Fragment {

    OnFragmentChangeListener mOnFragmentChangeListener;

    public PreLogin() {
        // Required empty public constructor
    }

    public static PreLogin newInstance() {
        PreLogin fragment = new PreLogin();
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
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pre_login, container, false);

        Button signUp = (Button) view.findViewById(R.id.signup);
        Button login = (Button) view.findViewById(R.id.login);

        signUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mOnFragmentChangeListener.launchFragment(SignUpFragment.newInstance());
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mOnFragmentChangeListener.launchFragment(LoginFragment.newInstance());
            }
        });

        // get views of two buttons
        // set on click listeners for each
            // redirect to appropriate ... fragments
        return view;
    }
}
