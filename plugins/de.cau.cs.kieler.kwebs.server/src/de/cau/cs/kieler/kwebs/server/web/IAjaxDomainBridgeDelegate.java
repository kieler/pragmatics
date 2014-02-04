/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.web;

import java.net.URLConnection;

/**
 * Interface for delegating content retrieval when AJAX requests must be 'tunneled' by the
 * server due to security reasons.
 *  
 * @author swe
 *
 */
public interface IAjaxDomainBridgeDelegate {

    /**
     * 
     * @param processingExchange
     * @return
     */
    String getMimetype(final ResourceProcessingExchange processingExchange);
    
    /**
     * 
     * @param processingExchange
     * @return
     */
    URLConnection getConnection(final ResourceProcessingExchange processingExchange);
    
}
