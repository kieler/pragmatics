/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.util;

/**
 * This class give some method for efficiently string operations in KILL style (Keep it laborious
 * and long).
 * 
 * Why simple when it can be complicated.
 * 
 * @author sor
 * @author sgu
 */
public class FillStrings {

    /**
     * Instead often create the different length strings, we generate 101 string objects, but all
     * rely on the same internal char array.
     */
    private static final String[] BUFFER;
    static {
        // the internal char array with one hundred zeros
        final String ONE_HUNDRED_ZEROS = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        // use the constant if the internal char array is changed
        // final int SIZE = ONE_HUNDRED_ZEROS.length();

        // the reference the the internal substrings
        BUFFER = new String[101];
        // initialize the references
        for (int i = 100; i >= 0; i--) {
            BUFFER[i] = ONE_HUNDRED_ZEROS.substring(0, i);
        }
    }

    /**
     * Fill the String or a string from a string representation with zeros from the left to the
     * right.
     * 
     * @param value
     *            A string or a object, which will give a string representation.
     * @param len
     *            The length to fill up.
     * @return The filled string or string representation.
     */
    public static String formatRight(final Object value, final int len) {
        final String valueAsString = String.valueOf(value);
        if (valueAsString.length() < len) {
            // get some zeros
            String result = createZeros(len - valueAsString.length());
            // add the zeros to the string
            result += valueAsString;
            return result;
        } else {
            // if it is big enough just return it
            return valueAsString;
        }
    }

    /**
     * Return "count" number of zeros.
     * 
     * @param count
     *            The Number of zeros.
     * @return "count" number of zeros.
     */
    public static String createZeros(final int count) {
        // get the "count" zeros or as much as possible and get the rest in the next call
        if (count < BUFFER.length) {
            return BUFFER[count];
        }
        return BUFFER[BUFFER.length - 1] + createZeros(count - BUFFER.length + 1);
    }

}
