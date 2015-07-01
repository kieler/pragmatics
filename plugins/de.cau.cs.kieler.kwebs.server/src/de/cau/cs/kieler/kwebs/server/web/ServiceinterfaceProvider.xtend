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
package de.cau.cs.kieler.kwebs.server.web

import de.cau.cs.kieler.kwebs.server.configuration.Configuration

/**
 * This class implements a web content provider for displaying the service meta data in HTML format.
 *
 * @author swe
 */
class ServiceinterfaceProvider
    extends AbstractProvider
{

    /**
     *
     */
    override CharSequence getHeaders(
        ResourceProcessingExchange processingExchange
    )
    {
        '''
        <script type='text/javascript' src='scripts/ajax.js'></script>
        <script type='text/javascript'>
            function escape(xml) {
                return xml
                    .replace(/&/g,  "&amp;")
                    .replace(/</g,   "&lt;")
                    .replace(/>/g,   "&gt;")
                    .replace(/"/g, "&quot;");
            }
        </script>
        <link rel="stylesheet" href="scripts/styles/gc.css">
        <style type='text/css'>
            .small {
                font-size         : 8pt;
                font-family     : Consolas, Menlo, Monaco, Lucida Console, Liberation Mono, DejaVu Sans Mono, Bitstream Vera Sans Mono, Courier New, monospace, serif;
                text-decoration : none;
            }
        </style>
        '''
    }

    /**  */
    private static String WSDL_SUFFIX
        = "?WSDL"

    /**  */
    private static String WSDL_ADAPTER_URL
        = "AjaxDomainBridge.html?handler=WsdlDelegate"

    /**
     *
     */
    override CharSequence getBody(
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
        val String        url           = 
            if (publishHTTP)
                config.getConfigProperty(Configuration::JAXWS_HTTP_ADDRESS) + WSDL_SUFFIX
            else if (publishHTTPS)
                config.getConfigProperty(Configuration::JAXWS_HTTPS_ADDRESS) + WSDL_SUFFIX
            else
                null
                
        '''
        <h2>
            Service Interfaces
        </h2>
        <p>
            We provide a HTTP-based and a SOAP-based interface. The SOAP interface is explained in further detail
            below. For the HTTP interface have a look at our 
            <a href="http://rtsys.informatik.uni-kiel.de/confluence/x/FgKE">Wiki</a>.
        </p>
        
        <h3>SOAP</h3>
        <p>
            Since KWebS uses a SOAP web service, you need the interface definition in order to use its features.
            The server provides it in form of a document based on the Web Service Description Language (WSDL).
            The interface definition is the same either for HTTP or for HTTPS based access.
            We recommend using a web service framework that allows to automatically generate the
            necessary implementation skeletons from the WSDL document. «
                if (url != null)
                    '''
                    You can generate the client skeleton code from the service interface definition
                    that is located <a href='«url»'>here</a>.'''
                else
                    '''
                    At the moment the server is configured not to publish the service either via
                    HTTP or HTTPS so no location is available to download or display the service interface.'''
            »
        </p>«
            if (url != null) {
                '''
                <p>
                    The following listing gives you a first impression of the functionality KWebS provides:
                    <br/>
                    <br/>
                    <div id='interface'>
                    </div>
                </p>
                <script type='text/javascript'>
                    try {
                        if (!Ajax_IsBrowserAjaxCapable()) {
                            alert("Your browser does not support AJAX. The service interface can not be displayed.");
                        } else {
                            Ajax_Call(
                                AJAX_OPT_CREATEREQUEST, null, AJAX_GET, "«WSDL_ADAPTER_URL»", null, AJAX_ASYNC,
                                function(r){
                                    var elem = document.getElementById('interface');
                                    if (elem != null) {
                                        
                                        
                                        ««« This fixes the problem that JAX-WS generates a comment in the WSDL that
                                        ««« is not correctly indended
                                        var lines   = escape(r.responseText.replace(/<!-- Published by JAX-WS RI.*-->/, "\n\n")).split("\n");
                                        var content = "";
                                        var leads   = (lines.length + "").length;
                                        var pad     = " ";
                                        while (pad.length < leads) {
                                            pad += " ";
                                        }
                                        for (var i = 0; i < lines.length; i++) {
                                            content +=  lines[i] + "\n"; «««(pad + i).slice(-leads) + ".    " +
                                        }
                                        elem.innerHTML = "<pre class=\"prettyprint linenums lang-xml\">" + content + "</pre>";
                                        prettyPrint();
                                    } else {
                                        throw "DOM element for displaying the interface definition not found.";
                                    }
                                }
                            );
                        }
                    } catch (e) {
                        alert("An error occured while trying to display the interface definition:\n\n" + e);
                    }
                </script>
                '''
            }
        »'''
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
