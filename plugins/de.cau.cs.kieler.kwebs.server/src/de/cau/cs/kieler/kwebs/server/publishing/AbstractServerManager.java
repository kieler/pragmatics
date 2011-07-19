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

package de.cau.cs.kieler.kwebs.server.publishing;

import java.util.Properties;

/**
 * Abstract base implementation of the interface {@link IServerManager}.
 *
 * @author swe
 *
 */
abstract class AbstractServerManager implements IServerManager {

    // The class {@code AbstractServerManager} and its descendants are package private
    // so no need for getters and setters here.

    //CHECKSTYLEOFF VisibilityModifier

    /** The shared service properties. */
    protected Properties properties;

    //CHECKSTYLEON VisibilityModifier

    /**
     * {@inheritDoc}
     */
    public void setProperties(final Properties theproperties) {
        properties = theproperties;
    }

}
