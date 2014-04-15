package com.kamalan.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hesam on 15/04/14
 */
public class MySpStorage {

    private static final String TAG = "MySpStorage";
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String FACEBOOK_TOKEN = "FacebookToken";

    /**
     * This method stores Facebook token into shared preferences file
     * @param context Application context
     * @param token The token that is returned by Facebook
     */
    public static void setFacebookToken(Context context, String token) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(FACEBOOK_TOKEN, token);
        editor.commit();
    }

    /**
     * This method returns stored token of Facebook. If there is nothing then returns null.
     * @param context Application context
     * @return token The token that is returned by Facebook
     */
    public static String getFacebookToken(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(FACEBOOK_TOKEN, null);
    }
}
