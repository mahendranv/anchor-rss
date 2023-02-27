package com.github.mahendranv.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SecondsConverterTest {

    @Test
    public void testSecondsToString() {
        // Test with positive seconds
        String expected = "5:34:59";
        long seconds = (5 * 3600L) + (34 * 60L) + 59;
        String actual = SecondsConverter.secondsToString(seconds);
        assertEquals(expected, actual);

        // Test with negative seconds
        expected = "-5:34:59";
        seconds = -((5 * 3600L) + (34 * 60L) + 59);
        actual = SecondsConverter.secondsToString(seconds);
        assertEquals(expected, actual);

        // Test with zero seconds
        expected = "0:00:00";
        seconds = 0L;
        actual = SecondsConverter.secondsToString(seconds);
        assertEquals(expected, actual);
    }

    @Test
    public void testStringToSeconds() {
        // Test with valid input string
        String durationString = "10:30:15";
        long expected = (10 * 3600L) + (30 * 60L) + 15;
        long actual = SecondsConverter.stringToSeconds(durationString);
        assertEquals(expected, actual);

        // Test with input string that has leading zeros
        durationString = "01:02:03";
        expected = (1 * 3600L) + (2 * 60L) + 3;
        actual = SecondsConverter.stringToSeconds(durationString);
        assertEquals(expected, actual);

        // Test with input string that has only seconds
        durationString = "0:0:45";
        expected = 45L;
        actual = SecondsConverter.stringToSeconds(durationString);
        assertEquals(expected, actual);

        // Test with input string that has invalid format
        durationString = "1:2:3:4";
        String finalDurationString = durationString;
        assertThrows(IllegalArgumentException.class, () -> {
            SecondsConverter.stringToSeconds(finalDurationString);
        });
    }
}
