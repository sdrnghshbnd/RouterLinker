package com.bt.util;

public class ConnectionFormatter {

    /**
     * Formats a connection between two locations into a string representation.
     * The format will ensure that the connection is always displayed with
     * the alphabetically smaller location first.
     *
     * @param location1 the name of the first location.
     * @param location2 the name of the second location.
     * @return a string representing the connection between the two locations,
     *         formatted as "Location1 <-> Location2" or "Location2 <-> Location1"
     *         depending on alphabetical order.
     */
    public static String formatConnection(String location1, String location2) {
        return location1.compareTo(location2) < 0
                ? location1 + " <-> " + location2
                : location2 + " <-> " + location1;
    }
}