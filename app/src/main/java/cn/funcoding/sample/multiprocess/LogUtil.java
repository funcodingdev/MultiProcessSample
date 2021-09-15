package cn.funcoding.sample.multiprocess;

import android.util.Log;

public class LogUtil {
    private static final String TAG = "LogUtil";

    public static void i(String msg) {
        Log.i(TAG, "[" + ProcessUtils.getCurrentProcessName() + "]" + "[Info]: " + msg);
    }
}
