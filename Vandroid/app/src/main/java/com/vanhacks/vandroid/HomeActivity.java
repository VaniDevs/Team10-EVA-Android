package com.vanhacks.vandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ParseUser user = new ParseUser();
//                user.setUsername("my name");
//                user.setPassword("my pass");
//                user.setEmail("email@example.com");

// other fields can be set just like with ParseObject
//                Log.d("test","test");
//                user.signUpInBackground(new SignUpCallback() {
//                    public void done(ParseException e) {
//                        if (e == null) {
//                            Log.d("work","itworks?");
//                        } else {
//                            Log.d("sad",e.getMessage());
//                            // Sign up didn't succeed. Look at the ParseException
//                            // to figure out what went wrong
//                        }
//                    }
//                });
//            }
//        });

        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("Vanhacks")
                        .clientKey("spiderman")
                        .server("http://66.175.210.39:1337/parse")
                        .build()
        );

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, PersonalInfoFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
