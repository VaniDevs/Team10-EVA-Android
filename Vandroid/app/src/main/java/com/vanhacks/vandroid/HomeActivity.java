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

import com.parse.ParseException;
import com.parse.ParseCloud;
import com.parse.FunctionCallback;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements OnFragmentChangeListener {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String BIRTH_DATE = "birthDate";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String MOBILE_PHONE = "mobilePhone";
    public static final String HOME_ADDRESS = "homeAddress";
    public static final String WORK_ADDRESS = "workAddress";
    public static final String WORK_PHONE = "workPhone";
    public static final String LICENSE_PLATE = "licensePlate";
    public static final String PARTNER_FIRST_NAME = "firstNamePartner";
    public static final String PARTNER_LAST_NAME = "lastNamePartner";
    public static final String PARTNER_BIRTH_DATE = "birthDatePartner";
    public static final String PARTNER_HEIGHT = "heightPartner";
    public static final String PARTNER_WEIGHT = "weightPartner";
    public static final String PARTNER_MOBILE_PHONE = "mobilePhonePartner";
    public static final String PARTNER_HOME_ADDRESS = "homeAddressPartner";
    public static final String PARTNER_WORK_ADDRESS = "workAddressPartner";
    public static final String PARTNER_WORK_PHONE = "workPhonePartner";
    public static final String PARTNER_LICENSE_PLATE = "licensePlatePartner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("firstName", "Daniel");
                params.put("lastName", "Daniel");
                params.put("phone", "1234");
                params.put("email", "danieltsang94@gmail.com");
                params.put("username", "danieltsang94@gmail.com");
                params.put("password", "lolok");
// other fields can be set just like with ParseObject
                Log.d("test", "test");
                ParseCloud.callFunctionInBackground("submitForm", params, new FunctionCallback<String>() {
                    public void done(String s, ParseException e) {
                        if (e == null) {
                            Log.d("work", "itworks?");
                        } else {
                            Log.d("sad", e.getMessage());
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });

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


//        Parse.enableLocalDatastore(this);
//
//        Parse.initialize(new Parse.Configuration.Builder(this)
//                        .applicationId("VanHacks")
//                        .clientKey("spiderman")
//                        .server("http://66.175.210.39:1337/parse/")
//                        .build()
//        );

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, PersonalInfoFragment.newInstance())
                .commit();
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
