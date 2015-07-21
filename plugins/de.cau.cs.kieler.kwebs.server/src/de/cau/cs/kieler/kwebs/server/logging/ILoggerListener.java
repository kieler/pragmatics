/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.logging;

import de.cau.cs.kieler.kwebs.server.logging.Logger.LoggerEvent;

/**
 * Interface for listening to events from the {@link Logger}.
 *
 * @author swe
 */
public interface ILoggerListener {

    /**
     * Every listener is notified about an logger event through this method.
     * 
     * @param event the logger event
     */
    void loggerEvent(final LoggerEvent event);
    
}
