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
package de.cau.cs.kieler.klay.cola.processors;

import java.util.Map.Entry;

import org.adaptagrams.AvoidTopologyAddon;
import org.adaptagrams.Box;
import org.adaptagrams.ShapeRef;
import org.adaptagrams.VariableIDMap;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.libavoid.LibavoidGraph;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.cola.ColaProperties;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;

/**
 * @author uru
 * 
 */
public class LibtopologyProcessor implements ILayoutProcessor {

    /**
     * First 'valid' index of the shape refs. Libavoid uses the first 5 indices for special
     * purposes.
     */
    private static final int FIRST_INDEX = 5;

    /**
     * {@inheritDoc}
     */
    public void process(final CGraph graph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Libtopology Addon", 1);

        KNode parentNode = (KNode) graph.getProperty(ColaProperties.ORIGIN);

        // do libavoid stuff
        LibavoidGraph libavoid = new LibavoidGraph(parentNode) {
            /**
             * {@inheritDoc}
             */
            @Override
            protected void applyLayout(final KNode root) {

                super.applyLayout(root);

                double borderSpacing = graph.getProperty(LayoutOptions.BORDER_SPACING);

                // calculate the offset from border spacing and node distribution
                double minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, maxX = Float.MIN_VALUE, maxY =
                        Float.MIN_VALUE;

                // find the minimal and maximal positions of the contained nodes
                for (ShapeRef sr : idShapeRefMap.values()) {
                    Box r = sr.routingBox();
                    minX = Math.min(minX, r.getMin().getX());
                    minY = Math.min(minY, r.getMin().getY());
                    maxX = Math.max(maxX, r.getMax().getX());
                    maxY = Math.max(maxY, r.getMax().getY());
                }

                KVector offset = new KVector(borderSpacing - minX, borderSpacing - minY);

                for (Entry<Integer, ShapeRef> entry : this.idShapeRefMap.entrySet()) {

                    if (entry.getKey() < FIRST_INDEX) {
                        continue;
                    }

                    KNode node = nodeIdMap.get(entry.getKey());
                    ShapeRef sr = entry.getValue();

                    KShapeLayout layout = node.getData(KShapeLayout.class);
                    layout.setXpos((float) (sr.routingBox().getMin().getX() + offset.x));
                    layout.setYpos((float) (sr.routingBox().getMin().getY() + offset.y));

                }

            }
        };
        libavoid.transformGraph();

        VariableIDMap idmap = new VariableIDMap();

        for (CNode n : graph.getChildren()) {
            KNode origin = (KNode) n.getProperty(ColaProperties.ORIGIN);

            Integer libId = libavoid.getNodeIdMap().inverse().get(origin);
            idmap.addMappingForVariable(n.cIndex, libId);

        }

        AvoidTopologyAddon addon =
                new AvoidTopologyAddon(graph.nodes, graph.constraints, graph.rootCluster, idmap);
        libavoid.getRouter().setTopologyAddon(addon);

        libavoid.process();

        progressMonitor.done();
    }
}
