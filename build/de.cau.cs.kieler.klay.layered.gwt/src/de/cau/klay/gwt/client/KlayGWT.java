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

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.Exportable;
import org.timepedia.exporter.client.ExporterUtil;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

import de.cau.klay.gwt.client.layout.JsonGraphImporter;

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

        // Export all js methods
        ExporterUtil.exportAll();

        klayInit();
    }

    private native void klayInit() /*-{
        if ($wnd.klayinit) {
            $wnd.klayinit();
        }
    }-*/;
    
    /**
     * Class defines the interface methods that are supplied to the JS developer.
     */
    public static class KlayLayoutInterface implements Exportable {
        
        @Export("$wnd.layout")
        public static void layout(final JavaScriptObject json, final JavaScriptObject callback) {

            try {
                // convert to lgraph and layout
                JSONObject obj = new JSONObject(json);
                new JsonGraphImporter().layout(obj);

                // pass the layouted graph to the callback
                JavaScriptObject result = obj.getJavaScriptObject();
                execCallback(callback, result);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
            
        public static native void execCallback(final JavaScriptObject callback, final JavaScriptObject json) /*-{
            $entry(callback(json));
        }-*/;
        
    }
}
