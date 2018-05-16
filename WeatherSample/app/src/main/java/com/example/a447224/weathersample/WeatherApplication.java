package com.example.a447224.weathersample;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class WeatherApplication extends Application {
    public static final String TAG = WeatherApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Application Created!");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
