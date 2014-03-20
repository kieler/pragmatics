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
package de.cau.cs.kieler.klay.cola;

import java.util.Map.Entry;

import org.adaptagrams.AvoidTopologyAddon;
import org.adaptagrams.Box;
import org.adaptagrams.ShapeRef;
import org.adaptagrams.VariableIDMap;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.libavoid.LibavoidGraph;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;

/**
 * @author uru
 *
 */
public class TIOLayoutProvider extends AbstractLayoutProvider {

    private static final int FIRST_INDEX = 5;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Improve Layout Topology Preserving", 1);
        
        
        
        parentNode.getData(KShapeLayout.class).setProperty(ColaProperties.PORT_DUMMIES, false);
        
        KGraphAdapter adapter = KGraphAdapters.adapt(parentNode);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).process();
        
        
        final CGraph graph = new KGraphImporter().importGraph(parentNode);
        
        LibavoidGraph libGraph = new LibavoidGraph(parentNode) {
            /**
             * {@inheritDoc}
             */
            @Override
            protected void applyLayout(final KNode root) {
                
                
                // dont apply edge bends
                
                // only move nodes
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
                //offset = new KVector();

                for (Entry<Integer, ShapeRef> entry : this.idShapeRefMap.entrySet()) {

                    
                    if (entry.getKey() < FIRST_INDEX) {
                        continue;
                    }

                    KNode node = nodeIdMap.get(entry.getKey());
                    Margins margins = node.getData(KShapeLayout.class).getProperty(LayoutOptions.MARGINS);
                    ShapeRef sr = entry.getValue();
                    
                    
                    
                    System.out.println(entry + " " + node);

                    KShapeLayout layout = node.getData(KShapeLayout.class);
                    layout.setXpos((float) (sr.routingBox().getMin().getX() + offset.x + margins.left));
                    layout.setYpos((float) (sr.routingBox().getMin().getY() + offset.y + margins.top));

                }
                
                super.applyLayout(root);
                
            }
        };
        libGraph.transformGraph();
        
        VariableIDMap idmap = new VariableIDMap();
        for (CNode n : graph.getChildren()) {
            KNode origin = (KNode) n.getProperty(ColaProperties.ORIGIN);
            Integer libId = libGraph.getNodeIdMap().inverse().get(origin);
            idmap.addMappingForVariable(n.cIndex, libId);
            System.out.println("Mapping: " + libId + " " + origin + " ");
        }

        AvoidTopologyAddon addon =
                new AvoidTopologyAddon(graph.nodes, graph.constraints, graph.rootCluster, idmap);
        libGraph.getRouter().setTopologyAddon(addon);

        libGraph.process();
        
        
        progressMonitor.done();
    }
    
    
}
