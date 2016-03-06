package com.vanhacks.vandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class PersonalInfoFragment extends Fragment {

    private OnFragmentChangeListener mOnFragmentChangeListener;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    public static PersonalInfoFragment newInstance() {
        PersonalInfoFragment fragment = new PersonalInfoFragment();
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

                mOnFragmentChangeListener.launchFragment(PartnerInfoFragment.newInstance(bundle));
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
//        EditText mobileNumber = (EditText) view.findViewById(R.id.mobile_phone);
//        PhoneNumberUtils.formatNumber(mobileNumber.getText().toString());
//        EditText workPhone = (EditText) view.findViewById(R.id.work_phone);
//        PhoneNumberUtils.formatNumber(workPhone.getText().toString());

        EditText firstName = (EditText) view.findViewById(R.id.first_name);
        EditText lastName = (EditText) view.findViewById(R.id.last_name);
        EditText birthDate = (EditText) view.findViewById(R.id.birthday);
        EditText height = (EditText) view.findViewById(R.id.height);
        EditText weight = (EditText) view.findViewById(R.id.weight);
        EditText mobile = (EditText) view.findViewById(R.id.mobile_phone);
        EditText homeAddress = (EditText) view.findViewById(R.id.home_address);
        EditText workAddress = (EditText) view.findViewById(R.id.work_address);
        EditText workPhone = (EditText) view.findViewById(R.id.work_phone);
        EditText licensePlate = (EditText) view.findViewById(R.id.license_plate);

        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        birthDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        homeAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        workAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        workPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
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
