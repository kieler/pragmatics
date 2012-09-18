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
 
package de.cau.cs.kieler.kwebs.server.web.delegates

import de.cau.cs.kieler.kwebs.server.web.IAjaxDomainBridgeDelegate
import de.cau.cs.kieler.kwebs.server.web.ResourceProcessingExchange
import de.cau.cs.kieler.kwebs.server.web.WebContentHandler
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.util.Map

/**
 * Delegate to deliver content via AJAX that is not hostet under the domain of the web
 * frontend.   
 *
 * @author swe
 */
class DefaultAjaxDelegate
    implements IAjaxDomainBridgeDelegate
{
	
	//////////
	
	/** */
	private static String PARAMETER_URL
		= "url"
		
	/** */
	private static String PROTOCOL_HTTP
		= "http"
		
	/** */
	private static String MIMETYPE_HTML
		= "text/html"
		
	//////////
	
	/**
	 * {@inheritDoc}
	 */
	override String getMimetype(
		ResourceProcessingExchange processingExchange
	) 
	{
		return WebContentHandler::guessMimeType(
			processingExchange.getResource(),
			MIMETYPE_HTML
		)
	}
	
	/**
	 * {@inheritDoc}
	 */
	override URLConnection getConnection(
		ResourceProcessingExchange processingExchange
	) 
	{
    	val Map<String, String> params   = processingExchange.getParams()
		val String              resource = params.get(PARAMETER_URL)
		val URL                 url      = new URL(resource)
		if (!url.getProtocol().equals(PROTOCOL_HTTP)) {
    		processingExchange.setResultCode(HttpURLConnection::HTTP_BAD_REQUEST)
    		return null
		}
		return url.openConnection()
	}
	
}
