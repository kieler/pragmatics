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

import java.io.UnsupportedEncodingException

/**
 * This class serves as a base implementation for classes providing web frontend components.
 *
 * @author  swe
 */
abstract class AbstractProvider
    implements IDynamicWebContentProvider 
{
    
    /**
     * Handles a HTTP request generating the index page.
     * 
     * @param requestData
     *            the request data holder
     */
    override void handleRequest(
        ResourceProcessingExchange processingExchange
    ) 
    {  
        
        if (providerOverride(processingExchange)) {
            return
        }
        
        try {       
                 
            processingExchange.getResourceInformation().setContent(
                '''
                <!DOCTYPE html>
                <html>
                    <head>
                        <title>KIELER Web Service For Layout</title>
                        <meta http-equiv='content-type' content='text/html; charset=utf-8'>
                        <link rel='stylesheet' type='text/css' href='styles/styles.css'/>
                        «getHeaders(processingExchange)»
                    </head>
                    <body>
                        <div class='layout' align='center'>
                            <table class='document' align='center'>
                                <tr class='header'>
                                    <td>
                                    </td>
                                </tr>
                                <tr class='menu'>
                                    <td>
                                        <table class='menu'>
                                            <tr>
                                                <td class='spacer'>&nbsp;&nbsp;</td>
                                                <td class='button'>
                                                    <a href='Index.html'>About</a>
                                                </td>
                                                <td class='spacer'>&nbsp;|&nbsp;</td>
                                                <td class='button'>
                                                    <a href='Providedlayout.html'>Provided Layout</a>
                                                </td>
                                                <td class='spacer'>&nbsp;|&nbsp;</td>
                                                <td class='button'>
                                                    <a href='Serviceinterface.html'>Service Interface</a>
                                                </td>
                                                <td class='spacer'>&nbsp;|&nbsp;</td>
                                                <td class='button'>
                                                    <a href='Downloads.html'>Downloads</a>
                                                </td>
                                                <td class='spacer'>&nbsp;|&nbsp;</td>
                                                <td class='button'>
                                                    <a href='Contact.html'>Contact</a>
                                                </td>
                                                <td class='spacer'>&nbsp;|&nbsp;</td>
                                                <td class='button'>
                                                    <a href='Live.html'>Live</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr class='content'>
                                    <td class='body'>
                                        <div align='left'>
                                            <p>
                                                «getBody(processingExchange)»
                                            </p>
                                        </div>
                                    </td>
                                </tr>
                                <tr class='empty' height='100%'>
                                    <td>
                                    </td>
                                </tr>
                                <tr class='footer' height='30px'>
                                    <td>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </body>
                </html>
                '''.toString.getBytes("UTF-8")
            );
            
            processingExchange.getResourceInformation().setCharset("UTF-8")
            
        } catch (UnsupportedEncodingException e) {
            // Nothing to do since exception is never thrown due to the fact
            // that UTF-8 is a requirement for every java runtime
        }
        
    }
    
    /**
     * 
     */
    def CharSequence getHeaders(ResourceProcessingExchange processingExchange)
    
    /**
     * 
     */
    def CharSequence getBody(ResourceProcessingExchange processingExchange)
    
    /**
     * 
     */
    def boolean providerOverride(ResourceProcessingExchange processingExchange)
    
    //////////
    
    /**
     * 
     */
    def protected String escapeHtml(String unescaped) {
        if (unescaped == null) {
            return null
        }
        val StringBuilder builder  = new StringBuilder()
        val Integer       length   = unescaped.length
        var Integer       position = 0
        var String        cur // better would be a comparison based on the primitive type 'char'
                              // Currently not possible because the xtend2 generator converts all
                              // char literals (e.g. 'ä') to strings.
        while (position < length) {
            
            cur = unescaped.substring(position, position + 1)
            // HTML special char escapes
            if      (cur.equals( "ä")) builder.append( "&auml;")
            else if (cur.equals( "ö")) builder.append( "&ouml;")
            else if (cur.equals( "ü")) builder.append( "&uuml;")
            else if (cur.equals( "Ä")) builder.append( "&Auml;")
            else if (cur.equals( "Ö")) builder.append( "&Ouml;")
            else if (cur.equals( "Ü")) builder.append( "&Uuml;") 
            else if (cur.equals( "ß")) builder.append("&szlig;") 
            else if (cur.equals( "&")) builder.append(  "&amp;") 
            // other escapes
            else if (cur.equals("\n")) builder.append(  "<br/>")
            else 
                builder.append(cur)

            position = position + 1

        }
        return builder.toString()    
    }    
    
}
