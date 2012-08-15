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

import com.sun.net.httpserver.Headers
import de.cau.cs.kieler.kwebs.server.configuration.Configuration
import de.cau.cs.kieler.kwebs.server.logging.Logger
import de.cau.cs.kieler.kwebs.util.Resources
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Array
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Enumeration
import java.util.Map
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import org.eclipse.core.runtime.Platform
import org.eclipse.xtext.xbase.lib.Pair
import org.osgi.framework.Bundle

/**
 * This class implements a web content provider for displaying the service meta data in HTML format.
 *
 * @author  swe
 */
class DownloadsProvider 
	extends AbstractProvider
{
	
	/**
	 * 
	 */
	override CharSequence getHeaders(
		RequestData requestData
	) 
	{
		return ''''''
	}
    
    /**  */
    private static String TRUSTSTORE_SUFFIX
    	= "security/client.jks"
    	
    /**
     * 
     */
	override CharSequence getBody(
		RequestData requestData
	) 
	{
		val Configuration config = Configuration::INSTANCE
		val String        urlTS  = config.getConfigProperty(Configuration::SUPPORTINGSERVER_ADDRESS) + "/" + TRUSTSTORE_SUFFIX
		return 
			'''
			<p class='title'>
				Downloads
			</p>
			<p>
				On this page you can select between some useful downloads the service provides.
			</p>
			«if (config.getConfigPropertyAsBoolean(Configuration::FRONTEND_HTTPS_EXPOSE_TRUSTSTORE, false)) {
				'''
				<p class='title'>Secure Access</p>
				<p>
					In order to use the layout service secured by HTTPS you need a trust store that you can download <a href='«urlTS»'>
					here</a>. Furthermore you will need the password to access the key that is stored in it and that is necessary
					for opening a secure connection. The policy for getting this password dependens on your service hoster.
				</p>
				<p>
					The following illustration demonstrates how you can configure a HTTPS based service in the KIELER modeling
					environment. After you downloaded the trust store to say <i>C:\kwebs\</i>, you create a server configuration which
					points to the appropriate service endpoint, in this case <i>https://layout.rtsys.informatik.uni-kiel.de:9443/layout</i>.
					Then you configure the trust store and enter the password that was given to you by your hoster.					
					<div align='center'>
						<img src='images/ts_conf.png'/>
					</div>
				</p>
				'''	
			}»
			<p class='title'>Layout on the command line</p>
			<p>
				There is also a java console client that can do layout and translations for you. You can download it from <a href='/Downloads.html?feature=ccl'>
				here</a>.
			</p>
			'''
	}
    
    /** Constant for query parameter 'algorithm'. */
    private static String PARAM_FEATURE_DOWNLOAD = "feature"
    
    private static Map<String, String> VALID_FEATURES = <String, String>newHashMap(
    	new Pair<String, String>("ccl", "de.cau.cs.kieler.kwebs.tools")
    )
    
    /**
     * 
     */
	override boolean providerOverride(
		RequestData requestData
	) 
	{
        val Map<String, String> params = requestData.params
		if (params.containsKey(PARAM_FEATURE_DOWNLOAD)) {
			if (!VALID_FEATURES.containsKey(params.get(PARAM_FEATURE_DOWNLOAD))) {
				Logger::log(
					"Invalid feature request: " + requestData.exchange.requestURI
				)
				throw new IllegalArgumentException(
					"Invalid feature request: " + requestData.exchange.requestURI
				)
			}
            doFeatureDownload(requestData)
            return true
        } 
        return false
	}
	
	private SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MM yyyy HH:mm:ss")
	
	def private void doFeatureDownload(
		RequestData requestData
	) 
	{		
        val Map<String, String> params  = requestData.params
        val String              feature = VALID_FEATURES.get(params.get(PARAM_FEATURE_DOWNLOAD)) 
    	try {
			val byte[] data = pluginAsByteArray(
				feature, "/", "*", true
			)
			val String  now     = formatter.format(new Date())
			val Headers headers = (requestData as CacheData).additionalHeaders
            headers.add("Expires:",       										   now + " GMT")
            headers.add("Last-Modified:", 										   now + " GMT")
            headers.add("Cache-Control:", 				  "no-store, no-cache, must-revalidate")
            headers.add("Cache-Control:", 							"post-check=0, pre-check=0")
            headers.add("Pragma:",        											 "no-cache")
            requestData.setName(feature + "_" + Resources::getPluginVersion(feature) + ".jar\"")
            requestData.setContent(data)
            requestData.setMimetype("application/octet-stream")
		} catch (Exception e) {
			Logger::log(
				"Could not create response for requested feature: " + feature
			)
			e.printStackTrace
		}
	}
	
    def private byte[] pluginAsByteArray(
		String  pluginId, 
		String  root,
		String  filter, 
		boolean recurse
    ) 
    throws IOException
    {
    	val Bundle 				  bundle  = Platform::getBundle(pluginId)
    	if (bundle == null) {
    		throw new IOException("No such bundle: " + pluginId)
    	}
    	val ByteArrayOutputStream bytes   = new ByteArrayOutputStream()
    	val ZipOutputStream  	  jar     = new ZipOutputStream(bytes)
    	jar.setLevel(9)
    	val Enumeration<URL>      entries = bundle.findEntries(root, filter, recurse)
    	val byte[]                buffer  = Array::newInstance(typeof(byte), 4096) as byte[]
    	var int                   read    = -1
    	while (entries.hasMoreElements()) {
    		val URL    		  entry = entries.nextElement()
    		var String 		  path  = entry.getPath()
    		if (!path.endsWith("/")) {
    			if (path.startsWith("/")) {
    				path = path.substring(1)
    			}
	    		try {
		    		val InputStream   in    = Resources::getResourceStream(pluginId, path)
		    		jar.putNextEntry(new ZipEntry(path))
		    		while ((read = in.read(buffer)) > 0) {
		    			jar.write(buffer, 0, read)
		    		}
		    		jar.closeEntry()
		    	} catch (Exception e) {
					Logger::log(
						Logger::severity_WARNING, "Could not add entry: " + entry, e
					)
				}
    		}
    	}
    	jar.flush()
    	jar.close()
    	return bytes.toByteArray();
    }
    
}
