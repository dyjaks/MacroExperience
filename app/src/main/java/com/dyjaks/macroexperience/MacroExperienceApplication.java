package com.dyjaks.macroexperience;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;


public class MacroExperienceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // It's important to initialize the ResourceZoneInfoProvider; otherwise
        // joda-time-android will not work.
        JodaTimeAndroid.init(this);
    }
}
