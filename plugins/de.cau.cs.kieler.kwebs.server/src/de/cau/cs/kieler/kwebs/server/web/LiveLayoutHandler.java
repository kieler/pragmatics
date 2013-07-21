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
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.server.service.LiveLayoutService;

/**
 * Handles layout requests at the GET /live/ domain. Expects the graph and configuration options as
 * parameters of the query string ('graph' and 'config'). The config parameter should be JSON with
 * available layout options as key value pairs.
 * 
 * @author uru
 */
public class LiveLayoutHandler implements HttpHandler {

    private static final int HTTP_OK = 200;

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final HttpExchange http) throws IOException {

        // retrieve the query parameters
        String decoded = URLDecoder.decode(http.getRequestURI().getQuery(), "UTF-8");
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
            for (String key : JSONObject.getNames(obj)) {
                opts.add(new GraphLayoutOption(key, obj.getString(key)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // perform the layout
        String outGraph = new LiveLayoutService().doLayout(graph, informat, outformat, opts);

        // fixes for an svg
        if (outformat.equals("org.w3.svg")) {
            outGraph = fixSvg(outGraph);
        } else {
            outGraph = "<pre class='pre-scrollable'>" + fixXML(outGraph) + "</pre>";
        }

        // send the result graph
        http.sendResponseHeaders(HTTP_OK, outGraph.length());
        OutputStream os = http.getResponseBody();
        os.write(outGraph.getBytes());
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
        String res4 = res3.replaceFirst("width=", "w=");
        String res5 = res4.replaceFirst("height=", "w=");
        StringBuffer sb = new StringBuffer(res5);
        sb.insert(sb.indexOf("<g") + 2, " id=\"group\"");

        return sb.toString();
    }

    private String fixXML(final String graph) {
        return graph.replace(">", "&gt;").replace("<", "&lt;");
    }
}
