/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.web

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.common.io.CharStreams
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import de.cau.cs.kieler.kwebs.server.layout.GraphLayoutOption
import de.cau.cs.kieler.kwebs.server.service.LiveLayoutService
import java.io.IOException
import java.io.InputStreamReader
import java.net.URLDecoder
import java.util.Map
import org.json.JSONException
import org.json.JSONObject

import sun.misc.BASE64Encoder

/**
 * Handles layout requests at the /layout/[*] context. Expects the graph and configuration options as
 * parameters of the query string ('graph' and 'config'). The config parameter should be JSON with
 * available layout options as key value pairs.
 * 
 * @author uru
 */
class HTTPLayoutHandler implements HttpHandler {
    
    private val HTTP_OK = 200
    private val HTTP_ERROR = 500 // internal server error
    
    /** layout service used to call the underlying layout facilities. */
    private val service = new LiveLayoutService()
    
    /** cache the jsonized service data. */ 
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
                case #['GET', 'POST'].contains(method):
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

    /**
     * Handle the requests we support.
     */
    def handleGetOrPost(HttpExchange http) {
        val uri = http.requestURI.toString
        switch uri {
            case uri.startsWith('/layout/serviceData'):
                http.handleServiceData
            case uri.startsWith('/layout/previewImage'):
                http.handlePreviewImage
            case uri.startsWith('/layout'):
                http.handleLayout
        }
        
        // for any request allow CORS
        http.responseHeaders.add("Access-Control-Allow-Origin", "*")
    }
    
    /**
     * Answer with a layouted graph of the input format.
     */
     def handleLayout(HttpExchange http) {
        
        var decoded = "";
        try {
          decoded = switch http.requestMethod.toUpperCase {
              case 'GET': 
                  URLDecoder.decode(http.requestURI.query, "UTF-8")
              case 'POST': {
                  val input = CharStreams.toString(new InputStreamReader(http.requestBody));
                  URLDecoder.decode(input, "UTF-8");
              }
          }
        } catch (IOException e) {
            http.handleError("Could not decode the passed options data.")
            return
        }
        
        val params = decoded.getParams

        // the graph
        val graph = params.get("graph")

        // formats
        val informat = params.get("iFormat")
        val outformat = params.get("oFormat")

        // config
        val config = params.get("config")
        val opts = Lists.newLinkedList()

        try {
            val obj = new JSONObject(config)
            if (obj.length() > 0) {
                for (String key : JSONObject.getNames(obj)) {
                    opts.add(new GraphLayoutOption(key, obj.getString(key)))
                }
            }
        } catch (JSONException e) {
            // e.printStackTrace();
            http.handleError("The passed configuration data is invalid. It has to be a "
                    + "comma-seperated list of valid layout options, see "
                    + "<a href='ProvidedLayout.html'>here</a> for further information on "
                    + "these options.", e)
            return
        }

        // perform the layout
        var outGraph = ""
        try {
            outGraph = service.doLayout(graph, informat, outformat, opts)
        } catch (Throwable t) {
            http.handleError("Could not process the input graph, make sure that the "
                    + "correct input format is selected and the input itself is well-formed.", t)
            return;
        }

        // fixes for an svg
        if (outformat.equals("org.w3.svg")) {
            outGraph = fixSvg(outGraph)
        }

        // send the result graph
        http.responseHeaders.add("Content-Type", "text/plain")
        http.sendResponseHeaders(HTTP_OK, outGraph.length)
        val os = http.responseBody
        os.write(outGraph.bytes)
        os.close()
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
    
    /**
     * @see #handleError(HttpExchange, String, Throwable)
     */
    def void handleError(HttpExchange http, String text) {
         http.handleError(text, null)
    }
    
    /**
     * Returns an error to the requesting client.
     */
    def void handleError(HttpExchange http, String text, Throwable t) {
       
        val error = '''
          {
            "message": «text.quote»,
            "throwable": «t?.message?.quote ?: "undefined"»
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
    
    /**
     * Transform the internal service data to a json format.
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
    
    /**
     * Retrieves key/value pairs from the passed query string
     */
    private def Map<String, String> getParams(String query) {
        val Map<String, String> params = Maps.newHashMap();

        query.split("&").forEach [ param |
            val kv = param.split("=", 2)
            params.put(kv.get(0), kv.get(1))
        ]

        return params;
    }

    /**
     * FIXME remove this at some point ...
     * 
     * The used jquery scripts for dragging and zooming require that the id of the outermost g
     * element is 'group'.
     */
    private def String fixSvg(String graph) {
        val res3 = graph.substring(graph.indexOf("<svg") - 1, graph.length())
        val res4 = res3.replaceFirst("width=", "w=")
        val res5 = res4.replaceFirst("height=", "w=")
        val sb = new StringBuffer(res5)
        sb.insert(sb.indexOf("<g") + 2, " id=\"group\"")

        return sb.toString()
    }

}