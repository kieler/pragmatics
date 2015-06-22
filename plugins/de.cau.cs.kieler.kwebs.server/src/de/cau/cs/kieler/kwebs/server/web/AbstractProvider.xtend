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
                        <link href="styles/bootstrap-3.0.2.min.css" rel="stylesheet">
                        <link href="styles/prettify.css" type="text/css" rel="stylesheet">
                        <link href="styles/style.css" type="text/css" rel="stylesheet">
                        <script src="scripts/jquery-1.10.2.min.js"></script>
                        <script src="scripts/bootstrap-3.0.2.min.js"></script>
                        <script src="scripts/prettify.js"></script>
                        <script src="scripts/jquery.stickytableheaders.min.js"></script>
                        «getHeaders(processingExchange)»
                        <style>
                            body {
                                padding-top: 70px;
                            } 
                            pre.prettyprint {
                                padding: 9px;
                            }
                        </style>
                        <script>
                          $(function() {
                              // remove leading whitespace from each code element 
                              $('.prettyprint').each(function() {
                               var code = $(this);
                               var text = code.html();
                               if (text) {
                                  var lines = text.split("\n");
                                  if (lines.length > 0) {
                                      var match = /^\s+(.+)$/.exec(lines[0]);
                                      if (match && match.length > 1) {
                                        var length = match[0].length - match[1].length;         
                                        var strippedLines = $.map(lines, function(e, i) {
                                            var woWhitespaces = e.substring(length);
                                            return woWhitespaces;
                                        });
                                      }
                                      // replace with the whitespace free code
                                      code.html(strippedLines.join("\n"));
                                  }
                               }
                              });
                              
                              prettyPrint();
                          });
                        </script>
                    </head>
                    <body>

                        <header class="navbar navbar-inverse navbar-fixed-top">
                          <div class="container">
                              <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                                  <span class="sr-only">Toggle navigation</span>
                                  <span class="icon-bar"></span>
                                  <span class="icon-bar"></span>
                                  <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="Index.html">KIELER</a>
                              </div>
                            <nav class="nav navbar-nav" role="navigation">
                              <div class="collapse navbar-collapse" id="navbar-collapse">
                                <ul class="nav navbar-nav">
                                  <li><a href='Providedlayout.html'>Provided Layout</a></li>
                                  <li><a href='Serviceinterface.html'>Service Interface</a></li>
                                  <li><a href='Downloads.html'>Downloads</a></li>
                                  <li><a href='Contact.html'>Contact</a></li>
                                  <li><a href='Live.html'>Live</a></li>
                                </ul>
                              </div>
                            </nav>
                          </div>
                        </header>
                                            
                        «IF requireContainer()»
                        <div class="container-fluid">
                          <div class="row">
                            <div class="col-md-8 col-md-offset-2">
                        «ENDIF»
                        «getBody(processingExchange)»
                        «IF requireContainer()»
                            </div>
                          </div>
                        </div>
                        «ENDIF»
                        
                        <script>
                            var offset = $('.navbar').height();
                            $("html:not(.legacy) table").stickyTableHeaders({fixedOffset: offset});
                        </script>
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
    
    def boolean requireContainer() {
      return true
    }
    
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
