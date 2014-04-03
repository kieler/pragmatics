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

/**
 *
 * @author swe
 * 
 */
public final class Alive extends AbstractManagementCommand {

    /**
     * {@inheritDoc}
     */
    public void execute() throws Exception {
        getExchange().setResponse("The KWebS server is currently running.");
    }

}
