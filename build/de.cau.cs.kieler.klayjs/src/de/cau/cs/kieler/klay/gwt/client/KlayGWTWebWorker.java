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
package de.cau.cs.kieler.klay.gwt.client;

import com.google.common.base.Joiner;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import de.cau.cs.kieler.kiml.UnsupportedConfigurationException;
import de.cau.cs.kieler.klay.gwt.client.layout.RecursiveLGraphLayout;
import de.cau.cs.kieler.klay.gwt.client.layout.UnsupportedJsonGraphException;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author uru
 */
public class KlayGWTWebWorker  implements EntryPoint {
    protected KlayGWTWebWorker() {
        
    }
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        exportLayout();
    }
    
    private native void exportLayout() /*-{
      $workergwtbridge = function(obj) {
      $entry(@de.cau.cs.kieler.klay.gwt.client.KlayGWTWebWorker::layout(Lcom/google/gwt/core/client/JavaScriptObject;)(obj));
      };
    }-*/;

    public static void layout(final JavaScriptObject params) {
        JSONObject obj = new JSONObject(params);

        JSONValue graph = obj.get("graph");
        JSONValue opts = obj.get("options");

        try {
            if (graph == null || graph.isObject() == null) {
                throw new UnsupportedConfigurationException(
                  "Mandatory parameters missing, 'graph' and 'success' must be specified");
            }
            new RecursiveLGraphLayout().layout(graph.isObject(), opts != null ? opts.isObject() : null);
            JavaScriptObject result = graph.isObject().getJavaScriptObject();
            postMessage(result);
        } catch (Exception e) {
            JSONObject errObj = new JSONObject();
            errObj.put("type", new JSONString(e.getClass().getName()));
            if (e.getMessage() != null) {
                errObj.put("text", new JSONString(e.getMessage()));
            } else {
                errObj.put("text", new JSONString("null (sic)"));
            }
            errObj.put("stacktrace", new JSONString(Joiner.on("\n").join(e.getStackTrace())));
            postMessage(errObj.isObject().getJavaScriptObject());

            e.printStackTrace();
        }
    }
 	
    // Workaround for posting from static method
    private native static void postMessage(final JavaScriptObject obj) /*-{
      self.postMessage(obj);
    }-*/;
}
