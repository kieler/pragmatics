/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.cau.cs.kieler.kwebs.server.layout.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.server.service.LiveLayoutService;

/**
 * Handles layout requests at the GET /live/ domain. Expects the graph and configuration options as
 * parameters of the query string ('graph' and 'config'). The config parameter should be JSON with
 * available layout options as key value pairs.
 * 
 * @author uru
 * 
 * @deprecated replaced by {@link HTTPLayoutHandler}
 */
public class LiveLayoutHandler implements HttpHandler {

    private static final int HTTP_OK = 200;
    private static final int HTTP_ERROR = 500; // internal server error

    /**
     * {@inheritDoc}
     */
    public void handle(final HttpExchange http) throws IOException {

        if (http.getRequestMethod().toUpperCase().equals("OPTIONS")) {
            // CORS preflight
            handleOptionsRequest(http);
        } else if (http.getRequestMethod().toUpperCase().equals("POST")
                || http.getRequestMethod().toUpperCase().equals("GET")) {
            // layout request
            handleLayoutRequest(http);
        } else {
            sendError(http, "Unsupported request method: " + http.getRequestMethod(), null);
        }
    }

    /**
     * Perform layout on the passed data.
     */
    private void handleLayoutRequest(final HttpExchange http) throws IOException {

        String decoded = "";

        try {
            if (http.getRequestMethod().toUpperCase().equals("GET")) {
                // for get the parameters are in the url
                decoded = URLDecoder.decode(http.getRequestURI().getQuery(), "UTF-8");
            } else if (http.getRequestMethod().toUpperCase().equals("POST")) {
                // port post we have to look into the body
                String input = CharStreams.toString(new InputStreamReader(http.getRequestBody()));
                decoded = URLDecoder.decode(input, "UTF-8");
            } else {
                sendError(http, "Could not handle method " + http.getRequestMethod() + ".", null);
                return;
            }
        } catch (IOException e) {
            sendError(http, "Could not decode the passed data.", e);
            return;
        }

        Map<String, String> params = getParams(decoded);

        // the graph
        String graph = params.get("graph");

        // formats
        String informat = params.get("iFormat");
        String outformat = params.get("oFormat");

        // config
        String config = params.get("config");
        List<GraphLayoutOption> opts = Lists.newLinkedList();

        try {
            JSONObject obj = new JSONObject(config);
            if (obj.length() > 0) {
                for (String key : JSONObject.getNames(obj)) {
                    opts.add(new GraphLayoutOption(key, obj.getString(key)));
                }
            }
        } catch (JSONException e) {
            // e.printStackTrace();
            sendError(http, "The passed configuration data is invalid. It has to be a "
                    + "comma-seperated list of valid layout options, see "
                    + "<a href='ProvidedLayout.html'>here</a> for further information on "
                    + "these options.", e);
            return;
        }

        // perform the layout
        String outGraph = "";
        try {
            outGraph = new LiveLayoutService().doLayout(graph, informat, outformat, opts);
        } catch (Throwable t) {
            // t.printStackTrace();
            sendError(http, "Could not process the input graph, make sure that the "
                    + "correct input format is selected and the input itself is well-formed.", t);
            return;
        }

        // fixes for an svg
        if (outformat.equals("org.w3.svg")) {
            outGraph = fixSvg(outGraph);
        }

        http.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
             //   http.getRequestHeaders().getFirst("origin"));
        http.getResponseHeaders().add("content-type", "text/plain");

        // send the result graph
        http.sendResponseHeaders(HTTP_OK, outGraph.length());
        OutputStream os = http.getResponseBody();
        os.write(outGraph.getBytes());
        os.close();
    }

    /**
     * Handling a CORS preflight package. Allowing cross-site GET requests.
     */
    private void handleOptionsRequest(final HttpExchange http) throws IOException {

        // allow the same origin
        http.getResponseHeaders().add("Access-Control-Allow-Origin",
                http.getRequestHeaders().getFirst("origin"));
        // only allow GET and OPTIONS (comma separated list!)
        http.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
        // just echo
        http.getResponseHeaders().add("Access-Control-Allow-Headers",
                http.getRequestHeaders().getFirst("Access-Control-Request-Headers"));
        // package no older than 10 seconds
        http.getResponseHeaders().add("access-control-max-age", "10");
        // OK
        http.sendResponseHeaders(HTTP_OK, -1);
        http.getResponseBody().close();
    }

    private void sendError(final HttpExchange http, final String text, final Throwable t)
            throws IOException {
        String error = "";
        try {
            JSONObject obj = new JSONObject();
            obj.put("message", text);
            if (t != null) {
                obj.put("throwable", t.getMessage());
            }
            error = obj.toString(2);
        } catch (JSONException ex) {
            ex.printStackTrace();
            error = ex.getMessage();
        }

        // send the response
        http.sendResponseHeaders(HTTP_ERROR, error.getBytes().length);
        OutputStream os = http.getResponseBody();
        os.write(error.getBytes());
        os.close();
    }

    private Map<String, String> getParams(final String query) {
        Map<String, String> params = Maps.newHashMap();

        for (String param : Splitter.on("&").split(query)) {
            String[] kv = param.split("=", 2);
            params.put(kv[0], kv[1]);
        }

        return params;
    }

    /**
     * The used jquery scripts for dragging and zooming require that the id of the outermost g
     * element is 'group'.
     */
    private String fixSvg(final String graph) {

        String res3 = graph.substring(graph.indexOf("<svg") - 1, graph.length());
        StringBuffer sb = new StringBuffer(res3);
        sb.insert(sb.indexOf("<g") + 2, " id=\"group\"");

        return sb.toString();
    }
}
