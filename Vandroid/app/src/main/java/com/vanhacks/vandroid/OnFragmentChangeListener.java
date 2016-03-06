package com.vanhacks.vandroid;

import android.app.Fragment;

import com.parse.ParseUser;

public interface OnFragmentChangeListener {
    void launchFragment(Fragment fragment);
    void putToNewUser(String key, String value);
    void setNewUser(ParseUser user);
    ParseUser getNewUser();
    void showAlertSentMessage();
    void showOkaySentMessage();
    void showLoginFailed();
}
