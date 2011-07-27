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
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
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
public class JaxWsAdapter extends Handler implements java.util.logging.Filter {
        
    /** The singleton instance. */
    private static final JaxWsAdapter INSTANCE
        = new JaxWsAdapter();
        
    /** Prefix added to messages from jaxws logging events. */ 
    private static final String JAXWS_PREFIX
        = "<JAXWS> ";
    
    /**
     * Registers the singleton instance of this class as filter for the global
     * java logger.
     */
    public static void register() {
        java.util.logging.Logger topLogger = java.util.logging.Logger.getLogger("");
        Handler consoleHandler = null;
        for (java.util.logging.Handler handler : topLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                consoleHandler = handler;
                break;
            }
        }
        if (consoleHandler == null) {
            consoleHandler = new java.util.logging.ConsoleHandler();
            topLogger.addHandler(consoleHandler);
        }
        consoleHandler.setFilter(INSTANCE);
        
    }
    
    /**
     * {@inheritDoc}
     */
    public void close() {
    }

    /**
     * {@inheritDoc}
     */
    public void flush() {
    }

    /**
     * {@inheritDoc}
     */
    public void publish(final LogRecord record) {
        isLoggable(record);
    }    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLoggable(final LogRecord record) {
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
        return false;
    }
 
}
