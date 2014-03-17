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
package de.cau.cs.kieler.klay.cola;

import java.util.Arrays;
import java.util.Map.Entry;

import org.adaptagrams.AvoidTopologyAddon;
import org.adaptagrams.Box;
import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.Rectangle;
import org.adaptagrams.ShapeRef;
import org.adaptagrams.VariableIDMap;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.libavoid.LibavoidGraph;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.cola.processors.DirectionConstraintProcessor;
import de.cau.cs.kieler.klay.cola.processors.NonUniformEdgeLengthProcessor;
import de.cau.cs.kieler.klay.cola.processors.PortConstraintProcessor;

/**
 * 
 * @author uru
 */
public class ColaLayoutProvider extends AbstractLayoutProvider {

    private float spacing;
    private float borderSpacing;

    private CGraph graph;

    /**
     * Main entry point of the layout provider.
     * 
     * {@inheritDoc}
     */
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {

        System.out.println("Called layout "
                + parentNode.getData(KShapeLayout.class)
                        .getProperty(LayoutOptions.LAYOUT_HIERARCHY));

        // handle some properties
        KLayoutData rootLayout = parentNode.getData(KLayoutData.class);

        // spacing
        spacing = rootLayout.getProperty(LayoutOptions.SPACING);
        Rectangle.setXBorder(spacing);
        Rectangle.setYBorder(spacing);

        borderSpacing = rootLayout.getProperty(LayoutOptions.BORDER_SPACING);

        // calculate margins
        calculateMarginsAndSizes(parentNode);

        // execute layout algorithm
        KGraphImporter importer = new KGraphImporter();
        graph = importer.importGraph(parentNode);

        BasicProgressMonitor bpm = new BasicProgressMonitor();
        new DirectionConstraintProcessor().process(graph, bpm);
        new PortConstraintProcessor().process(graph, bpm);
        new NonUniformEdgeLengthProcessor().process(graph, bpm);

        //System.out.println(parentNode);
        System.out.println(Arrays.toString(graph.idealEdgeLengths));

        // for the moment fix the issue where the edgelengths do not allow 0
        for (int i = 0; i < graph.idealEdgeLengths.length; ++i) {
            if (graph.idealEdgeLengths[i] == 0) {
                graph.idealEdgeLengths[i] = borderSpacing + 20;
            }
        }

        ConstrainedFDLayout algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 50, false,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());
        algo.setClusterHierarchy(graph.rootCluster);

        // run some w/o overlap
        algo.makeFeasible();

        int runs = 10;
        
        for (int i = 0; i < runs; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out" + i + ".svg");

            // System.out.println(i);
        }

        // do some with overlap
        algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 1, true,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());
        algo.setClusterHierarchy(graph.rootCluster);

        algo.makeFeasible();

        for (int i = 0; i < runs; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out99overlap" + i + ".svg");

            // System.out.println(i);
        }

        
        graph.rootCluster.computeBoundingRect(graph.nodes);
        
        /*
         * End
         */

        // apply the calculated layout back to the kgrap
        importer.applyLayout(graph);

        
        boolean doLibavoid = true;
        
        if(doLibavoid) {
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

                        if (entry.getKey() < 5) {
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
                KNode origin = (KNode) n.getProperty(KGraphImporter.ORIGIN);
                
                Integer libId = libavoid.getNodeIdMap().inverse().get(origin);
                idmap.addMappingForVariable(n.cIndex, libId);
                
            }
            
            AvoidTopologyAddon addon =
                    new AvoidTopologyAddon(graph.nodes, graph.constraints, graph.rootCluster, idmap);
            libavoid.getRouter().setTopologyAddon(addon);
    
            libavoid.process();
        }
        
        // cleanup c++ objects
        algo.freeAssociatedObjects();

    }


    private void calculateMarginsAndSizes(final KNode parent) {
        KGraphAdapter adapter = KGraphAdapters.adapt(parent);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.calculateNodeMargins(adapter);

        if (parent.getData(KLayoutData.class).getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {
            for (KNode child : parent.getChildren()) {
                calculateMarginsAndSizes(child);
            }
        }
    }
    
}
