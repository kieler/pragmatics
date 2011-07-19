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

import de.cau.cs.kieler.kwebs.logging.Logger.LoggerEvent;

/**
 * This class registers as {@link ILoggerListener} and provides size based round trip
 * file logging.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class RoundTripFileLogging extends FileLogging {

    /** */
    private static final long BYTESFROMMBYTES_MULTIPLIER 
        = 1024 * 1024;

    /** */
    private static final int ROUNDTRIPSIZE_DEFAULTVALUE
        = 10;

    /** */
    private long roundTripMbytes
        = ROUNDTRIPSIZE_DEFAULTVALUE * BYTESFROMMBYTES_MULTIPLIER;

    /** */
    private static final String DEFAULT_LOGNAME
        = "kwebs.log"; //$NON-NLS-1$

    /**
     *
     * @param logFileName
     */
    public RoundTripFileLogging() {
        super(DEFAULT_LOGNAME);
    }

    /**
     *
     * @param logFileName
     */
    public RoundTripFileLogging(final String logFileName) {
        super(logFileName);
    }

    /**
     *
     * @param days
     * @param mbytes
     */
    public RoundTripFileLogging(final int thembytes) {
        super(DEFAULT_LOGNAME);
        roundTripMbytes = thembytes * BYTESFROMMBYTES_MULTIPLIER;
    }

    /**
     *
     * @param logFileName
     * @param mode
     * @param days
     * @param mbytes
     */
    public RoundTripFileLogging(final String thelogFileName, final int thembytes) {
        super(thelogFileName);
        roundTripMbytes = thembytes * BYTESFROMMBYTES_MULTIPLIER;
    }

    /**
     *
     * @return
     */
    public final long getMBytes() {
        return roundTripMbytes / BYTESFROMMBYTES_MULTIPLIER;
    }

    /**
     *
     * @param mbytes
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
