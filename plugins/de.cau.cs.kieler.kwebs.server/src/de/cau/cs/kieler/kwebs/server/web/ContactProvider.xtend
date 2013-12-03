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

package de.cau.cs.kieler.kwebs.server.web

import de.cau.cs.kieler.kwebs.server.configuration.Configuration

/**
 * This class implements a web content provider for displaying contact information.
 *
 * @author swe
 */
class ContactProvider 
    extends AbstractProvider
{
    
    /**
     * 
     */
    override CharSequence getHeaders(
        ResourceProcessingExchange processingExchange
    ) 
    {
        return ''''''
    }
    
    /**
     * 
     */
    override CharSequence getBody(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val Configuration config = Configuration::INSTANCE
        '''
        <div class="col-md-8 col-md-offset-2">
        <h2>Contact</h2>
        «if (config.hasConfigProperty(Configuration::CONTACT_INFORMATION)) {
            val String provider = config.getConfigProperty(Configuration::CONTACT_INFORMATION).escapeHtml                 
            '''
            <p>
                <b>This service is provided by:</b>
            </p>
            <p>
                «provider»
            </p>
            '''
        }»
        «if (config.hasConfigProperty(Configuration::CONTACT_EMAIL_ADDRESS)) {
            val String person  = config.getConfigProperty(Configuration::CONTACT_EMAIL_PERSON).escapeHtml
            val String address = config.getConfigProperty(Configuration::CONTACT_EMAIL_ADDRESS).escapeHtml                
            '''
            <p>
                Please feel free to contact <a href='mailto:«address»'>«person»</a> for any questions and suggestions
                regarding the layout web service.
            </p>
            '''
        }»
        </div>
        '''
    }
    
    /**
     * 
     */
    override boolean providerOverride(
        ResourceProcessingExchange processingExchange
    ) 
    {
        return false
    }
    
}
