package com.vanhacks.vandroid;

import android.app.Application;

import com.parse.Parse;

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
                        .server("http://66.175.210.39:1337/parse/")
                        .build()
        );

    }
}
