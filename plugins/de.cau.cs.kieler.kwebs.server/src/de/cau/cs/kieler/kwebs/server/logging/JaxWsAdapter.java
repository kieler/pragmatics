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
import java.util.Enumeration;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
//FIXME just implemented for testing purposes yet.
/**
 * Adapter for catching the jaxws logging events and log them in our own logger.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class JaxWsAdapter extends Handler {
    
    /** The singleton instance. */
    private static final JaxWsAdapter INSTANCE
        = new JaxWsAdapter();
    
    /** */
    private static final String COMSUNXML_PREFIX
        = "com.sun.xml.";
    
    /**
     * 
     */
    public static void registerToLoggers() {
        INSTANCE.setLevel(Level.ALL);
        LogManager logManager = LogManager.getLogManager();
        Enumeration<String> loggers = logManager.getLoggerNames();
        String loggerName = null;
        java.util.logging.Logger logger = null;
        java.util.logging.Handler[] loggerHandlers = null;
        while (loggers.hasMoreElements()) {
            loggerName = loggers.nextElement();
            if (loggerName.startsWith(COMSUNXML_PREFIX)) {
                Logger.log(Severity.DEBUG, "Registering to " + loggerName);
                logger = logManager.getLogger(loggerName);
                if (logger != null) {
                    loggerHandlers = logger.getHandlers();
                    for (java.util.logging.Handler loggerHandler : loggerHandlers) {
                        loggerHandler.setLevel(Level.OFF);
                    }                    
                    logger.addHandler(INSTANCE);
                } else {
                    Logger.log(Severity.DEBUG, "Registered logger " + loggerName + " was null");
                }
            }
        }
    }

    /** Prefix added to messages from jaxws logging events. */ 
    private static final String JAXWS_PREFIX
        = "<JAXWS> ";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void publish(final LogRecord record) {
        Logger.log(Severity.DEBUG, "Publishing event to logger");
        Severity severity = Severity.ALWAYS;
        Logger.log(
            new Date(record.getMillis()),
            record.getSourceClassName(),
            record.getSourceMethodName(),
            null,
            severity,
            JAXWS_PREFIX + record.getMessage(),
            null,
            record.getThrown()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void flush() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
    }
 
}
