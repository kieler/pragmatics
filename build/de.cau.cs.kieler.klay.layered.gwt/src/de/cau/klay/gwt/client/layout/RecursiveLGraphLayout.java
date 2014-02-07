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
package de.cau.klay.gwt.client.layout;

import com.google.gwt.json.client.JSONObject;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Recursively layouts an hierarchical {@link LGraph}.
 * 
 * @author uru
 */
public class RecursiveLGraphLayout {

    /**
     * Layouts the passed hierarchical {@link LGraph}.
     * 
     * @param json
     *            the graph in json format.
     * @param options
     *            additional options that will be added to every 'flat' {@link LGraph}, can be null.
     */
    public void layout(final JSONObject json, final JSONObject options) {

        // the importer
        JsonGraphImporter importer = new JsonGraphImporter();

        // global layout options
        // TODO pass them to the importer ...
        // globalOptions = options;

        // transform JSON -> LGraph
        LGraph graph = importer.importGraph(json);

        // perform layer-based layout
        KlayLayered klayLayered = new KlayLayered();
        // LGraph result = klayLayered.doLayout(graph, new BasicProgressMonitor());
        LGraph result = recLayout(klayLayered, graph);

        // transfer the layout information back to the json objects
        importer.applyLayout(result);
    }

    private LGraph recLayout(final KlayLayered layered, final LGraph graph) {

        for (LNode n : graph.getLayerlessNodes()) {
            LGraph childGraph = n.getProperty(Properties.CHILD_LGRAPH);
            if (childGraph != null) {
                LGraph res = recLayout(layered, childGraph);

                // resize the compound node, such that it is considered
                // properly when layouting the parent graph
                n.getSize().x = res.getSize().x;
                n.getSize().y = res.getSize().y;
            }
        }

        LGraph layouted = layered.doLayout(graph, new BasicProgressMonitor());

        return layouted;
    }

}
