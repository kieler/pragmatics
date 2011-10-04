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

package de.cau.cs.kieler.kwebs.server.publishing;

import java.net.URI;

import de.cau.cs.kieler.kwebs.server.configuration.Configuration;

/**
 * Abstract base implementation of the interface {@link IServerManager}.
 *
 * @author swe
 *
 */
abstract class AbstractServerManager implements IServerManager {

    //CHECKSTYLEOFF VisibilityModifier
    
    /** The server wide configuration instance. */
    protected Configuration config
        = Configuration.getInstance();

    /** The address this manager exposes the serviced object on. */
    protected URI address;
    
    //CHECKSTYLEON VisibilityModifier
    
    /**
     * {@inheritDoc}
     */
    public void setAddress(final URI serviceAddress) {
        address = serviceAddress;
    }
    
}
