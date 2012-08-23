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
//<textarea id='interface' cols='75' rows='50' wrap='off' readonly='readonly'></textarea>
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
        RequestData requestData
    )
    {
        return
            '''
            <script type='text/javascript' src='scripts/ajax.js'></script>
            <script type='text/javascript' src='scripts/highlight.js'></script>
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
                code, pre {
                    margin           : 0;
                    padding          : 0;
                    border           : 0;
                    font-size        : 8pt;
                    vertical-align   : baseline;
                    background       : transparent;
                    font-family      : Consolas, Menlo, Monaco, Lucida Console, Liberation Mono, DejaVu Sans Mono, Bitstream Vera Sans Mono, Courier New, monospace, serif;
                    background-color : #eeeeee;
                    text-align:left;
                }
                pre {
                    width              : 650px;
                    max-height         : 600px;
                    padding            : 5px;
                    padding-bottom     : 20px;
                    margin-bottom      : 40px;
                    overflow           : auto;
                    -moz-border-radius : 5px;
                    border-radius      : 5px;
                    -moz-box-shadow    : 5px 5px grey;
                    -webkit-box-shadow : 5px 5px grey;
                    box-shadow         : 5px 5px grey;
                }
                .small {
                	font-size 	    : 8pt;
                    font-family     : Consolas, Menlo, Monaco, Lucida Console, Liberation Mono, DejaVu Sans Mono, Bitstream Vera Sans Mono, Courier New, monospace, serif;
                	text-decoration : none;
                }
            </style>
            '''
    }

    /**  */
    private static String WSDL_SUFFIX
        = "?WSDL"

    /**
     *
     */
    //ToDo: url via configuration since it can not be automatically detected
    override CharSequence getBody(
        RequestData requestData
    )
    {
        val Configuration config = Configuration::INSTANCE
        val String        url    = config.getConfigProperty(Configuration::JAXWS_HTTP_PUBLIC_ADDRESS) + WSDL_SUFFIX
        return
            '''
            <p class='title'>
                Service Interface
            </p>
            <p>
                As KWebS uses a SOAP web service, you need the interface definition in order to use its features. The server provides it
                in form of a document based on the web service description language (WSDL). The interface definition is the same either
                for HTTP or for HTTPS based access and you can use the tools provided by the web service framework you want to use to
                generate the necessary implementation skeletons. You can generate the client skeleton code from the service interface
                definition that is located <a href='«url»'>here</a>.
            </p>
            <p>
                The following listing gives you a first impression of the functionality KWebS provides:
                <br/>
                <br/>
                <div align='center'>
                    <pre>
                        <code id='interface'>
                        </code>
                    </pre>
                </div>
            </p>
            <script type='text/javascript'>
                try {
                    Ajax_Call(
                        AJAX_OPT_CREATEREQUEST, null, AJAX_GET, "«url»", null, AJAX_ASYNC,
                        function(r){
                            var elem = document.getElementById('interface');
                            if (elem != null) {
                                ««« This fixes the problem that JAX-WS generates a comment in the WSDL that
                                ««« is not correctly indended
                                var lines   = hljs.highlight("xml", r.responseText.replace(/<!-- Published by JAX-WS RI.*-->/, "\n\n")).value.split("\n");
                                var content = "";
                                var leads   = (lines.length + "").length;
                                var pad     = " ";
                                while (pad.length < leads) {
                                    pad += " ";
                                }
                                for (var i = 0; i < lines.length; i++) {
                                    content += //"<span style='margin:0px;padding:0px;background-color:"
                                             //+ (i % 2 == 0 ? "#fafafa" : "#c0ffc0" )
                                             /*+ ";'>" +*/ (pad + i).slice(-leads) + ".    " + lines[i]/* + "</span>*/ + "\n";
                                }
                                elem.innerHTML = content;
                            } else {
                                throw "DOM element for displaying the interface definition not found.";
                            }
                        }
                    );
                } catch (e) {
                    alert("An error occured while trying to display the interface definition:\n\n" + e);
                }
            </script>
            <p>
                <div class='small' align='center'>
                	Thanks to the guys from software maniacs for their great syntax highlighting that can be found <a class='small' href='http://softwaremaniacs.org/soft/highlight/en/'>here</a>.
                </div>
            </p>
            '''
    }

    /**
     *
     */
    override boolean providerOverride(
        RequestData requestData
    )
    {
        return false
    }

}
