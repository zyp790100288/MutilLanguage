package com.intretech.app.mutillanguage.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */

public class SpfUtils {
    public  final static String spf_name="mutil_spf";
    public final static String keyLanguage = "keyLanguage";


    public static void saveAppLanguage(Context context, int languageType){
        SharedPreferences spf=context.getSharedPreferences(spf_name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=spf.edit();
        editor.putInt(keyLanguage,languageType);
        editor.commit();

    }
    public static int getAppLanguage(Context context){
        SharedPreferences spf=context.getSharedPreferences(spf_name, Activity.MODE_PRIVATE);
        int languageType =spf.getInt(keyLanguage,0);
        return languageType;
    }
}
