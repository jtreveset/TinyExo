package com.github.jtreveset.tinyexoplayer;

import android.os.Build;

import com.google.android.exoplayer.ExoPlayerLibraryInfo;

/**
 * Created by Admin on 08/04/2017.
 */

public class Utils {
    public static String buildUserAgent() {
        return "(Linux;Android " + Build.VERSION.RELEASE + ") " + "TinyExoplayer/"
                + BuildConfig.VERSION_NAME + " (ExoPlayerLib/" + ExoPlayerLibraryInfo.VERSION + ")";
    }
}
