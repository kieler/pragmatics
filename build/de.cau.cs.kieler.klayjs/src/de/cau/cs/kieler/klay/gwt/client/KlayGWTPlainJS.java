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
public class KlayGWTPlainJS implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        exportLayout();
    }
    
    // JSNI is not happy when some lines are wrapped
    // CHECKSTYLEOFF LineLength
    private native void exportLayout() /*-{
      
      var klay = {
        layout: function(d) {
                  $entry(@de.cau.cs.kieler.klay.gwt.client.KlayGWTPlainJS::layout(Lcom/google/gwt/core/client/JavaScriptObject;)(d));
                }
      };
      
      // in case the user passed a dedicated registration mechanism
      if (typeof klayregister === "function") {
          klayregister(klay);
      } else {
        // try to guess how we want to register
       
        // regular browser
        if (typeof document !== "undefined") {
          $wnd.$klay = klay;
        } 
        // nodejs
        if (typeof module === "object" && module.exports) { 
          module.exports = klay;
        } 
        // web worker
        if (typeof document === "undefined" && typeof self !== "undefined") {
          self.addEventListener('message', function(e) { klay.layout(e.data); }, false);
        }
      }
      
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
            if (graph == null || graph.isObject() == null) {
                throw new UnsupportedConfigurationException(
                        "Mandatory parameter missing, 'graph' must be specified");
            }

            // convert to lgraph and layout
            new RecursiveLGraphLayout().layout(graph.isObject(), opts != null ? opts.isObject() : null);

            // pass the layouted graph to the callback
            JavaScriptObject result = graph.isObject().getJavaScriptObject();
            if (success != null && success.isObject() != null) {
                execCallback(success.isObject().getJavaScriptObject(), result);
            } else {
                execCallback(null, result);
            }

        } catch (UnsupportedJsonGraphException ujge) {
            if (error != null && error.isObject() != null) {
                execCallback(error.isObject().getJavaScriptObject(), ujge.toJson()
                        .getJavaScriptObject());
            } else {
                execCallback(null, ujge.toJson().getJavaScriptObject());
            }
            
        } catch (Exception e) {

            JSONObject errObj = new JSONObject();
            errObj.put("type", new JSONString(e.getClass().getName()));
            if (e.getMessage() != null) {
                errObj.put("text", new JSONString(e.getMessage()));
            } else {
                errObj.put("text", new JSONString("null (sic)"));
            }
            errObj.put("stacktrace", new JSONString(Joiner.on("\n").join(e.getStackTrace())));

            if (error != null && error.isObject() != null) {
                execCallback(error.isObject().getJavaScriptObject(), errObj.isObject()
                        .getJavaScriptObject());
            } else {
                execCallback(null, errObj.isObject().getJavaScriptObject());
            }
            e.printStackTrace();
        }

    }
        
    private static native void execCallback(final JavaScriptObject callback, 
            final JavaScriptObject json) /*-{
        
      // in case the user passed a dedicated callback mechanism
      if (typeof klaycallback === "function") {
          klaycallback(json);
      } else {
        // try to guess how we want to call back
        
        if (typeof document !== "undefined") {
          // DEFAULT BROWSER 
          $entry(callback(json));
        } else if (typeof module === "object" && module.exports) { 
          // NODE JS
          $entry(callback(json));
        } if (typeof document === "undefined" && typeof self !== "undefined") {
          // WEB WORKER    
          self.postMessage(json);
        }
      }
       
    }-*/;

}
