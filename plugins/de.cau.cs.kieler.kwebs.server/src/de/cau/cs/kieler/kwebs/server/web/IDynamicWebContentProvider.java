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

package de.cau.cs.kieler.kwebs.server.web;

/**
 * Interface for dynamic web content providers. 
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public interface IDynamicWebContentProvider {
    
    /**
     * This method is used by the dispatch handler to invoke a dynamic web content handler.
     * 
     * @param requestData
     *            the request data
     * @throws Exception
     *            when an error occurs during execution
     */
    void handleRequest(final RequestData requestData) throws Exception;
}
