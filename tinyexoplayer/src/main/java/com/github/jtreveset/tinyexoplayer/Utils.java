package com.github.jtreveset.tinyexoplayer;

import android.os.Build;

import com.google.android.exoplayer.ExoPlayerLibraryInfo;

/**
 * Utility class
 */
public class Utils {

    /**
     * Builds a User-Agent string for the TinyExoPlayer
     * @return user agent string
     */
    public static String buildUserAgent() {
        return "(Linux;Android " + Build.VERSION.RELEASE + ") " + "TinyExoplayer/"
                + BuildConfig.VERSION_NAME + " (ExoPlayerLib/" + ExoPlayerLibraryInfo.VERSION + ")";
    }
}
