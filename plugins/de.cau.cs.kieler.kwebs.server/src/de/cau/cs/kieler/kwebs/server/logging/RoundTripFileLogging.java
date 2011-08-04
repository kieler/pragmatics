/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.logging;

import de.cau.cs.kieler.kwebs.server.logging.Logger.LoggerEvent;

/**
 * This class registers as {@link ILoggerListener} and provides size based round trip
 * file logging.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class RoundTripFileLogging extends FileLogging {

    /** Constant for a megabyte. */
    private static final long BYTESFROMMBYTES_MULTIPLIER 
        = 1024 * 1024;

    /** Default maximum size of the log file in megabytes. */
    private static final int ROUNDTRIPSIZE_DEFAULTVALUE
        = 10;

    /** Currently set maximum size of the log file in megabytes. */
    private long roundTripMbytes
        = ROUNDTRIPSIZE_DEFAULTVALUE * BYTESFROMMBYTES_MULTIPLIER;

    /** Default name of the log file. */
    private static final String DEFAULT_LOGNAME
        = "kwebs.log";

    /**
     * Creates a new {@code RoundTripFileLogging} logger with default capacity 
     * and name of the log file.
     */
    public RoundTripFileLogging() {
        super(DEFAULT_LOGNAME);
    }

    /**
     * Creates a new {@code RoundTripFileLogging} logger with default capacity 
     * and given name of the log file.
     * 
     * @param logFileName
     *            the path to the log file to be used.
     */
    public RoundTripFileLogging(final String logFileName) {
        super(logFileName);
    }

    /**
     * Creates a new {@code RoundTripFileLogging} logger with given capacity 
     * and default name of the log file.
     * 
     * @param thembytes
     *            the maximum size of the log file in megabytes.
     */
    public RoundTripFileLogging(final int thembytes) {
        super(DEFAULT_LOGNAME);
        roundTripMbytes = thembytes * BYTESFROMMBYTES_MULTIPLIER;
    }

    /**
     * Creates a new {@code RoundTripFileLogging} logger with given capacity 
     * and name of the log file.
     *
     * @param thelogFileName
     *            the path to the log file to be used.
     * @param thembytes
     *            the maximum size of the log file in megabytes.
     */
    public RoundTripFileLogging(final String thelogFileName, final int thembytes) {
        super(thelogFileName);
        roundTripMbytes = thembytes * BYTESFROMMBYTES_MULTIPLIER;
    }

    /**
     * Returns the currently set maximum size of the log file.
     * 
     * @return the currently set maximum size of the log file
     */
    public final long getMBytes() {
        return roundTripMbytes / BYTESFROMMBYTES_MULTIPLIER;
    }

    /**
     * Sets the maximum size of the log file.
     * 
     * @param thembytes
     *            the new maximum size of the log file
     */
    public final void setMBytes(final int thembytes) {
        roundTripMbytes = thembytes * BYTESFROMMBYTES_MULTIPLIER;
    }

    /**
     * {@inheritDoc}
     */
    public final void loggerEvent(final LoggerEvent event) {
        //CHECKSTYLEOFF EmptyBlock
        try {
            if (super.size() >= roundTripMbytes) {
                super.clear();
            }
        } catch (Exception e) {
        }
        //CHECKSTYLEON EmptyBlock
        super.loggerEvent(event);
    }

}
