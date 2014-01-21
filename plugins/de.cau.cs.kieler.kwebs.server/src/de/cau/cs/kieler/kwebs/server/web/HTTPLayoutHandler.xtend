/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.web

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import de.cau.cs.kieler.kwebs.server.service.LiveLayoutService
import java.io.IOException
import java.net.URLDecoder
import org.json.JSONObject
import sun.misc.BASE64Encoder

/**
 * Handles layout requests at the /layout/[*] context. Expects the graph and configuration options as
 * parameters of the query string ('graph' and 'config'). The config parameter should be JSON with
 * available layout options as key value pairs.
 * 
 * 
 * @author uru
 */
class HTTPLayoutHandler implements HttpHandler {
    
    val HTTP_OK = 200
    val HTTP_ERROR = 500 // internal server error
    
    val service = new LiveLayoutService()
    
    private var String cachedServiceData
    
    /**
     * Main entry point for every http request.
     */
    override handle(HttpExchange http) throws IOException {

        val method = http.requestMethod.toUpperCase
        try {
            switch method {
                case 'OPTION':
                    http.handleCORS
                case 'GET':
                    http.handleGetOrPost
                default: 
                    http.handleError("Unsupported request method: " 
                        + http.requestMethod + ".")
            }
        } catch (Exception e) {
            http.handleError("Exception occured during request.", e)
        }
    }

    /**
     * Handling a CORS preflight package. Allowing cross-origin requests.
     */
    def handleCORS(HttpExchange http) {
        http.responseHeaders.add("Access-Control-Allow-Origin", http.requestHeaders.getFirst("origin"))

        // only allow GET, POST and OPTIONS (comma separated list!)
        http.responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
        http.responseHeaders.add("Access-Control-Allow-Headers",
            http.requestHeaders.getFirst("Access-Control-Request-Headers"))

        // package no older than 10 seconds
        http.responseHeaders.add("Access-Control-Max-Age", "10")

        // OK
        http.sendResponseHeaders(HTTP_OK, -1);
        http.responseBody.close();
    }

    def handleGetOrPost(HttpExchange http) {
        println("get or post " + http.requestURI)
        val uri = http.requestURI.toString
        switch uri {
            case '/layout':
                println("live")
            case '/layout/serviceData':
                http.handleServiceData
            case uri.startsWith('/layout/previewImage'):
                http.handlePreviewImage
        }
        
        // for any request allow CORS
        http.responseHeaders.add("Access-Control-Allow-Origin", "*")
    }
    
    /**
     * Answer with the internal service data, specifying supported layout etc.
     */
    def handleServiceData(HttpExchange http) {
        // get possibly cached service data json
        val data = if (cachedServiceData == null) {
            cachedServiceData = serviceDataJson.toString
        } else {
            cachedServiceData
        }
        
        // send the sevice data
        http.responseHeaders.add("Content-Type", "application/json")
        http.sendResponseHeaders(HTTP_OK, data.bytes.length)
        val os = http.responseBody
        os.write(data.bytes)
        os.close()
    }
    
    /**
     * Answer with the preview image located at the specified path.
     */
    def handlePreviewImage(HttpExchange http) {
        // retrieve the image        
        var imgPath = http.requestURI.toString.replace("/layout/previewImage/", "");
        if (imgPath.indexOf("?") > 0) {
            imgPath = imgPath.substring(0, imgPath.indexOf("?"))
        }
        val img = service.getPreviewImage(imgPath)
        if (img == null) {
            http.handleError("Could not find the specified image")
        }

        // should we return base64?
        val params = URLDecoder.decode(http.requestURI.query ?: "", "UTF-8");
        val base64 = params.contains("base64=true")
        
        var byte[] bytes = img;
        if (base64) {
            // encode the bytes to base64
            val encoded = new BASE64Encoder().encode(img);
            bytes = encoded.bytes;
            http.responseHeaders.add("Content-Type", "image/png;base64")
        } else {
            http.responseHeaders.add("Content-Type", "image/png")
        }
        
        // send the preview image
        http.sendResponseHeaders(HTTP_OK, bytes.length)
        val os = http.responseBody
        os.write(bytes)
        os.close()
    }
    
    
    def void handleError(HttpExchange http, String text) {
         http.handleError(text, null)
    }
    
    def void handleError(HttpExchange http, String text, Throwable t) {
       
        val error = '''
          {
            message: «text.quote»,
            throwable: «t?.message?.quote ?: "undefined"»
          }'''

        // send the response
        http.responseHeaders.add("Content-Type", "application/json")
        http.sendResponseHeaders(HTTP_ERROR, error.getBytes().length);
        val os = http.getResponseBody();
        os.write(error.getBytes());
        os.close();
    }

    /*---------------------------------------------------------------------------
     *  Internal methods
     */
    
    private def getServiceDataJson() {
        val data = service.serviceData
        '''
        {
          "version": "«data.version»",
          "layoutAlgorithms": [
              «
               data.layoutAlgorithms.map [ a |
                   '''
                   {
                     "id": «a.id.quote»,
                     "name": «a.name.quote»,
                     "description": «a.description.quote»,
                     "version": «a.version.quote»,
                     "category": «a.category?.id?.quote»,
                     "type": "«a.type?.id»",
                     "previewImagePath": «a.previewImagePath.quote»,
                     "knownOptions": [
                        «
                        a.knownOptions.map [ opt |
                            '''
                            {
                              "id": «opt.option.id.quote»,
                              "default": «opt.^default.quote»
                            }'''
                        ].join(",\n")
                        »
                     ]
                   }'''
               ].join(",\n")   
              »
          ],
          "categories": [
              «
              data.categories.map [ c |
                  '''
                  {
                    "id": «c.id.quote»,
                    "name": «c.name.quote»
                  }'''
              ].join(",\n")
              »
          ],
          "layoutOptions": [
              «
              data.layoutOptions.map[ lo | 
                  '''
                  {
                    "id": «lo.id.quote»,
                    "type": «lo.type.quote»,
                    "name": «lo.name.quote»,
                    "description": «lo.description.quote»,
                    "appliesTo": «lo.appliesTo.quote»,
                    "default": «lo.^default.quote»,
                    "advanced": «lo.advanced»,
                    "implementation": «lo.implementation.quote»
                  }'''                
              ].join(",\n")
              »
          ],
          "supportedFormats": [
              «
              data.supportedFormats.map [ f |
                  '''
                  {
                    "id": «f.id.quote»,
                    "description": «f.description.quote»,
                    "name": «f.name.quote»
                  }'''
              ].join(",\n")
              »
          ]
        }
        '''
    }
    
    /**
     * JSON Strings must not contain " or / or controls characters.
     * This method surrounds the passed string with quotes while 
     * escaping all characters that are not allowed.
     */
    private def quote(String s) {
        JSONObject.quote(s)
    }

}