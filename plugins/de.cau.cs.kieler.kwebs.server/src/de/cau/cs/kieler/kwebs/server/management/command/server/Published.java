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

package de.cau.cs.kieler.kwebs.server.management.command.server;

import de.cau.cs.kieler.kwebs.server.publishing.ServicePublisher;

/**
 *
 * @author swe
 * 
 */
public final class Published extends AbstractManagementCommand {

    /**
     * {@inheritDoc}
     */
    public void execute() throws Exception {
        getExchange().setResponse(
            "The configured services are currently"
            + (!ServicePublisher.INSTANCE.isPublished() ? " not" : "")
            + " published."
                    
            );
        }
        
}
