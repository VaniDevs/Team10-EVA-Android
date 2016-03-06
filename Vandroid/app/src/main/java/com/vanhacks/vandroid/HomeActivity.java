package com.vanhacks.vandroid;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class HomeActivity extends AppCompatActivity implements OnFragmentChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        // start of notif stuff
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notif_icon_test)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        int mId = 001;
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        set the intent stuff
        Intent resultIntent = new Intent(this, HomeActivity.class);
        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        Notification not = mBuilder.build();
        not.flags = Notification.FLAG_ONGOING_EVENT;
        mNotificationManager.notify(mId, not);
        // end of notif stuff
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d("test","est");
//        ParseUser user = new ParseUser();
//        user.setUsername("test");
//        user.setPassword("1234");
//        user.setEmail("email@example.com");
//        // other fields can be set just like with ParseObject
//        user.put("phone", "650-253-0000");
//        user.signUpInBackground(new SignUpCallback() {
//            public void done(ParseException e) {
//                if (e == null) {
//                    // Hooray! Let them use the app now.
//                    Log.d("parse", "things worked!");
//                } else {
//                    // Sign up didn't succeed. Look at the ParseException
//                    // to figure out what went wrong
//                    Log.d("parse", e.getMessage(), e);
//                }
//            }
//        });
//        try {
//            user.pin();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, ButtonFragment.newInstance())
                    .commit();
        } else {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, PersonalInfoFragment.newInstance())
                    .commit();
        }

    }

    @Override
    public void launchFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
