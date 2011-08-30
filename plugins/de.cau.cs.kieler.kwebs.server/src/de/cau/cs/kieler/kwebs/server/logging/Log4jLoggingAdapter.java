/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.logging;

import java.util.Date;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;

/**
 * Adapter for catching the jeti log4j logging events and log them in our own logger.
 *
 * @kieler.rating  2011-08-25 proposed yellow
 *      reviewed by ckru, msp, mri
 *      
 * @author  swe
 */
public class Log4jLoggingAdapter extends AppenderSkeleton {
 
    /**
     * {@inheritDoc}
     */
    public void close() {
    }

    /**
     * {@inheritDoc}
     */
    public boolean requiresLayout() {
        return false;
    }

    /** Prefix added to messages from jeti logging events. */ 
    private static final String JETI_PREFIX
        = "<LOG4JLOG> ";
    
    /**
     * {@inheritDoc}
     */
    protected void append(final LoggingEvent event) {
        Date date = new Date(event.getTimeStamp());
        Severity severity = null;
        if (event.getLevel() == Level.ALL) {
            severity = Severity.ALWAYS;
        } else if (event.getLevel() == Level.DEBUG) {
            severity = Severity.DEBUG;
        } else if (event.getLevel() == Level.TRACE) {
            severity = Severity.DEBUG;
        } else if (event.getLevel() == Level.INFO) {
            severity = Severity.INFO;
        } else if (event.getLevel() == Level.WARN) {
            severity = Severity.WARNING;
        } else if (event.getLevel() == Level.ERROR) {
            severity = Severity.FAILURE;
        } else if (event.getLevel() == Level.FATAL) {
            severity = Severity.CRITICAL;
        } else if (event.getLevel() == Level.INFO) {
            severity = Severity.INFO;
        }
        String message = null;
        if (event.getRenderedMessage() != null) {
            message = event.getRenderedMessage();
        }
        message = JETI_PREFIX + message;
        String clas = null;
        if (event.getClass() != null) {
            clas = event.getClass().getCanonicalName();
        }
        String method = null;
        String line = null;
        if (event.getLocationInformation() != null) {
            method = event.getLocationInformation().getMethodName();
            line = event.getLocationInformation().getLineNumber();
        }
        Throwable throwable = null;
        if (event.getThrowableInformation() != null) {
            throwable = event.getThrowableInformation().getThrowable();
        }
        Logger.log(date, clas, method, line, severity, message, null, throwable);
    }

}
