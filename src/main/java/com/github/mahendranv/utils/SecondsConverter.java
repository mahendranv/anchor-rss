package com.github.mahendranv.utils;

public class SecondsConverter {

    /**
     * Converts a duration in seconds to a string in the format "h:mm:ss"
     */
    public static String secondsToString(long seconds) {
        long absSeconds = Math.abs(seconds);
        String sign = seconds < 0 ? "-" : "";
        long hours = absSeconds / 3600;
        long minutes = (absSeconds % 3600) / 60;
        long secondsOnly = absSeconds % 60;
        return String.format("%s%d:%02d:%02d", sign, hours, minutes, secondsOnly);
    }

    /**
     * Converts a string in the format "h:mm:ss" to a duration in seconds
     *
     * @param durationString
     * @return duration in seconds.
     */
    public static long stringToSeconds(String durationString) {
        String[] parts = durationString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        if (parts.length > 3) {
            throw new IllegalArgumentException("Given string is not in h:m:s format > " + durationString);
        }
        return (hours * 3600L) + (minutes * 60L) + seconds;
    }
}