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

package de.cau.cs.kieler.kwebs.server.web;

/**
 * Interface for dynamic web content providers. 
 *
 * @author  swe
 */
public interface IDynamicWebContentProvider {
    
    /**
     * This method is used by the dispatch handler to invoke a dynamic web content handler.
     * 
     * @param processingExchange
     *            the exchange DTO that holds information about the request itself and the
     *            {@link ResourceInformation} instance to deliver the result of processing the request
     */
    void handleRequest(final ResourceProcessingExchange processingExchange);
    
}
