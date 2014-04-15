package com.kamalan.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Hesam on 15/04/14
 */
public class MyInternetConnection {

    private static String TAG = "MyInternetConnection";

    private static int internetStatus = -1;
    private static String internetType = null;


    public static boolean isAvailable(Context context) {
        boolean found = false;

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                found = true;
                internetStatus = 0;
            }

            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo _3g  = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isConnected())
                internetType = "WIFI";

            if (_3g.isConnected())
                internetType = "3G";

        } catch (Exception e) {
            Log.e("CheckConnectivity Exception", e.getMessage(), e);
        }

        if(!found)
            Log.e(TAG, "Internet Connection not found.");

        return found;
    }

    public static int getInternetStatus() {
        return internetStatus;
    }

    public static String getInternetType() {
        return internetType;
    }
}
