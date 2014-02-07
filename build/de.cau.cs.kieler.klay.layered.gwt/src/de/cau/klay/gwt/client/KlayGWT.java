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
package de.cau.klay.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import de.cau.cs.kieler.kiml.UnsupportedConfigurationException;
import de.cau.klay.gwt.client.layout.RecursiveLGraphLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * Have a look at http://code.google.com/p/gwt-exporter/
 * 
 * @author uru
 */
public class KlayGWT implements EntryPoint {
   
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // export the layout method
        exportKlayLayout();
        
        // offer an init function, after interface methods have been exported
        klayInit();
    }

    private native void klayInit() /*-{
        if ($wnd.klayinit) {
            $wnd.klayinit();
        }
    }-*/;
    
    private native void exportKlayLayout() /*-{
        $wnd.$klay = {};
        $wnd.$klay.layout = $entry(@de.cau.klay.gwt.client.KlayGWT::layout(
            Lcom/google/gwt/core/client/JavaScriptObject;));
    }-*/;
    
    /**
     * Entry point to perform layout.
     * 
     * @param params
     *            an js object containing the graph, options, and callback functions.
     */
    public static void layout(final JavaScriptObject params) {

        // retrieve the passed parameters
        JSONObject obj = new JSONObject(params);
        
        JSONValue graph = obj.get("graph");
        JSONValue success = obj.get("success");
        JSONValue error = obj.get("error");
        JSONValue opts = obj.get("options");
        
        try {
            if (graph == null || success == null || graph.isObject() == null
                    || success.isObject() == null) {
                throw new UnsupportedConfigurationException(
                        "Mandatory parameters missing, 'graph' and 'success' must be specified");
            }

            // convert to lgraph and layout
            //new JsonGraphImporter().layout(graph.isObject(), opts != null ? opts.isObject() : null);
            new RecursiveLGraphLayout().layout(graph.isObject(), opts != null ? opts.isObject() : null);

            // pass the layouted graph to the callback
            JavaScriptObject result = graph.isObject().getJavaScriptObject();
            execCallback(success.isObject().getJavaScriptObject(), result);

        } catch (Exception e) {
            if (error != null && error.isObject() != null) {
                JSONObject errObj = new JSONObject();
                errObj.put("text", new JSONString(e.getClass() + " " + e.getMessage()));
                execCallback(error.isObject().getJavaScriptObject(),
                        errObj.getJavaScriptObject());
            }
            e.printStackTrace();
        }

    }
        
    private static native void execCallback(final JavaScriptObject callback, 
            final JavaScriptObject json) /*-{
        $entry(callback(json));
    }-*/;
   
}
