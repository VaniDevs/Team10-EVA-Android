package com.vanhacks.vandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class PersonalInfoFragment extends Fragment {

    private OnFragmentChangeListener mOnFragmentChangeListener;
    private Bundle mEmailPwordBundle;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    public static PersonalInfoFragment newInstance(Bundle arguments) {
        PersonalInfoFragment fragment = new PersonalInfoFragment();
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

        mEmailPwordBundle = getArguments();

        final FloatingActionButton floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.next_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionButton.setVisibility(View.GONE);

                View v = getView();
                EditText firstName = (EditText) v.findViewById(R.id.first_name);
                EditText lastName = (EditText) v.findViewById(R.id.last_name);
//                EditText email = (EditText) v.findViewById(R.id.email);
                EditText birthDate = (EditText) v.findViewById(R.id.birthday);
                EditText height = (EditText) v.findViewById(R.id.height);
                EditText weight = (EditText) v.findViewById(R.id.weight);
                EditText mobile = (EditText) v.findViewById(R.id.mobile_phone);
                EditText homeAddress = (EditText) v.findViewById(R.id.home_address);
                EditText workAddress = (EditText) v.findViewById(R.id.work_address);
                EditText workPhone = (EditText) v.findViewById(R.id.work_phone);
                EditText licensePlate = (EditText) v.findViewById(R.id.license_plate);

//                //Super crappy code to test Parse functionality
//                ParseUser user = new ParseUser();
//                user.setUsername(firstName.getText().toString());
//                user.setPassword("1234");
////                user.setEmail(email.getText().toString());
//                // other fields can be set just like with ParseObject
//                user.put("phone", mobile.getText().toString());
//                user.put("firstName",firstName.getText().toString());
//                user.put("lastName", lastName.getText().toString());
//                user.signUpInBackground(new SignUpCallback() {
//                    public void done(ParseException e) {
//                        if (e == null) {
//                            // Hooray! Let them use the app now.
//                            Log.d("parse", "things worked!");
//                        } else {
//                            // Sign up didn't succeed. Look at the ParseException
//                            // to figure out what went wrong
//                            Log.d("parse", e.getMessage(), e);
//                        }
//                    }
//                });
//                try {
//                    user.pin();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

                ParseUser user = mOnFragmentChangeListener.getNewUser();
                user.setUsername(firstName.getText().toString() + " " + lastName.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.FIRST_NAME, firstName.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.LAST_NAME, lastName.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.BIRTH_DATE, birthDate.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.HEIGHT, height.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.WEIGHT, weight.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.MOBILE_PHONE, mobile.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.HOME_ADDRESS, homeAddress.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.WORK_ADDRESS, workAddress.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.WORK_PHONE, workPhone.getText().toString());
                mOnFragmentChangeListener.putToNewUser(HomeActivity.LICENSE_PLATE, licensePlate.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putString(HomeActivity.FIRST_NAME, firstName.getText().toString());
                bundle.putString(HomeActivity.LAST_NAME, lastName.getText().toString());
                bundle.putString(HomeActivity.BIRTH_DATE, birthDate.getText().toString());
                bundle.putString(HomeActivity.HEIGHT, height.getText().toString());
                bundle.putString(HomeActivity.WEIGHT, weight.getText().toString());
                bundle.putString(HomeActivity.MOBILE_PHONE, mobile.getText().toString());
                bundle.putString(HomeActivity.HOME_ADDRESS, homeAddress.getText().toString());
                bundle.putString(HomeActivity.WORK_ADDRESS, workAddress.getText().toString());
                bundle.putString(HomeActivity.WORK_PHONE, workPhone.getText().toString());
                bundle.putString(HomeActivity.LICENSE_PLATE, licensePlate.getText().toString());
                bundle.putAll(mEmailPwordBundle);

                mOnFragmentChangeListener.launchFragment(PartnerInfoFragment.newInstance(bundle));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
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
