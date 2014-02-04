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

import de.cau.cs.kieler.kwebs.server.configuration.Configuration
import de.cau.cs.kieler.kwebs.server.security.ServerSSLContextFactory
import de.cau.cs.kieler.kwebs.server.web.IAjaxDomainBridgeDelegate
import de.cau.cs.kieler.kwebs.server.web.ResourceProcessingExchange
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

/**
 * Delegate to deliver service interface via AJAX.   
 *
 * @author swe
 */
class WsdlDelegate
    implements IAjaxDomainBridgeDelegate
{
	
	//////////
	
	/** */
	private static String PROTOCOL_HTTPS
		= "https"
		
	/** */
	private static String WSDL_SUFFIX
		= "?WSDL"
		
	/** */
	private static String MIMETYPE_XML
		= "application/x-www-form-urlencoded"
		
	//////////
	
	/**
	 * 
	 */
	override String getMimetype(
		ResourceProcessingExchange processingExchange
	) 
	{
		return MIMETYPE_XML;
	}
	
	/**
	 * 
	 */
	override URLConnection getConnection(
		ResourceProcessingExchange processingExchange
	) 
	{
		val Configuration config       = Configuration::INSTANCE
		val boolean       publishHTTP  = config.getConfigPropertyAsBoolean(
			Configuration::JAXWS_PUBLISH_HTTP, false
		) 
		val boolean       publishHTTPS = config.getConfigPropertyAsBoolean(
			Configuration::JAXWS_PUBLISH_HTTPS, false
		)
		val String        url    	   = 
			if (publishHTTP)
				config.getConfigProperty(Configuration::JAXWS_HTTP_ADDRESS) + WSDL_SUFFIX
			else if (publishHTTPS)
				config.getConfigProperty(Configuration::JAXWS_HTTPS_ADDRESS) + WSDL_SUFFIX
			else
				null
		// SOAP service is not published so we can not retrieve the service interface
		if (url == null) {
			 processingExchange.setResultCode(HttpURLConnection::HTTP_UNAVAILABLE)
			 return null;
		}
        val URL 		  wsdlLocation   = new URL(url)
        val URLConnection wsdlConnection = wsdlLocation.openConnection()
        // Configure HTTPS if necessary
        if (wsdlLocation.getProtocol().equalsIgnoreCase(PROTOCOL_HTTPS)) {
        	(wsdlConnection as HttpsURLConnection).setSSLSocketFactory(createSSLSocketFactory)
        }
        return wsdlConnection
	}
	
	//////////
	
	/**
	 * 
	 */
	def private SSLSocketFactory createSSLSocketFactory(
	) 
	throws NoSuchAlgorithmException, KeyStoreException,
           CertificateException, IOException, KeyManagementException 
    {
       
        // Load the servers trust store to open secured connection. The clients certificate is
        // also stored in there. This is a workaround since we can not and must not know the password
        // to the clients key store on server side.
        
        val SSLContext       context = ServerSSLContextFactory::INSTANCE.create()
        val SSLSocketFactory factory = context.getSocketFactory()
        
        ServerSSLContextFactory::INSTANCE.destroy(context)
        
        return factory    
        
    }
    
}
