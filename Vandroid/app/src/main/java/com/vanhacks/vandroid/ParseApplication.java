package com.vanhacks.vandroid;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by mathewt on 3/5/16.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("VanHacks")
                        .clientKey("spiderman")
                        .enableLocalDataStore()
                        .server("http://66.175.210.39:1337/parse/")
                        .build()
        );
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
    }
}
