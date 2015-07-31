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

package de.cau.cs.kieler.kwebs.server.management.command.client;

import java.io.Serializable;

/**
 *
 * @author  swe
 * 
 */
public interface IManagementExchange extends Serializable {
    
    /**
     * 
     * @param name
     * @param <T>
     * @return
     */
    <T extends Serializable> T getParameter(final String name);
    
    /**
     * 
     * @param name
     * @param value
     * @param <T>
     */
    <T extends Serializable> void setParameter(final String name, final T value);
    
    /**
     * 
     * @param command
     */
    void setCommand(final String command);
    
    /**
     * 
     * @return
     */
    String getCommand();
    
    /**
     * @param response
     * @return
     */
    void setResponse(final Serializable response);
    
    /**
     * 
     * @return
     */
    Serializable getResponse();

}
