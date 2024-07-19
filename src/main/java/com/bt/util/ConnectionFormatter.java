package com.bt.util;

public class ConnectionFormatter {
    public static String formatConnection(String location1, String location2) {
        return location1.compareTo(location2) < 0
                ? location1 + " <-> " + location2
                : location2 + " <-> " + location1;
    }
}
