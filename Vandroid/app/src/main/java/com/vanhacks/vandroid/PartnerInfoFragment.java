package com.vanhacks.vandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class PartnerInfoFragment extends Fragment {

    OnFragmentChangeListener mOnFragmentChangeListener;
    Bundle mPersonalInfoBundle;

    public PartnerInfoFragment() {
        // Required empty public constructor
    }

    public static PartnerInfoFragment newInstance(Bundle arguments) {
        PartnerInfoFragment fragment = new PartnerInfoFragment();
        fragment.setArguments(arguments);
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

        mPersonalInfoBundle = getArguments();

        final FloatingActionButton floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.next_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionButton.setVisibility(View.GONE);

                View v = getView();
                EditText firstName = (EditText) v.findViewById(R.id.first_name);
                EditText lastName = (EditText) v.findViewById(R.id.last_name);
                EditText birthDate = (EditText) v.findViewById(R.id.birthday);
                EditText height = (EditText) v.findViewById(R.id.height);
                EditText weight = (EditText) v.findViewById(R.id.weight);
                EditText mobile = (EditText) v.findViewById(R.id.mobile_phone);
                EditText homeAddress = (EditText) v.findViewById(R.id.home_address);
                EditText workAddress = (EditText) v.findViewById(R.id.work_address);
                EditText workPhone = (EditText) v.findViewById(R.id.work_phone);
                EditText licensePlate = (EditText) v.findViewById(R.id.license_plate);

                Bundle bundle = new Bundle();
                bundle.putString(HomeActivity.PARTNER_FIRST_NAME, firstName.getText().toString());
                bundle.putString(HomeActivity.PARTNER_LAST_NAME, lastName.getText().toString());
                bundle.putString(HomeActivity.PARTNER_BIRTH_DATE, birthDate.getText().toString());
                bundle.putString(HomeActivity.PARTNER_HEIGHT, height.getText().toString());
                bundle.putString(HomeActivity.PARTNER_WEIGHT, weight.getText().toString());
                bundle.putString(HomeActivity.PARTNER_MOBILE_PHONE, mobile.getText().toString());
                bundle.putString(HomeActivity.PARTNER_HOME_ADDRESS, homeAddress.getText().toString());
                bundle.putString(HomeActivity.PARTNER_WORK_ADDRESS, workAddress.getText().toString());
                bundle.putString(HomeActivity.PARTNER_WORK_PHONE, workPhone.getText().toString());
                bundle.putString(HomeActivity.PARTNER_LICENSE_PLATE, licensePlate.getText().toString());
                bundle.putAll(mPersonalInfoBundle);

                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_FIRST_NAME, firstName.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_LAST_NAME, lastName.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_BIRTH_DATE, birthDate.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_HEIGHT, height.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_WEIGHT, weight.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_MOBILE_PHONE, mobile.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_HOME_ADDRESS, homeAddress.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_WORK_ADDRESS, workAddress.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_WORK_PHONE, workPhone.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.PARTNER_LICENSE_PLATE, licensePlate.getText().toString());

                ParseUser newUser = mOnFragmentChangeListener.getNewUser();
                newUser.signUpInBackground(new SignUpCallback() {
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
                try {
                    newUser.pin();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // TODO: bundle has errthang
                

                // TODO: don't launch new fragment; go to a main landing fragment, send bundle to service
                // TODO: send shit off here ; then we'll probably want to launch the real app fragment
                mOnFragmentChangeListener.launchFragment(ButtonFragment.newInstance());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partner_info, container, false);
        EditText licensePlate = (EditText) view.findViewById(R.id.license_plate);
        licensePlate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
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
