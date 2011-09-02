/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
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
 * This class registers as {@link ILoggerListener} and provides logging to screen.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class DisplayLogging implements ILoggerListener {

    /**
     * {@inheritDoc}
     */
    public void loggerEvent(final LoggerEvent event) {
        System.out.println(event.toString().replaceAll("\n", " "));
    }

}
