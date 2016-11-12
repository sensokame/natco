package com.example.houcem.natco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;


/**
 * Created by houcem on 11/11/16.
 */

public class SplashActivity extends Activity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent;
                if(AccessToken.getCurrentAccessToken()!=null)
                Log.e("checking credentials",AccessToken.getCurrentAccessToken().toString());
                if(AccessToken.getCurrentAccessToken()!=null){
                    mainIntent = new Intent(SplashActivity.this,MainPage.class);
                }
                else{
                    mainIntent = new Intent(SplashActivity.this,LoginPage.class);
                }

                SplashActivity.this.startActivity(mainIntent);
                SplashActivity
                        .this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

