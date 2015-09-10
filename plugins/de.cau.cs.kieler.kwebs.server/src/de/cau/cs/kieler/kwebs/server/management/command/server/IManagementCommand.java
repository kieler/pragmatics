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

package de.cau.cs.kieler.kwebs.server.management.command.server;

import de.cau.cs.kieler.kwebs.server.management.command.client.IManagementExchange;

/**
 *
 * @author swe
 * 
 */
public interface IManagementCommand {

    /**
     * 
     * @param context
     * @throws Exception
     */
    void initialize(final IManagementExchange exchange) throws Exception;
    
    /**
     * 
     * @throws Exception
     */
    void execute() throws Exception;
    
}
