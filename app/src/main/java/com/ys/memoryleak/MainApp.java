package com.ys.memoryleak;

import android.app.Application;
import android.os.StrictMode;

/**
 * Created by shanyin on 2020/6/2
 */
public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                .detectAll() //
                .penaltyLog() //
                .penaltyDeath() //
                .build());
    }
}
