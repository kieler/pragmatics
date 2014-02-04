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
package de.cau.cs.kieler.kwebs.server.web

import de.cau.cs.kieler.kwebs.server.Application
import de.cau.cs.kieler.kwebs.server.util.Xtend2Util
import de.cau.cs.kieler.kwebs.server.web.delegates.DefaultAjaxDelegate
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URLConnection
import java.util.Map
import org.eclipse.core.runtime.Platform

/**
 * Adapter to deliver resources that are served by different servers than our own via AJAX. This is necessary 
 * since many browsers like Chrome and Opera do not allow AJAX requests over domain boundaries (like defined 
 * in the specification of the JavaScript XMLHttpRequest object).    
 *
 * @author swe
 */
class AjaxdomainbridgeProvider
    extends AbstractProvider
{
    
    /** */
    private IAjaxDomainBridgeDelegate defaultDelegate
        = new DefaultAjaxDelegate()
        
    //////////
    
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
        return ''''''
    }
    
    /** */
    private static String PARAMETER_HANDLER
        = "handler"
        
    /** */
    private static int BUFFER_SIZE
        = 4096
        
    /**
     *
     */
    override boolean providerOverride(
        ResourceProcessingExchange processingExchange
    )
    {
        var IAjaxDomainBridgeDelegate delegate = defaultDelegate
        val Map<String, String>       params   = processingExchange.getParams()
        try {
            if (params.containsKey(PARAMETER_HANDLER)) {
                delegate = createDelegate(processingExchange)
            } 
            val URLConnection         con    = delegate.getConnection(processingExchange)
            if (con == null) {
                return true
            }
            val BufferedInputStream   in     = new BufferedInputStream(con.getInputStream())
            val ByteArrayOutputStream out    = new ByteArrayOutputStream()
            val byte[]                   buffer = Xtend2Util::getByteArray(BUFFER_SIZE)
            var int                   read   = -1
            while ((read = in.read(buffer)) > 0) {
                out.write(buffer, 0, read)
            }
            in.close()
            out.flush()
            processingExchange.getResourceInformation().setContent(out.toByteArray())
            processingExchange.getResourceInformation().setMimetype(
                delegate.getMimetype(processingExchange)
            )
           } catch (IOException e) {
            processingExchange.setResultCode(HttpURLConnection::HTTP_CLIENT_TIMEOUT)
           } catch (Exception e) {
            processingExchange.setResultCode(HttpURLConnection::HTTP_BAD_REQUEST)
           }
           return true
    }

    //////////
    
    /** */
    private static String DELEGATE_BASEPACKAGE
        = "de.cau.cs.kieler.kwebs.server.web.delegates"
    
    /**
     * 
     */    
    def private IAjaxDomainBridgeDelegate createDelegate(
        ResourceProcessingExchange processingExchange
    )
    throws IllegalAccessException, InstantiationException
    {
        val Map<String, String>       params   = processingExchange.getParams()
        val String                    handler  = params.get(PARAMETER_HANDLER)
        val IAjaxDomainBridgeDelegate delegate 
            = 
            Platform::getBundle(Application::PLUGIN_ID).loadClass(
                DELEGATE_BASEPACKAGE + "." + handler
            ).newInstance() 
            as IAjaxDomainBridgeDelegate
        return delegate
    }
    
}
