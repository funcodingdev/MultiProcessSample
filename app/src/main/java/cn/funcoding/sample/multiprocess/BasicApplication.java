package cn.funcoding.sample.multiprocess;

import android.app.Application;

public class BasicApplication extends Application {
    private static BasicApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static BasicApplication getApp() {
        return sInstance;
    }
}
