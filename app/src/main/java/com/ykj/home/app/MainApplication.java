package com.ykj.home.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by kejie.yuan
 * Date: 2019/7/23
 * Description: ${DESCRIPTION}
 */
public class MainApplication extends Application {

    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MainApplication getInstance() {
        return instance;
    }

    public Context getContext() {
        return getApplicationContext();
    }
}
