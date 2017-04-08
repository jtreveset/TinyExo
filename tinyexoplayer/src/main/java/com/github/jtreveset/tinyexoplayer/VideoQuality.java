package com.github.jtreveset.tinyexoplayer;

import java.util.Locale;

/**
 * Small class that represents a Video quality by its bitrate
 * Provides a toString overload to construct a human-readable format
 */
public class VideoQuality {

    private long bitrate;
    private boolean auto;

    public VideoQuality(long bitrate, boolean auto) {
        this.bitrate = bitrate;
        this.auto = auto;
    }

    @Override
    public String toString() {

        String sBitrate;

        if (auto) {
            sBitrate = "Auto";
        } else if (bitrate > 0) {
            if (bitrate < 1e3) { // Smaller than 1Kbps
                sBitrate = String.format(Locale.US, "%dbps", bitrate);
            } else if (bitrate < 1e6) { // Smaller than 1Mbps
                sBitrate = String.format(Locale.US, "%.0fKbps", bitrate/1e3);
            } else {                    // Greater than 1Mbps
                sBitrate = String.format(Locale.US, "%.2fMbps", bitrate/1e6);
            }
        } else {
            sBitrate = "-";
        }

        return sBitrate;
    }

    public long getBitrate() {
        return bitrate;
    }

    public boolean isAutomatic() {
        return auto;
    }
}
