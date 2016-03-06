package com.vanhacks.vandroid;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginFragment extends android.app.Fragment {

    OnFragmentChangeListener mOnFragmentChangeListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnFragmentChangeListener = (OnFragmentChangeListener) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final FloatingActionButton floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.next_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionButton.setVisibility(View.GONE);

                View v = getView();
                EditText email = (EditText) v.findViewById(R.id.email);
                EditText password = (EditText) v.findViewById(R.id.password);

                ParseUser.logInInBackground(email.getText().toString().trim(), password.getText().toString(), new
//                ParseUser.logInInBackground("rr@rr.ca", "rr", new
                        LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    try {
                                        user.pin();
                                    } catch (ParseException e1) {
                                        e1.printStackTrace();
                                    }
                                    mOnFragmentChangeListener.launchFragment(ButtonFragment.newInstance());
                                } else {
                                    mOnFragmentChangeListener.showLoginFailed();
                                    floatingActionButton.setVisibility(View.VISIBLE);
                                }
                            }
                        });
//                try {
//                    ParseUser user = ParseUser.logIn(email.getText().toString(), password.getText().toString());
//                    if (user != null) {
//                        try {
//                            user.pin();
//                        } catch (ParseException e1) {
//                            e1.printStackTrace();
//                        }
//                        mOnFragmentChangeListener.launchFragment(ButtonFragment.newInstance());
//                    } else {
//                        mOnFragmentChangeListener.showLoginFailed();
//                        floatingActionButton.setVisibility(View.VISIBLE);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }



                Bundle bundle = new Bundle();
                bundle.putString(HomeActivity.EMAIL, email.getText().toString());
                bundle.putString(HomeActivity.PASSWORD, password.getText().toString());


            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText password = (EditText) view.findViewById(R.id.password);

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        return view;
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
