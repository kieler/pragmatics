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
 * This class implements a web content provider for displaying the service meta data in HTML format.
 *
 * @author  swe
 */
class IndexProvider 
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
		return 
			'''
			<p class='title'>Welcome ...</p>
			<p>
				... to the web frontend of the layout server hosted by:
			</p>
			<p>
				<div align='center'>
					«
						config.getConfigProperty(Configuration::FRONTEND_SERVICE_HOSTER)
					»
				</div>
			</p>
			<p>
				On this page you may inform yourself about the possibilities the server gives you for getting a layout for your graphs.
				Furthermore you can inform yourself about how to consume its services. We hope that the layout we provide will be an useful 
				enhancement for your project.
				
				Have fun exploring the service!
			</p>
			«if (config.hasConfigProperty(Configuration::FRONTEND_SERVICE_HOSTER_LOGO))
				// src has to be declared relative to the classpath
				'''
				<p>
					<div align='center'>
						<img src='«config.getConfigProperty(Configuration::FRONTEND_SERVICE_HOSTER_LOGO)»'/> 
					</div>
				</p>
				'''
			»
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
